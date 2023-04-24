/**
 * @BelongsProject: refuse-classification-applet
 * @BelongsPackage: co.lvyi.bean.admin.entity
 * @Author: 吕易
 * @CreateTime: 2023-04-24  15:32
 * @Description: TODO
 * @Version: 1.0
 */
package co.lvyi.bean.admin.entity;

import lombok.Data;

@Data
public class Question extends BaseEntity{
    private static final long serialVersionUID = 1L;

    private Integer questionId;
    private String question;
    private String answerA;
    private String answerB;
    private String answerC;
    private String answerD;
    private String correctAnswer;
}

