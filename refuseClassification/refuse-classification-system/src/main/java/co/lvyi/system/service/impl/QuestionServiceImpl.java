/**
 * @BelongsProject: refuse-classification-applet
 * @BelongsPackage: co.lvyi.system.service.impl
 * @Author: 吕易
 * @CreateTime: 2023-04-24  15:57
 * @Description: TODO
 * @Version: 1.0
 */
package co.lvyi.system.service.impl;

import co.lvyi.bean.admin.dto.QuestionDTO;
import co.lvyi.bean.admin.entity.Question;
import co.lvyi.system.mapper.QuestionMapper;
import co.lvyi.system.service.IQuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuestionServiceImpl implements IQuestionService {

    @Autowired
    private QuestionMapper questionMapper;

    @Override
    public List<Question> selectQuestionList(QuestionDTO questionDTO) {
        return questionMapper.selectQuestionList(questionDTO);
    }
}

