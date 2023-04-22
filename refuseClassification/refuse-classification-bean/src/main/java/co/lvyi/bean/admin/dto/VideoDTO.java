/**
 * @BelongsProject: refuse-classification-applet
 * @BelongsPackage: co.lvyi.bean.admin.dto
 * @Author: 吕易
 * @CreateTime: 2023-04-22  15:46
 * @Description: TODO
 * @Version: 1.0
 */
package co.lvyi.bean.admin.dto;

import lombok.Data;

@Data
public class VideoDTO {

    private String title;
    private String name;
    private String path;
    private String duration;
    private String videoSize;
    private String videoFormat;
}

