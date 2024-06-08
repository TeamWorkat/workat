package org.workat.workat_project.review.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ReviewListDTO {
    private int review_id;
    private int res_id;
    private int rating;
    private String user_nm;
    private String place_nm;
    private String placeCategory;
    private LocalDateTime reviewCreatedDate;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private LocalTime place_in;
    private LocalTime place_out;
    private List<String> picture_sources;
}
