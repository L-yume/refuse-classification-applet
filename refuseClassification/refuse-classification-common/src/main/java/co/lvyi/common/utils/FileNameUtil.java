/**
 * @BelongsProject: refuse-classification-applet
 * @BelongsPackage: co.lvyi.common.utils
 * @Author: 吕易
 * @CreateTime: 2023-04-21  21:21
 * @Description: TODO
 * @Version: 1.0
 */
package co.lvyi.common.utils;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.lang.UUID;

public class FileNameUtil {
    private static final String[] imageExtension = {".jpg", ".jpeg", ".png", ".gif",".mp4"};

    public static String getImgName(String url) {
        String ext = "";
        for (String extItem : imageExtension) {
            if (url.indexOf(extItem) != -1) {
                ext = extItem;
                break;
            }
        }
        // 2022年06月09日 + UUID + .jpg
        return  DateUtil.today() + UUID.fastUUID() + ext;
    }
}

