package org.workat.workat_project.review.service;

import org.workat.workat_project.review.entity.ReviewInsertDTO;
import org.workat.workat_project.review.entity.ReviewListDTO;
import org.workat.workat_project.review.entity.ReviewResDTO;

import java.util.List;

public interface ReviewService {
    List<ReviewResDTO> getReviewInfoList(int placeId);

    List<ReviewListDTO> userReviewList(String mail);

    int insertReview(String mail, ReviewInsertDTO reviewInsertDTO);
}
