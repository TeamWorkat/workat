package org.workat.workat_project.review.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReviewVO {

    private int review_id;
    private String content;
    private LocalDateTime created_date;
    private LocalDateTime modified_date;
    private String status;
    private int rating;
    private int res_id;
    private int user_id;
    private int place_id;

}
