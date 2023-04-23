/**
 * @BelongsProject: refuse-classification-applet
 * @BelongsPackage: co.lvyi.controller
 * @Author: 吕易
 * @CreateTime: 2023-04-23  21:30
 * @Description: TODO
 * @Version: 1.0
 */
package co.lvyi.controller;

import co.lvyi.bean.admin.dto.VideoDTO;
import co.lvyi.bean.admin.entity.Video;
import co.lvyi.common.controller.BaseController;
import co.lvyi.common.page.TableDataInfo;
import co.lvyi.system.service.IVideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/uniapp/video")
public class VideoController extends BaseController {

    @Autowired
    private IVideoService videoService;

    @PreAuthorize("@ss.hasPermi('uniapp:video:list')")
    @GetMapping("/list")
    public TableDataInfo list(VideoDTO videoDTO) {
        startPage();
        List<Video> list = videoService.selectVideoList(videoDTO);
        return getDataTable(list);
    }
}

