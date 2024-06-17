package org.workat.workat_project.review.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.workat.workat_project.picture.repository.PictureMapper;
import org.workat.workat_project.review.entity.ReviewInsertDTO;
import org.workat.workat_project.review.entity.ReviewListDTO;
import org.workat.workat_project.review.entity.ReviewResDTO;
import org.workat.workat_project.review.service.ReviewService;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/review")
public class ReviewController {
    private final ReviewService reviewService;
    private final PictureMapper pictureMapper;

    @GetMapping("/list")
    public ResponseEntity<List<ReviewListDTO>> userReviewList(Authentication principal) {
        return ResponseEntity.ok(reviewService.userReviewList(principal.getName()));
    }

    @PostMapping("/insert")
    public ResponseEntity<Integer> insertUserReview(Authentication principal, ReviewInsertDTO reviewDTO) {
        reviewService.insertReview(principal.getName(), reviewDTO);
        return ResponseEntity.ok(reviewDTO.getReview_id());
    }

    @GetMapping("/detail")
    public ResponseEntity<ReviewResDTO> userReviewDetail(@RequestParam(name = "review_id") int reviewId) {
        return ResponseEntity.ok(reviewService.userReviewDetail(reviewId));
    }

    @PostMapping("/update")
    public ResponseEntity<Integer> updateUserReview(Authentication principal, ReviewInsertDTO reviewDTO) {
        ReviewInsertDTO reviewInsertDTO = reviewService.updateUserReview(principal.getName(),reviewDTO);
        return ResponseEntity.ok(reviewInsertDTO.getReview_id());
    }

    @GetMapping("/delete")
    public ResponseEntity<Integer> deleteUserReview(@RequestParam(name = "review_id") int reviewId) {
        pictureMapper.deleteReviewPicture(reviewId);
        reviewService.deleteUserReview(reviewId);
        return ResponseEntity.ok(reviewId);
    }
}
