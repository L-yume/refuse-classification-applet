package co.lvyi.system.service;

import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

public interface IOssService {
    String upload(MultipartFile file);

    Map<String, Object> uploadVideo(MultipartFile file);

    boolean needUpload(String imageUrl);

    String upload(String url);
}
