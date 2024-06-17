package org.workat.workat_project.partner.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.workat.workat_project.partner.entity.PartnerReplyDTO;
import org.workat.workat_project.partner.entity.PartnerReviewDTO;

import org.workat.workat_project.partner.service.PartnerReviewService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/partner")
public class PartnerReviewController {

	private final PartnerReviewService reviewService;

	@GetMapping("/reviewlist")
	public ResponseEntity<List<PartnerReviewDTO>> getReviewList() {
		return ResponseEntity.ok(reviewService.getReviewList());
	}

	@GetMapping("/reviewDetail")
	public ResponseEntity<PartnerReviewDTO> getReviewDetail(@RequestParam(name = "review_id") int review_id){
		return ResponseEntity.ok(reviewService.getReviewDetail(review_id));
	}


	@GetMapping("/reviewDelete")
	public ResponseEntity<Integer> deleteReview(@RequestParam (name = "review_id") int review_id){
		return ResponseEntity.ok(reviewService.deleteReview(review_id));
	}
}
