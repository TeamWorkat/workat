package org.workat.workat_project.review.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ReviewInsertDTO {
    private int res_id;
    private int rating;
    private String content;
    private int place_id;
    private List<MultipartFile> files;
    private int user_id;
    private int review_id;
    private List<String> src;

}
