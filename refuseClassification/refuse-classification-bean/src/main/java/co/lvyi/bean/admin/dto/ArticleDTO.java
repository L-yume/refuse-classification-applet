/**
 * @BelongsProject: refuse-classification-applet
 * @BelongsPackage: co.lvyi.bean.admin.dto
 * @Author: 吕易
 * @CreateTime: 2023-04-20  16:11
 * @Description: TODO
 * @Version: 1.0
 */
package co.lvyi.bean.admin.dto;

import co.lvyi.bean.admin.entity.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="文章分页对象", description="文章")
public class ArticleDTO {

    private String title;
    private String reprintedFrom;
    @ApiModelProperty(value = "标题关键字")
    private String keywords;
    private String abstracts;
    private String content;
    private Integer isOnTop;
    @ApiModelProperty(value = "文章状态PUBLISHED,DELETED,DRAFT")
    private String status;
    private String attribute;
}

