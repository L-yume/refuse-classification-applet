/**
 * @BelongsProject: refuse-classification-applet
 * @BelongsPackage: co.lvyi.controller
 * @Author: 吕易
 * @CreateTime: 2023-04-17  11:02
 * @Description: TODO
 * @Version: 1.0
 */
package co.lvyi.controller;

import co.lvyi.bean.admin.entity.SysMenu;
import co.lvyi.common.controller.BaseController;
import co.lvyi.common.restful.JsonResult;
import co.lvyi.system.service.ISysMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 菜单信息
 */
@RestController
@RequestMapping("system/menu")
public class SysMenuController extends BaseController {

    @Autowired
    private ISysMenuService menuService;

    /**
     * 获取菜单列表
     */
    @PreAuthorize("@ss.hasPermi('system:menu:list')")
    @GetMapping("/list")
    public JsonResult list(SysMenu menu)
    {
        List<SysMenu> menus = menuService.selectMenuList(menu, getUserId());
        return success(menus);
    }
}

