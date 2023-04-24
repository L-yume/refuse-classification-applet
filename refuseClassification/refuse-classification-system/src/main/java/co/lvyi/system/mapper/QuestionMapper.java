package co.lvyi.system.mapper;

import co.lvyi.bean.admin.dto.QuestionDTO;
import co.lvyi.bean.admin.entity.Question;

import java.util.List;

public interface QuestionMapper {

    List<Question> selectQuestionList(QuestionDTO questionDTO);
}
