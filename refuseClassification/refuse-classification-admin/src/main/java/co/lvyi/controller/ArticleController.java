/**
 * @BelongsProject: refuse-classification-applet
 * @BelongsPackage: co.lvyi.controller
 * @Author: 吕易
 * @CreateTime: 2023-04-19  22:57
 * @Description: TODO
 * @Version: 1.0
 */
package co.lvyi.controller;

import co.lvyi.bean.admin.dto.ArticleDTO;
import co.lvyi.bean.admin.entity.Article;
import co.lvyi.common.annotation.Log;
import co.lvyi.common.controller.BaseController;
import co.lvyi.common.enums.BusinessType;
import co.lvyi.common.page.TableDataInfo;
import co.lvyi.common.restful.JsonResult;
import co.lvyi.system.service.IArticleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("uniapp/article")
@Slf4j
public class ArticleController extends BaseController {

    @Autowired
    private IArticleService articleService;


    @GetMapping("/list2")
    public TableDataInfo list2(ArticleDTO articleDTO) {
        startPage();
        List<Article> list = articleService.selectArticleList(articleDTO);
        log.info("文章列表："+list);
        return getDataTable(list);
    }

    @RequestMapping(value = "/getById", method = RequestMethod.GET)
    public JsonResult getInfo(@RequestParam(value = "articleId") Integer articleId) {
        log.info("sss"+articleService.selectArticleById(articleId));
        return success(articleService.selectArticleById(articleId));
    }

    @Log(title = "文章管理", businessType = BusinessType.INSERT)
    @PostMapping
    public JsonResult add(@Validated @RequestBody ArticleDTO articleDTO) {
        return toAjax(articleService.addArticle(articleDTO));
    }

    @Log(title = "删除文章", businessType = BusinessType.DELETE)
    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    public JsonResult delete(@RequestParam(value = "articleId") Integer articleId) {
        return toAjax(articleService.deleteArticleById(articleId));
    }

    @Log(title = "修改文章", businessType = BusinessType.UPDATE)
    @PutMapping
    public JsonResult update(@Validated @RequestBody ArticleDTO articleDTO) {
        return toAjax(articleService.updateArticle(articleDTO));
    }
}

