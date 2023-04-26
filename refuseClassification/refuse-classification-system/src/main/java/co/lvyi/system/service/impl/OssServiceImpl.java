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
import java.util.Date;

@Service
@Slf4j
public class OssServiceImpl implements IOssService {

    @Autowired
    private OSSClient ossClient;

    @Value("${aliyun.oss.bucketName}")
    private String ossBucketName;

    @Value("${aliyun.oss.dir.prefix}")
    private String ossDirPrefix;

    @Value("${aliyun.oss.dir.prefix2}")
    private String ossDirPrefix2;

    @Override
    public String upload(MultipartFile file) {
        try {
            // codingmore/images/ + 2022年06月09日 + UUID + .jpg
            String objectName = ossDirPrefix + FileNameUtil.getImgName(file.getOriginalFilename());
            // 创建PutObject请求。
            ossClient.putObject(ossBucketName, objectName, file.getInputStream());
            //ossClient.shutdown();
            return getImgUrl(objectName);
        } catch (IOException e) {
            log.error(e.getMessage());
        }
        return null;
    }

    @Override
    public String uploadVideo(MultipartFile file) {
        try {
            String objectName = ossDirPrefix2 + FileNameUtil.getImgName(file.getOriginalFilename());
            ossClient.putObject(ossBucketName, objectName, file.getInputStream());
            // 关闭OSSClient。
            //ossClient.shutdown();
            //默认十年不过期
            Date expiration = new Date(System.currentTimeMillis() + 3600L * 1000 * 24 * 365 * 10);
            String path = ossClient.generatePresignedUrl(ossBucketName, objectName, expiration).toString();
            return path.substring(0, path.indexOf("?"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private String getImgUrl(String imgName) {
        return "https://" + ossBucketName + "." + ossClient.getEndpoint().getHost()  + "/" + imgName;
    }
}

