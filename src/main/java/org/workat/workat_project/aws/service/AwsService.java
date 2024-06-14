package org.workat.workat_project.aws.service;

    import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;

public interface AwsService {
    String getFileFolder(String fileFolder);

    String uploadFile(MultipartFile files, String fileFolder);

    String getFileUrl(String fileName);

    void deleteFile(String fileName);
}
