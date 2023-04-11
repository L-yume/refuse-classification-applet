/**
 * @BelongsProject: refuse-classification-applet
 * @BelongsPackage: co.lvyi.common.exception
 * @Author: 吕易
 * @CreateTime: 2023-04-08  14:55
 * @Description: TODO
 * @Version: 1.0
 */
package co.lvyi.common.exception;

/**
 * 验证码错误异常类
 */
public class CaptchaException extends UserException
{
    private static final long serialVersionUID = 1L;

    public CaptchaException()
    {
        super("user.jcaptcha.error", null);
    }
}

