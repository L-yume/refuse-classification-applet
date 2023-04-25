/**
 * @BelongsProject: refuse-classification-applet
 * @BelongsPackage: co.lvyi.system.entity
 * @Author: 吕易
 * @CreateTime: 2023-04-25  13:38
 * @Description: TODO
 * @Version: 1.0
 */
package co.lvyi.system.entity;

import lombok.Data;

@Data
public class SysRoleMenu {
    /** 角色ID */
    private Long roleId;

    /** 菜单ID */
    private Long menuId;
}

