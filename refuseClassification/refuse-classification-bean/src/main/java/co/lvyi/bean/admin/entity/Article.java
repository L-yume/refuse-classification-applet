/**
 * @BelongsProject: refuse-classification-applet
 * @BelongsPackage: co.lvyi.bean.admin.entity
 * @Author: 吕易
 * @CreateTime: 2023-04-20  16:59
 * @Description: TODO
 * @Version: 1.0
 */
package co.lvyi.bean.admin.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.handlers.JacksonTypeHandler;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.Date;
import java.util.Map;

@Data
@ApiModel(value="Article对象", description="文章")
@TableName("articles")
@EqualsAndHashCode(callSuper = false)
public class Article implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "article_id")
    @TableId(value = "article_id", type = IdType.AUTO)
    private Integer articleId;

    @ApiModelProperty(value = "对应作者ID")
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
}

