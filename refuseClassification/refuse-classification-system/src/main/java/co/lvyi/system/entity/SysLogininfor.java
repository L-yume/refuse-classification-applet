/**
 * @BelongsProject: refuse-classification-applet
 * @BelongsPackage: co.lvyi.system.entity
 * @Author: 吕易
 * @CreateTime: 2023-04-08  15:08
 * @Description: TODO
 * @Version: 1.0
 */
package co.lvyi.system.entity;

import co.lvyi.bean.admin.entity.BaseEntity;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

/**
 * 系统访问记录表 sys_logininfor
 */
@Data
public class SysLogininfor extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /** ID */
    private Long infoId;

    /** 用户账号 */
    private String userName;

    /** 登录状态 0成功 1失败 */
    private String status;

    /** 登录IP地址 */
    private String ipaddr;

    /** 登录地点 */
    private String loginLocation;

    /** 浏览器类型 */
    private String browser;

    /** 操作系统 */
    private String os;

    /** 提示消息 */
    private String msg;

    /** 访问时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date loginTime;

}

