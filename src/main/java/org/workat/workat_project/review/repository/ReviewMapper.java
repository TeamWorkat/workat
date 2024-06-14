package org.workat.workat_project.review.repository;

import org.apache.ibatis.annotations.Mapper;
import org.workat.workat_project.review.entity.*;

import java.util.List;
import java.util.Map;

@Mapper
public interface ReviewMapper {
    List<ReviewVO> getReviewInfo(int placeId);

    ReplyVO getReply(int reviewId);

    List<StarPointDTO> getStarPointEach(int placeId);

    List<ReviewListDTO> getUserReviewList(String mail);

    int insertUserReview(ReviewInsertDTO reviewInsertDTO);

    ReviewVO getUserReviewInfo(int reviewId);

}
