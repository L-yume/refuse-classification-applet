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
import co.lvyi.common.annotation.Log;
import co.lvyi.common.controller.BaseController;
import co.lvyi.common.enums.BusinessType;
import co.lvyi.common.page.TableDataInfo;
import co.lvyi.common.restful.JsonResult;
import co.lvyi.system.service.IRefuseSortService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/{sortId}")
    public JsonResult getInfo(@PathVariable Integer sortId) {
        return success(refuseSortService.selectSortById(sortId));
    }

    @Log(title = "垃圾分类", businessType = BusinessType.INSERT)
    @PostMapping
    public JsonResult add(@Validated @RequestBody RefuseSort refuseSort) {
        return toAjax(refuseSortService.insertSort(refuseSort));
    }

    @Log(title = "垃圾分类", businessType = BusinessType.UPDATE)
    @PutMapping
    public JsonResult update(@Validated @RequestBody RefuseSort refuseSort) {
        return toAjax(refuseSortService.updateSort(refuseSort));
    }

    @Log(title = "垃圾分类", businessType = BusinessType.DELETE)
    @DeleteMapping("/{sortId}")
    public JsonResult remove(@PathVariable Integer sortId) {
        return toAjax(refuseSortService.deleteSortById(sortId));
    }
}

