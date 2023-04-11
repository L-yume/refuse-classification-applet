/**
 * @BelongsProject: refuse-classification-applet
 * @BelongsPackage: co.lvyi.common.exception
 * @Author: 吕易
 * @CreateTime: 2023-04-08  14:56
 * @Description: TODO
 * @Version: 1.0
 */
package co.lvyi.common.exception;

/**
 * 验证码失效异常类
 */
public class CaptchaExpireException extends UserException
{
    private static final long serialVersionUID = 1L;

    public CaptchaExpireException()
    {
        super("user.jcaptcha.expire", null);
    }
}

