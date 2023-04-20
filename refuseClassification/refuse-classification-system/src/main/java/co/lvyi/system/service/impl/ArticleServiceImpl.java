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
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArticleServiceImpl extends ServiceImpl<ArticleMapper, Article> implements IArticleService {

    @Autowired
    private ArticleMapper articleMapper;

    @Override
    public IPage<ArticleVO> findByPage(ArticleDTO articleDTO) {
        QueryWrapper<ArticleDTO> queryWrapper = new QueryWrapper<>();
        if (articleDTO.getOrderBy() != null){
            String[] cloums = articleDTO.getOrderBy().split(",");
            queryWrapper.orderBy(true, true, cloums);
        }
        if (StringUtils.isNotEmpty(articleDTO.getTitleKeyword())){
            queryWrapper.like("title", "%" + articleDTO.getTitleKeyword() + "%");
        }
        if (StringUtils.isNotEmpty(articleDTO.getPostStatus())){
            queryWrapper.eq("status", articleDTO.getPostStatus());
        }

        Page<ArticleVO> articlePage = new Page<>(articleDTO.getPage(), articleDTO.getPageSize());
        return this.getBaseMapper().findByPage(articlePage, queryWrapper);
    }

    @Override
    public List<Article> selectArticleList(ArticleDTO articleDTO) {
        return articleMapper.selectArticleList(articleDTO);
    }
}

