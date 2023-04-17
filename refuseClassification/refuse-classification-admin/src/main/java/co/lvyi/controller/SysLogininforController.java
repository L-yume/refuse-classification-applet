/**
 * @BelongsProject: refuse-classification-applet
 * @BelongsPackage: co.lvyi.controller
 * @Author: 吕易
 * @CreateTime: 2023-04-17  15:57
 * @Description: TODO
 * @Version: 1.0
 */
package co.lvyi.controller;

import co.lvyi.common.controller.BaseController;
import co.lvyi.common.page.TableDataInfo;
import co.lvyi.system.entity.SysLogininfor;
import co.lvyi.system.service.ISysLogininforService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 系统访问记录
 */
@RestController
@RequestMapping("system/logininfor")
public class SysLogininforController extends BaseController {

    @Autowired
    private ISysLogininforService logininforService;

    @PreAuthorize("@ss.hasPermi('system:logininfor:list')")
    @GetMapping("/list")
    public TableDataInfo list(SysLogininfor logininfor)
    {
        startPage();
        List<SysLogininfor> list = logininforService.selectLogininforList(logininfor);
        return getDataTable(list);
    }
}

