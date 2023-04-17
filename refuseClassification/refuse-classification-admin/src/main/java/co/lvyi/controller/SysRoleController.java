/**
 * @BelongsProject: refuse-classification-applet
 * @BelongsPackage: co.lvyi.controller
 * @Author: 吕易
 * @CreateTime: 2023-04-16  22:51
 * @Description: TODO
 * @Version: 1.0
 */
package co.lvyi.controller;

import co.lvyi.bean.admin.entity.SysRole;
import co.lvyi.common.controller.BaseController;
import co.lvyi.common.page.TableDataInfo;
import co.lvyi.security.service.TokenService;
import co.lvyi.system.service.ISysRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 角色信息
 */
@RestController
@RequestMapping("system/role")
public class SysRoleController extends BaseController {

    @Autowired
    private ISysRoleService roleService;

    @Autowired
    private TokenService tokenService;

    @PreAuthorize("@ss.hasPermi('system:role:list')")
    @GetMapping("/list")
    public TableDataInfo list(SysRole role)
    {
        startPage();
        List<SysRole> list = roleService.selectRoleList(role);
        return getDataTable(list);
    }
}

