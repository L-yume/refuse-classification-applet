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
    long pageSize;
    long page;

    @ApiModelProperty(value = "排序字段,按数据库字段:menu_order,post_date,post_modified,page_view;多个以逗号间隔")
    private String orderBy;
    @ApiModelProperty(value = "是否升序，boolean类型")
    private boolean isAsc;
    @ApiModelProperty(value = "标题关键字")
    private String TitleKeyword;

    @ApiModelProperty(value = "文章状态PUBLISHED,DELETED,DRAFT")
    private String postStatus;
}

