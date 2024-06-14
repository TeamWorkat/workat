package org.workat.workat_project.review.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.workat.workat_project.review.entity.ReviewListDTO;
import org.workat.workat_project.review.service.ReviewService;

import java.security.Principal;
import java.util.List;

@Controller
@AllArgsConstructor
@RequestMapping("/api/review")
public class ReviewController {
    private final ReviewService reviewService;

    @GetMapping("/list")
    @ResponseBody
    public ResponseEntity<List<ReviewListDTO>> userReviewList(Principal principal) {
        System.err.println("온다 리뷰");
        return ResponseEntity.ok(reviewService.userReviewList("user1@example.com"));
    }
}
