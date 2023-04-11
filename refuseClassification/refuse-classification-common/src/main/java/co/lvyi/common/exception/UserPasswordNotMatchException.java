/**
 * @BelongsProject: refuse-classification-applet
 * @BelongsPackage: co.lvyi.common.exception
 * @Author: 吕易
 * @CreateTime: 2023-04-08  14:57
 * @Description: TODO
 * @Version: 1.0
 */
package co.lvyi.common.exception;

/**
 * 用户密码不正确或不符合规范异常类
 */
public class UserPasswordNotMatchException extends UserException
{
    private static final long serialVersionUID = 1L;

    public UserPasswordNotMatchException()
    {
        super("user.password.not.match", null);
    }
}

