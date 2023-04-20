/**
 * @BelongsProject: refuse-classification-applet
 * @BelongsPackage: co.lvyi.system.mapper
 * @Author: 吕易
 * @CreateTime: 2023-04-20  16:15
 * @Description: TODO
 * @Version: 1.0
 */
package co.lvyi.system.mapper;

import co.lvyi.bean.admin.dto.ArticleDTO;
import co.lvyi.bean.admin.entity.Article;
import co.lvyi.bean.admin.vo.ArticleVO;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ArticleMapper extends BaseMapper<Article> {

    IPage<ArticleVO> findByPage(IPage<ArticleVO> page, @Param(Constants.WRAPPER) Wrapper<ArticleDTO> wrapper);

    List<Article> selectArticleList(ArticleDTO articleDTO);
}

