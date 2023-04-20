/**
 * @BelongsProject: refuse-classification-applet
 * @BelongsPackage: co.lvyi.bean.admin.entity
 * @Author: 吕易
 * @CreateTime: 2023-04-18  15:30
 * @Description: TODO
 * @Version: 1.0
 */
package co.lvyi.bean.admin.entity;

import lombok.Data;

import java.util.Date;

/**
 * 垃圾类别对象
 */
@Data
public class RefuseSort extends BaseEntity{

    private static final long serialVersionUID = 1L;

    /**
     * 垃圾类别id
     */
    private Integer sortId;

    /**
     * 垃圾类型
     */
    private String refuseType;

    /**
     * 垃圾描述
     */
    private String description;
}

