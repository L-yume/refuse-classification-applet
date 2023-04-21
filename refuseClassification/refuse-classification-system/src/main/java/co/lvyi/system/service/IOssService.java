package co.lvyi.system.service;

import org.springframework.web.multipart.MultipartFile;

public interface IOssService {
    String upload(MultipartFile file);
}
