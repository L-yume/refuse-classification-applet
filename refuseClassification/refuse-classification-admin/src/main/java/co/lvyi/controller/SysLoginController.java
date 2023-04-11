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
import co.lvyi.common.constant.Constants;
import co.lvyi.common.restful.JsonResult;
import co.lvyi.security.service.SysLoginService;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

    @PostMapping("/login")
    public JsonResult login(@RequestBody LoginDTO loginDTO){
        JsonResult result = JsonResult.success();
        //生成令牌
        String token = sysLoginService.login(loginDTO.getUsername(), loginDTO.getPassword(),
                loginDTO.getCode(), loginDTO.getUuid());
        result.put(Constants.TOKEN, token);
        return result;
    }
}

