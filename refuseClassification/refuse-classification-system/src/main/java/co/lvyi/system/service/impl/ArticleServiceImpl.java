/**
 * @BelongsProject: refuse-classification-applet
 * @BelongsPackage: co.lvyi.system.service.impl
 * @Author: 吕易
 * @CreateTime: 2023-04-20  19:51
 * @Description: TODO
 * @Version: 1.0
 */
package co.lvyi.system.service.impl;

import co.lvyi.bean.admin.dto.ArticleDTO;
import co.lvyi.bean.admin.entity.Article;
import co.lvyi.bean.admin.vo.ArticleVO;
import co.lvyi.common.exception.ServiceException;
import co.lvyi.common.utils.StringUtils;
import co.lvyi.system.mapper.ArticleMapper;
import co.lvyi.system.service.IArticleService;
import co.lvyi.system.service.IOssService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringEscapeUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
@Slf4j
public class ArticleServiceImpl extends ServiceImpl<ArticleMapper, Article> implements IArticleService {

    @Autowired
    private ArticleMapper articleMapper;

    @Autowired
    private ThreadPoolTaskExecutor ossUploadImageExecutor;

    @Autowired
    private IOssService ossService;

    private static ObjectMapper objectMapper = new ObjectMapper();

    // 匹配图片的 markdown 语法
    // ![](hhhx.png)
    // ![xx](hhhx.png?ax)
    public static final String IMG_PATTERN = "\\!\\[(.*)\\]\\((.*)\\)";

    @Override
    public List<Article> selectArticleList(ArticleDTO articleDTO) {
        return articleMapper.selectArticleList(articleDTO);
    }

    @Override
    public ArticleVO selectArticleById(Integer articleId) {
        return articleMapper.selectArticleById(articleId);
    }

    @Override
    public int addArticle(ArticleDTO articleDTO) {
        Article article = new Article();
        BeanUtils.copyProperties(articleDTO, article);
        article.setKeywords(article.getTitle());
        article.setIsOnTop(0);
        handleContentImg(article);
        //handleAttribute(articleDTO, article);
        return articleMapper.addArticle(article);
    }

    @Override
    public int deleteArticleById(Integer articleId) {
        return articleMapper.deleteArticleById(articleId);
    }

    @Override
    public int updateArticle(ArticleDTO articleDTO) {
        if (articleDTO.getArticleId() == null) {
            log.error("更新文章时，文章 ID 为空");
            throw new ServiceException("文章 ID 不能为空");
        }

        /*Article article = getById(articleDTO.getArticleId());
        if (article == null) {
            log.error("文章不存在，文章ID{}",articleDTO.getArticleId());
            throw new ServiceException("更新文章时，文章不存在");
        }*/

        Article article = new Article();
        BeanUtils.copyProperties(articleDTO, article);
        handleContentImg(article);
        return articleMapper.updateArticle(article);
    }

    private void handleAttribute(ArticleDTO articleDTO, Article article) {
        if (StringUtils.isNotEmpty(articleDTO.getAttribute())) {
            try {
                Map attribute = objectMapper.readValue(articleDTO.getAttribute(), Map.class);
                //article.setAttribute(attribute);
            } catch (JsonProcessingException e) {
                log.error("扩展字段处理出错：{}", e);
            }
        }
    }

    /**
     * 对外链图片进行转链
     *
     * @param article
     */
    private void handleContentImg(Article article) {
        String content = article.getContent();
        String htmlContent = article.getHtmlContent();

        if (StringUtils.isEmpty(content) || StringUtils.isEmpty(htmlContent)) {
            return;
        }

        htmlContent = StringEscapeUtils.unescapeHtml4(htmlContent);

        Pattern p = Pattern.compile(IMG_PATTERN, Pattern.CASE_INSENSITIVE);
        Matcher m = p.matcher(content);

        Map<String, Future<String>> map = new HashMap<>();

        while (m.find()) {
            String imageName = m.group(1);
            String imageUrl = m.group(2);

            log.info("使用分组进行替换图片名字：{}，图片路径：{}", imageName, imageUrl);

            // 确认是本站链接，不处理
            if (!ossService.needUpload(imageUrl)) {
                continue;
            }

            // 通过线程池将图片上传到 OSS
            Future<String> future = ossUploadImageExecutor.submit(() -> ossService.upload(imageUrl));
            map.put(imageUrl, future);
        }
        for (String oldUrl : map.keySet()) {
            Future<String> future = map.get(oldUrl);

            String imageUrl = null;
            try {
                imageUrl = future.get();
            } catch (InterruptedException | ExecutionException e) {
                log.error("图片地址获取出错{}", e);
            }
            content = content.replace(oldUrl, imageUrl);

            if (StringUtils.isEmpty(htmlContent)) {
                htmlContent = htmlContent.replace(oldUrl, imageUrl);
            }
        }
        article.setContent(content);
        article.setHtmlContent(htmlContent);
    }
}

