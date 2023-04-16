/**
 * @BelongsProject: refuse-classification-applet
 * @BelongsPackage: co.lvyi.common.controller
 * @Author: 吕易
 * @CreateTime: 2023-04-16  15:43
 * @Description: TODO
 * @Version: 1.0
 */
package co.lvyi.common.controller;

import co.lvyi.bean.admin.entity.LoginUser;
import co.lvyi.common.constant.RespondCode;
import co.lvyi.common.page.PageDomain;
import co.lvyi.common.page.TableDataInfo;
import co.lvyi.common.page.TableSupport;
import co.lvyi.common.restful.JsonResult;
import co.lvyi.common.utils.*;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;

import java.beans.PropertyEditorSupport;
import java.util.Date;
import java.util.List;

/**
 * web层通用数据处理
 */
public class BaseController
{
    protected final Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     * 将前台传递过来的日期格式的字符串，自动转化为Date类型
     */
    @InitBinder
    public void initBinder(WebDataBinder binder)
    {
        // Date 类型转换
        binder.registerCustomEditor(Date.class, new PropertyEditorSupport()
        {
            @Override
            public void setAsText(String text)
            {
                setValue(DateUtils.parseDate(text));
            }
        });
    }

    /**
     * 设置请求分页数据
     */
    protected void startPage()
    {
        PageUtils.startPage();
    }

    /**
     * 设置请求排序数据
     */
    protected void startOrderBy()
    {
        PageDomain pageDomain = TableSupport.buildPageRequest();
        if (StringUtils.isNotEmpty(pageDomain.getOrderBy()))
        {
            String orderBy = SqlUtil.escapeOrderBySql(pageDomain.getOrderBy());
            PageHelper.orderBy(orderBy);
        }
    }

    /**
     * 清理分页的线程变量
     */
    protected void clearPage()
    {
        PageUtils.clearPage();
    }

    /**
     * 响应请求分页数据
     */
    @SuppressWarnings({ "rawtypes", "unchecked" })
    protected TableDataInfo getDataTable(List<?> list)
    {
        TableDataInfo rspData = new TableDataInfo();
        rspData.setCode(RespondCode.SUCCESS);
        rspData.setMsg("查询成功");
        rspData.setRows(list);
        rspData.setTotal(new PageInfo(list).getTotal());
        return rspData;
    }

    /**
     * 返回成功
     */
    public JsonResult success()
    {
        return JsonResult.success();
    }

    /**
     * 返回失败消息
     */
    public JsonResult error()
    {
        return JsonResult.error();
    }

    /**
     * 返回成功消息
     */
    public JsonResult success(String message)
    {
        return JsonResult.success(message);
    }

    /**
     * 返回成功消息
     */
    public JsonResult success(Object data)
    {
        return JsonResult.success(data);
    }

    /**
     * 返回失败消息
     */
    public JsonResult error(String message)
    {
        return JsonResult.error(message);
    }

    /**
     * 返回警告消息
     */
    public JsonResult warn(String message)
    {
        return JsonResult.warn(message);
    }

    /**
     * 响应返回结果
     *
     * @param rows 影响行数
     * @return 操作结果
     */
    protected JsonResult toAjax(int rows)
    {
        return rows > 0 ? JsonResult.success() : JsonResult.error();
    }

    /**
     * 响应返回结果
     *
     * @param result 结果
     * @return 操作结果
     */
    protected JsonResult toAjax(boolean result)
    {
        return result ? success() : error();
    }

    /**
     * 页面跳转
     */
    public String redirect(String url)
    {
        return StringUtils.format("redirect:{}", url);
    }

    /**
     * 获取用户缓存信息
     */
    public LoginUser getLoginUser()
    {
        return SecurityUtils.getLoginUser();
    }

    /**
     * 获取登录用户id
     */
    public Long getUserId()
    {
        return getLoginUser().getUserId();
    }


    /**
     * 获取登录用户名
     */
    public String getUsername()
    {
        return getLoginUser().getUsername();
    }
}

