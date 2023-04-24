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
import co.lvyi.common.controller.BaseController;
import co.lvyi.common.page.TableDataInfo;
import co.lvyi.system.service.IQuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}

