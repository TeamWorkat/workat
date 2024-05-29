package org.workat.workat_project.review.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReplyVO {
    private int review_id;
    private int res_id;
    private String reply_content;
    private LocalDateTime created_date;
    private LocalDateTime modified_date;
    private String status;
}
