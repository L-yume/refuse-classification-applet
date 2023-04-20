/**
 * @BelongsProject: refuse-classification-applet
 * @BelongsPackage: co.lvyi.controller
 * @Author: 吕易
 * @CreateTime: 2023-04-18  16:05
 * @Description: TODO
 * @Version: 1.0
 */
package co.lvyi.controller;

import co.lvyi.bean.admin.entity.RefuseSort;
import co.lvyi.common.controller.BaseController;
import co.lvyi.common.page.TableDataInfo;
import co.lvyi.system.service.IRefuseSortService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("uniapp/sort")
@Slf4j
public class RefuseSortController extends BaseController {

    @Autowired
    private IRefuseSortService refuseSortService;

    @GetMapping("/list")
    public TableDataInfo list(RefuseSort refuseSort){
        startPage();
        List<RefuseSort> list = refuseSortService.selectSortList(refuseSort);
        log.info("垃圾类别信息:"+getDataTable(list));
        return getDataTable(list);
    }
}

