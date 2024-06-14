package org.workat.workat_project.aws;

import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.workat.workat_project.aws.service.AwsService;

@Controller
@RequiredArgsConstructor
public class TestController {

    private final AwsService awsService;

    @PostMapping("/test/aws")
    public ResponseEntity<List<String>> testAws(@RequestParam(name = "files") List<MultipartFile> files, @RequestParam(name = "fileFolder") String fileFolder) {
       
    	 List<String> answer = new ArrayList<>();
            files.forEach(file -> {
            	System.out.println(file);
                String url = awsService.uploadFile(file, fileFolder);
//                System.err.println(awsService.getFileUrl(url));
                answer.add(awsService.getFileUrl(url));
            });
            return ResponseEntity.ok(answer);
        
    }

}
