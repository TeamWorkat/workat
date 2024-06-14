package org.workat.workat_project.review.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.workat.workat_project.aws.service.AwsService;
import org.workat.workat_project.review.entity.ReviewInsertDTO;
import org.workat.workat_project.review.entity.ReviewListDTO;
import org.workat.workat_project.review.entity.ReviewResDTO;
import org.workat.workat_project.review.service.ReviewService;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@Controller
@AllArgsConstructor
@RequestMapping("/api/review")
public class ReviewController {
    private final ReviewService reviewService;
    private final AwsService awsService;

    @GetMapping("/list")
    @ResponseBody
    public ResponseEntity<List<ReviewListDTO>> userReviewList(Principal principal) {
        return ResponseEntity.ok(reviewService.userReviewList("user1@example.com"));
    }

    @PostMapping("/insert")
    @ResponseBody
    public ResponseEntity<Integer> insertUserReview(Principal principal, ReviewInsertDTO reviewDTO) {
        reviewService.insertReview("user1@example.com", reviewDTO);
        return ResponseEntity.ok(reviewDTO.getReview_id());
    }

    @GetMapping("/detail")
    @ResponseBody
    public ResponseEntity<ReviewResDTO> userReviewDetail(@RequestParam(name = "review_id") int reviewId) {
        System.err.println("reviewId: " + reviewId);
        return ResponseEntity.ok(reviewService.userReviewDetail(reviewId));
    }
}
