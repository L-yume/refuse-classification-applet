/**
 * @BelongsProject: refuse-classification-applet
 * @BelongsPackage: co.lvyi.common.utils
 * @Author: 吕易
 * @CreateTime: 2023-04-17  14:20
 * @Description: TODO
 * @Version: 1.0
 */
package co.lvyi.common.utils;

import co.lvyi.bean.admin.entity.SysDictData;
import co.lvyi.common.constant.CacheConstants;
import co.lvyi.common.redis.RedisCache;
import com.alibaba.fastjson2.JSONArray;

import java.util.List;

/**
 * 字典工具类
 */
public class DictUtils {

    /**
     * 设置字典缓存
     *
     * @param key 参数键
     * @param dictDatas 字典数据列表
     */
    public static void setDictCache(String key, List<SysDictData> dictDatas)
    {
        SpringUtils.getBean(RedisCache.class).setCacheObject(getCacheKey(key), dictDatas);
    }

    /**
     * 获取字典缓存
     *
     * @param key 参数键
     * @return dictDatas 字典数据列表
     */
    public static List<SysDictData> getDictCache(String key)
    {
        JSONArray arrayCache = SpringUtils.getBean(RedisCache.class).getCacheObject(getCacheKey(key));
        if (StringUtils.isNotNull(arrayCache))
        {
            return arrayCache.toList(SysDictData.class);
        }
        return null;
    }

    /**
     * 设置cache key
     *
     * @param configKey 参数键
     * @return 缓存键key
     */
    public static String getCacheKey(String configKey)
    {
        return CacheConstants.SYS_DICT_KEY + configKey;
    }
}

