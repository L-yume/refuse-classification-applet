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
import co.lvyi.bean.admin.vo.ArticleVO;
import co.lvyi.common.controller.BaseController;
import co.lvyi.common.page.TableDataInfo;
import co.lvyi.common.restful.ResultObject;
import co.lvyi.system.service.IArticleService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/system/article")
@Slf4j
public class ArticleController extends BaseController {

    @Autowired
    private IArticleService articleService;

    @GetMapping("/list")
    @ResponseBody
    public ResultObject<Map<String, Object>> list(ArticleDTO articleDTO) {
        Map<String, Object> map = new HashMap<>();
        IPage<ArticleVO> page = articleService.findByPage(articleDTO);
        log.info("文章列表："+page);
        map.put("items", page.getRecords());
        map.put("total", page.getTotal());
        return ResultObject.success(map);
    }

    @GetMapping("/list2")
    public TableDataInfo list2(ArticleDTO articleDTO) {
        startPage();
        List<Article> list = articleService.selectArticleList(articleDTO);
        log.info("文章列表："+list);
        return getDataTable(list);
    }
}

