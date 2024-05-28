package org.workat.workat_project.review.entity;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
public class ReviewResDTO {

    private ReviewVO reviewVO;
    private ReplyVO replyVO;
    private List<String> review_picture_source;

}
