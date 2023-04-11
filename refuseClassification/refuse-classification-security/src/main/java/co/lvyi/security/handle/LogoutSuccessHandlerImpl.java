/**
 * @BelongsProject: refuse-classification-applet
 * @BelongsPackage: co.lvyi.security.handle
 * @Author: 吕易
 * @CreateTime: 2023-04-10  16:41
 * @Description: TODO
 * @Version: 1.0
 */
package co.lvyi.security.handle;

import co.lvyi.bean.admin.entity.LoginUser;
import co.lvyi.common.constant.Constants;
import co.lvyi.common.restful.JsonResult;
import co.lvyi.common.utils.ServletUtils;
import co.lvyi.common.utils.StringUtils;
import co.lvyi.security.manager.AsyncFactory;
import co.lvyi.security.manager.AsyncManager;
import co.lvyi.security.service.TokenService;
import com.alibaba.fastjson2.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 自定义退出处理类 返回成功
 */
@Configuration
public class LogoutSuccessHandlerImpl implements LogoutSuccessHandler
{
    @Autowired
    private TokenService tokenService;

    /**
     * 退出处理
     *
     * @return
     */
    @Override
    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
            throws IOException, ServletException
    {
        LoginUser loginUser = tokenService.getLoginUser(request);
        if (StringUtils.isNotNull(loginUser))
        {
            String userName = loginUser.getUsername();
            // 删除用户缓存记录
            tokenService.delLoginUser(loginUser.getToken());
            // 记录用户退出日志
            AsyncManager.me().execute(AsyncFactory.recordLogininfor(userName, Constants.LOGOUT, "退出成功"));
        }
        ServletUtils.renderString(response, JSON.toJSONString(JsonResult.success("退出成功")));
    }
}

