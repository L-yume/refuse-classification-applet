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
 * 用户不存在异常类
 */
public class UserNotExistsException extends UserException
{
    private static final long serialVersionUID = 1L;

    public UserNotExistsException()
    {
        super("user.not.exists", null);
    }
}

