/**
 * @BelongsProject: refuse-classification-applet
 * @BelongsPackage: co.lvyi.system.service.impl
 * @Author: 吕易
 * @CreateTime: 2023-04-21  21:10
 * @Description: TODO
 * @Version: 1.0
 */
package co.lvyi.system.service.impl;

import co.lvyi.common.utils.FileNameUtil;
import co.lvyi.system.service.IOssService;
import com.aliyun.oss.OSSClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
@Slf4j
public class OssServiceImpl implements IOssService {

    @Autowired
    private OSSClient ossClient;

    @Value("${aliyun.oss.bucketName}")
    private String ossBucketName;

    @Value("${aliyun.oss.dir.prefix}")
    private String ossDirPrefix;

    @Override
    public String upload(MultipartFile file) {
        try {
            String objectName = ossDirPrefix + FileNameUtil.getImgName(file.getOriginalFilename());
            // 创建PutObject请求。
            ossClient.putObject(ossBucketName, objectName, file.getInputStream());
            return getImgUrl(objectName);
        } catch (IOException e) {
            log.error(e.getMessage());
        }
        return null;
    }

    private String getImgUrl(String imgName) {
        return "https://" + ossBucketName + "." + ossClient.getEndpoint().getHost()  + "/" + imgName;
    }
}

