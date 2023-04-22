/**
 * @BelongsProject: refuse-classification-applet
 * @BelongsPackage: co.lvyi.bean.admin.entity
 * @Author: 吕易
 * @CreateTime: 2023-04-22  14:44
 * @Description: TODO
 * @Version: 1.0
 */
package co.lvyi.bean.admin.entity;

import lombok.Data;

@Data
public class Video extends BaseEntity{
    private static final long serialVersionUID = 1L;

    private Long videoId;
    private String title;
    private String name;
    private String path;
    private String duration;
    private String videoSize;
    private String videoFormat;
    private boolean status;

}

