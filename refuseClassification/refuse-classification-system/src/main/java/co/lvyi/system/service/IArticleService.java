package co.lvyi.system.service;

import co.lvyi.bean.admin.dto.ArticleDTO;
import co.lvyi.bean.admin.entity.Article;
import co.lvyi.bean.admin.vo.ArticleVO;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

public interface IArticleService extends IService<Article> {


    List<Article> selectArticleList(ArticleDTO articleDTO);

    ArticleVO selectArticleById(Integer articleId);

    int addArticle(ArticleDTO articleDTO);
}
