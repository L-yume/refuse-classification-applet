/**
 * @BelongsProject: refuse-classification-applet
 * @BelongsPackage: co.lvyi.controller
 * @Author: 吕易
 * @CreateTime: 2023-04-16  15:29
 * @Description: TODO
 * @Version: 1.0
 */
package co.lvyi.controller;

import co.lvyi.bean.admin.entity.SysUser;
import co.lvyi.common.controller.BaseController;
import co.lvyi.common.page.TableDataInfo;
import co.lvyi.system.service.ISysRoleService;
import co.lvyi.system.service.ISysUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 用户信息
 */
@RestController
@RequestMapping("system/user")
@Slf4j
public class SysUserController extends BaseController {

    @Autowired
    public ISysUserService userService;

    @Autowired
    public ISysRoleService roleService;

    /**
     * 获取用户列表
     */
    @PreAuthorize("@ss.hasPermi('system:user:list')")
    @GetMapping("/list")
    public TableDataInfo list(SysUser user)
    {
        startPage();
        List<SysUser> list = userService.selectUserList(user);
        log.info("用户信息:"+getDataTable(list));
        return getDataTable(list);
    }
}

