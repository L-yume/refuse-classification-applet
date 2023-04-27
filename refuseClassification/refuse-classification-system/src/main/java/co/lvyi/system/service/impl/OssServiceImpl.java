/**
 * @BelongsProject: refuse-classification-applet
 * @BelongsPackage: co.lvyi.system.service.impl
 * @Author: 吕易
 * @CreateTime: 2023-04-21  21:10
 * @Description: TODO
 * @Version: 1.0
 */
package co.lvyi.system.service.impl;

import it.sauronsoftware.jave.Encoder;
import co.lvyi.common.utils.FileNameUtil;
import co.lvyi.system.service.IOssService;
import com.aliyun.oss.OSSClient;
import it.sauronsoftware.jave.MultimediaInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.net.URL;
import java.nio.channels.FileChannel;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.TimeZone;

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
    public Map<String, Object> uploadVideo(MultipartFile file) {
        try {
            String objectName = ossDirPrefix2 + FileNameUtil.getImgName(file.getOriginalFilename());
            ossClient.putObject(ossBucketName, objectName, file.getInputStream());
            // 关闭OSSClient。
            //ossClient.shutdown();
            //默认十年不过期
            Date expiration = new Date(System.currentTimeMillis() + 3600L * 1000 * 24 * 365 * 10);
            String path = ossClient.generatePresignedUrl(ossBucketName, objectName, expiration).toString();

            return getVideoMsg(path.substring(0, path.indexOf("?")), file.getOriginalFilename());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private String getImgUrl(String imgName) {
        return "https://" + ossBucketName + "." + ossClient.getEndpoint().getHost()  + "/" + imgName;
    }

    public static Map<String, Object> getVideoMsg(String path, String name) {

        Map<String, Object> map = new HashMap<>();
        File file = getFileByHttpURL(path);
        Encoder encoder = new Encoder();
        FileChannel fc = null;
        String size = "";

        if (file != null) {
            try {
                MultimediaInfo m = encoder.getInfo(file);
                long duration = m.getDuration();
                //初始化Formatter的转换格式。
                SimpleDateFormat formatter = new SimpleDateFormat("HH:mm:ss");
                formatter.setTimeZone(TimeZone.getTimeZone("GMT+00:00"));
                String hms = formatter.format(duration);

                FileInputStream fis = new FileInputStream(file);
                fc = fis.getChannel();
                BigDecimal fileSize = new BigDecimal(fc.size());
                size = fileSize.divide(new BigDecimal(1048576), 2, RoundingMode.HALF_UP) + "MB";

                map.put("name", name);
                map.put("path", path);
                map.put("duration", hms);
                map.put("size", size);
                map.put("format", m.getFormat());
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                if (fc != null) {
                    try {
                        fc.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        return map;
    }

    /**
     * 根据URL地址获取文件
     * @param path URL网络地址
     * @return File
     */
    private static File getFileByHttpURL(String path){
        String newUrl = path.split("[?]")[0];
        String[] suffix = newUrl.split("/");
        //得到最后一个分隔符后的名字
        String fileName = suffix[suffix.length - 1];
        File file = null;
        InputStream inputStream = null;
        OutputStream outputStream = null;
        try{
            file = File.createTempFile("report",fileName);//创建临时文件
            URL urlFile = new URL(newUrl);
            inputStream = urlFile.openStream();
            outputStream = new FileOutputStream(file);

            int bytesRead = 0;
            byte[] buffer = new byte[8192];
            while ((bytesRead=inputStream.read(buffer,0,8192))!=-1) {
                outputStream.write(buffer, 0, bytesRead);
            }
        }catch (Exception e) {
            e.printStackTrace();
        }finally {
            try {
                if (null != outputStream) {
                    outputStream.close();
                }
                if (null != inputStream) {
                    inputStream.close();
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return file;
    }

}

