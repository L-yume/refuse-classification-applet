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
import co.lvyi.common.utils.StringUtils;
import co.lvyi.system.mapper.ArticleMapper;
import co.lvyi.system.service.IArticleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class ArticleServiceImpl extends ServiceImpl<ArticleMapper, Article> implements IArticleService {

    @Autowired
    private ArticleMapper articleMapper;

    private static ObjectMapper objectMapper = new ObjectMapper();

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
        //handleAttribute(articleDTO, article);
        return articleMapper.addArticle(article);
    }

    @Override
    public int deleteArticleById(Integer articleId) {
        return articleMapper.deleteArticleById(articleId);
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
}

