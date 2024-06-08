package org.workat.workat_project.aws.component;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Getter
public class AwsS3Component {

    @Value("${cloud.aws.s3.bucket}")
    private String bucket;

    @Value("${cloud.aws.s3.folder.folderName1}")
    private String placeFolder;

    @Value("${cloud.aws.s3.folder.folderName2}")
    private String roomFolder;

    @Value("${cloud.aws.s3.folder.folderName3}")
    private String reviewFolder;

}
