/**
 * @BelongsProject: refuse-classification-applet
 * @BelongsPackage: co.lvyi.controller
 * @Author: 吕易
 * @CreateTime: 2023-04-21  22:00
 * @Description: TODO
 * @Version: 1.0
 */
package co.lvyi.controller;

import co.lvyi.common.restful.ResultObject;
import co.lvyi.system.service.IOssService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

@RestController
@RequestMapping("/ossController/upload")
public class OssController {

    @Autowired
    private IOssService ossService;

    @PostMapping("/img")
    @ResponseBody
    public ResultObject<String> uploadImg(@RequestParam("file") MultipartFile file) {
        return ResultObject.success(ossService.upload(file));
    }

    @PostMapping("/video")
    @ResponseBody
    public ResultObject<Map<String, Object>> uploadVideo(@RequestParam("file") MultipartFile file) {
        return ResultObject.success(ossService.uploadVideo(file));
    }
}

