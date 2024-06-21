package org.workat.workat_project.aws;

import lombok.RequiredArgsConstructor;
//import net.coobird.thumbnailator.Thumbnails;
//
//import org.apache.tomcat.util.http.fileupload.FileItem;
//import org.apache.tomcat.util.http.fileupload.disk.DiskFileItem;
import org.springframework.http.ResponseEntity;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.workat.workat_project.aws.service.AwsService;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Controller
@RequiredArgsConstructor
public class TestController {

    private final AwsService awsService;

    @PostMapping("/test/aws")
    public ResponseEntity<List<String>> testAws(@RequestParam(name = "files") List<MultipartFile> files, @RequestParam(name = "fileFolder") String fileFolder) {
        List<String> answer = new ArrayList<>();
        files.forEach(file -> {
            
//                String realURL = awsService.uploadFile(file, fileFolder);
                
//                BufferedImage originalImage = ImageIO.read(new URL(realURL));
//
//                BufferedImage thumbnail = Thumbnails.of(originalImage)
//                        .size(200, 200)
//                        .asBufferedImage();
//
//                
//                MultipartFile thumbnailFile = convertBufferedImageToMultipartFile(thumbnail, realURL + "thumbnail.png");
//                
                String url = awsService.uploadFile(file, fileFolder);
                answer.add(awsService.getFileUrl(url));
        });
        return ResponseEntity.ok(answer);
    }
    
//    public static MultipartFile convertBufferedImageToMultipartFile(BufferedImage image, String fileName) throws IOException {
//        ByteArrayOutputStream os = new ByteArrayOutputStream();
//        ImageIO.write(image, "png", os);
//        ByteArrayInputStream input = new ByteArrayInputStream(os.toByteArray());
//        return new MockMultipartFile(fileName, fileName, "image/png", input);
//    }


}
