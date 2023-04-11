/**
 * @BelongsProject: refuse-classification-applet
 * @BelongsPackage: co.lvyi.system.service.impl
 * @Author: 吕易
 * @CreateTime: 2023-04-11  16:46
 * @Description: TODO
 * @Version: 1.0
 */
package co.lvyi.system.service.impl;

import co.lvyi.bean.admin.entity.SysMenu;
import co.lvyi.bean.admin.entity.TreeSelect;
import co.lvyi.system.service.ISysMenuService;
import co.lvyi.system.vo.RouterVo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

/**
 * 菜单 业务层处理
 */
@Service
public class SysMenuServiceImpl implements ISysMenuService {
    @Override
    public List<SysMenu> selectMenuList(Long userId) {
        return null;
    }

    @Override
    public List<SysMenu> selectMenuList(SysMenu menu, Long userId) {
        return null;
    }

    @Override
    public Set<String> selectMenuPermsByUserId(Long userId) {
        return null;
    }

    @Override
    public Set<String> selectMenuPermsByRoleId(Long roleId) {
        return null;
    }

    @Override
    public List<SysMenu> selectMenuTreeByUserId(Long userId) {
        return null;
    }

    @Override
    public List<Long> selectMenuListByRoleId(Long roleId) {
        return null;
    }

    @Override
    public List<RouterVo> buildMenus(List<SysMenu> menus) {
        return null;
    }

    @Override
    public List<SysMenu> buildMenuTree(List<SysMenu> menus) {
        return null;
    }

    @Override
    public List<TreeSelect> buildMenuTreeSelect(List<SysMenu> menus) {
        return null;
    }

    @Override
    public SysMenu selectMenuById(Long menuId) {
        return null;
    }

    @Override
    public boolean hasChildByMenuId(Long menuId) {
        return false;
    }

    @Override
    public boolean checkMenuExistRole(Long menuId) {
        return false;
    }

    @Override
    public int insertMenu(SysMenu menu) {
        return 0;
    }

    @Override
    public int updateMenu(SysMenu menu) {
        return 0;
    }

    @Override
    public int deleteMenuById(Long menuId) {
        return 0;
    }

    @Override
    public boolean checkMenuNameUnique(SysMenu menu) {
        return false;
    }
}

