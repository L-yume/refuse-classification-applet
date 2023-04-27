/**
 * @BelongsProject: refuse-classification-applet
 * @BelongsPackage: co.lvyi.controller
 * @Author: 吕易
 * @CreateTime: 2023-04-24  15:59
 * @Description: TODO
 * @Version: 1.0
 */
package co.lvyi.controller;

import co.lvyi.bean.admin.dto.QuestionDTO;
import co.lvyi.bean.admin.entity.Question;
import co.lvyi.common.annotation.Log;
import co.lvyi.common.controller.BaseController;
import co.lvyi.common.enums.BusinessType;
import co.lvyi.common.page.TableDataInfo;
import co.lvyi.common.restful.JsonResult;
import co.lvyi.system.service.IQuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("uniapp/test")
public class QuestionController extends BaseController {
    @Autowired
    private IQuestionService questionService;

    @PreAuthorize("@ss.hasPermi('uniapp:test:list')")
    @GetMapping("/list")
    public TableDataInfo list(QuestionDTO questionDTO) {
        startPage();
        List<Question> list = questionService.selectQuestionList(questionDTO);
        return getDataTable(list);
    }

    @Log(title = "新增试题", businessType = BusinessType.INSERT)
    @PostMapping
    public JsonResult add(@Validated @RequestBody QuestionDTO questionDTO) {
        return toAjax(questionService.addTest(questionDTO));
    }

    @GetMapping(value = "/{questionId}")
    public JsonResult getInfo(@PathVariable Integer questionId) {
        return success(questionService.selectTestById(questionId));
    }

    @Log(title = "修改试题", businessType = BusinessType.UPDATE)
    @PutMapping
    public JsonResult update(@Validated @RequestBody QuestionDTO questionDTO) {
        return toAjax(questionService.updateTest(questionDTO));
    }

    @Log(title = "删除试题", businessType = BusinessType.DELETE)
    @DeleteMapping("/{questionId}")
    public JsonResult delete(@PathVariable Integer questionId) {
        return toAjax(questionService.deleteTestById(questionId));
    }
}

