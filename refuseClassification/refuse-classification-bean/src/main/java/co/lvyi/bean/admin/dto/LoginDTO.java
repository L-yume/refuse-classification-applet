/**
 * @BelongsProject: refuse-classification-applet
 * @BelongsPackage: co.lvyi.bean.admin.dto
 * @Author: 吕易
 * @CreateTime: 2023-04-09  13:34
 * @Description: TODO
 * @Version: 1.0
 */
package co.lvyi.bean.admin.dto;

import lombok.Data;

/**
 * 登录对象
 */
@Data
public class LoginDTO {
    /**
     * 用户名
     */
    private String username;

    /**
     * 用户密码
     */
    private String password;

    /**
     * 验证码
     */
    private String code;

    /**
     * 唯一标识
     */
    private String uuid;
}

