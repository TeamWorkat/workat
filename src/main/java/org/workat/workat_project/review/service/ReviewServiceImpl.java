package org.workat.workat_project.review.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.workat.workat_project.aws.service.AwsService;
import org.workat.workat_project.picture.repository.PictureMapper;
import org.workat.workat_project.place.repository.PlaceMapper;
import org.workat.workat_project.reservation.entity.ReservationVO;
import org.workat.workat_project.reservation.repository.ReservationMapper;
import org.workat.workat_project.review.entity.ReviewInsertDTO;
import org.workat.workat_project.review.entity.ReviewListDTO;
import org.workat.workat_project.review.entity.ReviewResDTO;
import org.workat.workat_project.review.entity.ReviewVO;
import org.workat.workat_project.review.repository.ReviewMapper;
import org.workat.workat_project.room.entity.RoomResDTO;
import org.workat.workat_project.room.entity.RoomVO;
import org.workat.workat_project.user.entity.UserVO;
import org.workat.workat_project.user.repository.UserMapper;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ReviewServiceImpl implements ReviewService {

    private final ReviewMapper reviewMapper;
    private final PictureMapper pictureMapper;
    private final ReservationMapper reservationMapper;
    private final UserMapper userMapper;
    private final AwsService awsService;
    private final PlaceMapper placeMapper;

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

    @Override
    public List<ReviewListDTO> userReviewList(String mail) {
        List<ReviewListDTO> reviewListDTOList = new ArrayList<>();
        List<ReviewListDTO> reviewListWithOutPicture = reviewMapper.getUserReviewList(mail);
        for(ReviewListDTO reviewListDTO : reviewListWithOutPicture){
            ReservationVO reservationVO = reservationMapper.selectReservationById(reviewListDTO.getRes_id());
            reviewListDTO.setStartDate(reservationVO.getCheck_in().toLocalDate().atTime(reviewListDTO.getPlace_in()));
            reviewListDTO.setEndDate(reservationVO.getCheck_out().toLocalDate().atTime(reviewListDTO.getPlace_out()));
            List<String> reviewPicture = pictureMapper.getReviewPictureSources(reviewListDTO.getReview_id());
            reviewListDTO.setPicture_sources(reviewPicture);
            reviewListDTOList.add(reviewListDTO);
        }
        return reviewListDTOList;
    }

    @Override
    @Transactional
    public int insertReview(String mail, ReviewInsertDTO reviewInsertDTO) {
        reviewInsertDTO.setUser_id(userMapper.findUserByEmail(mail).getUser_id());
        reviewMapper.insertUserReview(reviewInsertDTO);
        for (MultipartFile file : reviewInsertDTO.getFiles()) {
                    String uploadUrl = awsService.uploadFile(file, "review");
                    String fileSource = awsService.getFileUrl(uploadUrl);
                    String fileName = file.getOriginalFilename();
                    pictureMapper.insertUserReviewPicture(fileName,fileSource,reviewInsertDTO.getReview_id());
                }
        return 1;
    }

    @Override
    public ReviewResDTO userReviewDetail(int reviewId) {
        ReviewVO reviewVO = reviewMapper.getUserReviewInfo(reviewId);
        ReviewResDTO reviewResDTO = new ReviewResDTO();
        reviewResDTO.setReplyVO(reviewMapper.getReply(reviewId));
        reviewResDTO.setReview_picture_source(pictureMapper.getReviewPictureSources(reviewId));
        reviewResDTO.setReviewVO(reviewVO);
        reviewResDTO.setPlace_nm(placeMapper.getPlaceInfo(reviewVO.getPlace_id()).getPlace_nm());
        reviewResDTO.setCheck_in(reservationMapper.selectReservationById(reviewVO.getRes_id()).getCheck_in());
        reviewResDTO.setCheck_out(reservationMapper.selectReservationById(reviewVO.getRes_id()).getCheck_out());
        return reviewResDTO;
    }
}
