/**
 * @BelongsProject: refuse-classification-applet
 * @BelongsPackage: co.lvyi.security.handle
 * @Author: 吕易
 * @CreateTime: 2023-04-10  16:40
 * @Description: TODO
 * @Version: 1.0
 */
package co.lvyi.security.handle;

import co.lvyi.common.constant.RespondCode;
import co.lvyi.common.restful.JsonResult;
import co.lvyi.common.utils.ServletUtils;
import co.lvyi.common.utils.StringUtils;
import com.alibaba.fastjson2.JSON;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Serializable;

/**
 * 认证失败处理类 返回未授权
 */
@Component
public class AuthenticationEntryPointImpl implements AuthenticationEntryPoint, Serializable
{
    private static final long serialVersionUID = -8970718410437077606L;

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException e)
            throws IOException
    {
        int code = RespondCode.UNAUTHORIZED;
        String msg = StringUtils.format("请求访问：{}，认证失败，无法访问系统资源", request.getRequestURI());
        ServletUtils.renderString(response, JSON.toJSONString(JsonResult.error(code, msg)));
    }
}

