package co.lvyi.system.service;

import co.lvyi.bean.admin.dto.QuestionDTO;
import co.lvyi.bean.admin.entity.Question;

import java.util.List;

public interface IQuestionService {

    public List<Question> selectQuestionList(QuestionDTO questionDTO);
}
