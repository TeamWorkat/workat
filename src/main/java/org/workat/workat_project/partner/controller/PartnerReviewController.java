package org.workat.workat_project.partner.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
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
//
//	@GetMapping("/reviewDetail")
//	public ResponseEntity<PartnerRoomDTO> getRoomDetail(@RequestParam(name = "room_id") int room_id){
//		return ResponseEntity.ok(roomService.getRoomDetail(room_id));
//	}
//	
//	@PostMapping("/reviewUpdate")
//	public ResponseEntity<Integer> updateRoom(@RequestBody PartnerRoomDTO request){
//		System.out.println(request);
//		return ResponseEntity.ok(roomService.updateRoom(request));
//	}
//	
//	@PostMapping("/reviewInsert")
//	public ResponseEntity<Integer> insertRoom(@RequestBody PartnerRoomDTO request){
//		System.out.println(request);
//		return ResponseEntity.ok(roomService.insertRoom(request));
//	}
//	
//	
//	
//	@GetMapping("/reviewDelete")
//	public ResponseEntity<Integer> deleteRoom(@RequestParam (name = "room_id") int room_id){
//		return ResponseEntity.ok(roomService.deleteRoom(room_id));
//	}
}
