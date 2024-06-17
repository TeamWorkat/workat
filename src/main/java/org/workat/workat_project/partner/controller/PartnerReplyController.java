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
import org.workat.workat_project.partner.service.PartnerReplyService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/partner")
public class PartnerReplyController {

	private final PartnerReplyService replyService;


	@PostMapping("/replyInsert")
	public ResponseEntity<Integer> insertReply(@RequestBody PartnerReplyDTO request){
		System.out.println(request);
		return ResponseEntity.ok(replyService.insertReply(request));
	}

	@GetMapping("/replyDelete")
	public ResponseEntity<Integer> deleteReply(@RequestParam (name = "review_id") int review_id){
		return ResponseEntity.ok(replyService.deleteReply(review_id));
	}
}
