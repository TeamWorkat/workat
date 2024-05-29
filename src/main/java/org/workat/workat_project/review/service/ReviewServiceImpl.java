package org.workat.workat_project.review.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.workat.workat_project.picture.repository.PictureMapper;
import org.workat.workat_project.review.entity.ReviewResDTO;
import org.workat.workat_project.review.entity.ReviewVO;
import org.workat.workat_project.review.repository.ReviewMapper;
import org.workat.workat_project.room.entity.RoomResDTO;
import org.workat.workat_project.room.entity.RoomVO;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ReviewServiceImpl implements ReviewService {

    private final ReviewMapper reviewMapper;
    private final PictureMapper pictureMapper;

    @Override
    public List<ReviewResDTO> getReviewInfoList(int placeId){
        List<ReviewResDTO> reviewResDTOList = new ArrayList<>();
        List<ReviewVO> reviewVOList = reviewMapper.getReviewInfo(placeId);
        for(ReviewVO reviewVO : reviewVOList){
            ReviewResDTO reviewResDTO = new ReviewResDTO();
            reviewResDTO.setReviewVO(reviewVO);
            reviewResDTO.setReview_picture_source(pictureMapper.getReviewPictureSources(reviewVO.getReview_id()));
            reviewResDTO.setReplyVO(reviewMapper.getReply(reviewVO.getReview_id()));
            reviewResDTOList.add(reviewResDTO);
        }
        return reviewResDTOList;
    }
}
