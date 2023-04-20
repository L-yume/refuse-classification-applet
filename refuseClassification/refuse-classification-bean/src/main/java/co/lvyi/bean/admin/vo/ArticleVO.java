/**
 * @BelongsProject: refuse-classification-applet
 * @BelongsPackage: co.lvyi.bean.admin.vo
 * @Author: 吕易
 * @CreateTime: 2023-04-20  16:27
 * @Description: TODO
 * @Version: 1.0
 */
package co.lvyi.bean.admin.vo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.handlers.JacksonTypeHandler;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;
import java.util.Map;

@Data
@ApiModel(value = "ArticleVo", description = "文章Vo")
@EqualsAndHashCode(callSuper = false)
@TableName(autoResultMap = true)
public class ArticleVO extends BaseVO{

    @ApiModelProperty(value = "文章Id")
    private Integer articleId;

    @ApiModelProperty(value = "转载自")
    private String reprintedFrom;

    @ApiModelProperty(value = "发布时间")
    private Date createdTime;

    @ApiModelProperty(value = "正文")
    private String content;

    @ApiModelProperty(value = "标题")
    private String title;

    @ApiModelProperty(value = "文章状态")
    private String status;


    @ApiModelProperty(value = "修改时间")
    private Date updatedTime;

    @TableField(typeHandler = JacksonTypeHandler.class)
    @ApiModelProperty("属性")
    private Map<String,Object> attribute;


    @ApiModelProperty("格式化修改时间")
    private String postModifiedShortTime;

    public String getPostModifiedShortTime() {
        return DateUtil.getShortTime(getUpdatedTime());
    }


}

