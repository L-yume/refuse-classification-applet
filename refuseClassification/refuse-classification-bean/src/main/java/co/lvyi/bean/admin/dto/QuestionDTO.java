/**
 * @BelongsProject: refuse-classification-applet
 * @BelongsPackage: co.lvyi.bean.admin.dto
 * @Author: 吕易
 * @CreateTime: 2023-04-24  15:37
 * @Description: TODO
 * @Version: 1.0
 */
package co.lvyi.bean.admin.dto;

import lombok.Data;

@Data
public class QuestionDTO {
    private Integer questionId;
    private String question;
    private String answerA;
    private String answerB;
    private String answerC;
    private String answerD;
    private String correctAnswer;
    private String analysis;
}

