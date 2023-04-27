package co.lvyi.system.mapper;

import co.lvyi.bean.admin.dto.QuestionDTO;
import co.lvyi.bean.admin.entity.Question;

import java.util.List;

public interface QuestionMapper {

    List<Question> selectQuestionList(QuestionDTO questionDTO);

    int addTest(Question question);

    Question selectTestById(Integer questionId);

    int updateTest(QuestionDTO questionDTO);

    int deleteTestById(Integer questionId);
}
