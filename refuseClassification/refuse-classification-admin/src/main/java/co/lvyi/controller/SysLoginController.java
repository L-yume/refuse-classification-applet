/**
 * @BelongsProject: refuse-classification-applet
 * @BelongsPackage: co.lvyi.admin.controller
 * @Author: 吕易
 * @CreateTime: 2023-04-09  13:25
 * @Description: TODO
 * @Version: 1.0
 */
package co.lvyi.controller;

import co.lvyi.bean.admin.dto.LoginDTO;
import co.lvyi.bean.admin.entity.SysMenu;
import co.lvyi.bean.admin.entity.SysUser;
import co.lvyi.common.constant.Constants;
import co.lvyi.common.restful.JsonResult;
import co.lvyi.common.utils.SecurityUtils;
import co.lvyi.security.service.SysLoginService;
import co.lvyi.security.service.SysPermissionService;
import co.lvyi.system.service.ISysMenuService;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

/**
 * 登录验证
 */
@RestController
@RequestMapping("/admin")
@Slf4j
@Api(tags = "管理员登录功能")
public class SysLoginController {

    @Autowired
    private SysLoginService sysLoginService;

    @Autowired
    private ISysMenuService menuService;

    @Autowired
    private SysPermissionService permissionService;

    @PostMapping("/login")
    public JsonResult login(@RequestBody LoginDTO loginDTO){
        log.info("开始执行登录操作...");
        JsonResult result = JsonResult.success();
        //生成令牌
        String token = sysLoginService.login(loginDTO.getUsername(), loginDTO.getPassword(),
                loginDTO.getCode(), loginDTO.getUuid());
        result.put(Constants.TOKEN, token);
        return result;
    }

    /**
     * 获取用户信息
     *
     * @return 用户信息
     */
    @GetMapping("getInfo")
    public JsonResult getInfo()
    {
        log.info("获取用户信息...");
        SysUser user = SecurityUtils.getLoginUser().getUser();
        // 角色集合
        Set<String> roles = permissionService.getRolePermission(user);
        // 权限集合
        Set<String> permissions = permissionService.getMenuPermission(user);
        JsonResult ajax = JsonResult.success();
        ajax.put("user", user);
        ajax.put("roles", roles);
        ajax.put("permissions", permissions);
        log.info("用户信息:"+ajax);
        return ajax;
    }

    /**
     * 获取路由信息
     *
     * @return 路由信息
     */
    @GetMapping("getRouters")
    public JsonResult getRouters()
    {
        log.info("获取路由信息...");
        Long userId = SecurityUtils.getUserId();
        List<SysMenu> menus = menuService.selectMenuTreeByUserId(userId);
        log.info("路由信息:"+JsonResult.success(menuService.buildMenus(menus)));
        return JsonResult.success(menuService.buildMenus(menus));
    }
}

