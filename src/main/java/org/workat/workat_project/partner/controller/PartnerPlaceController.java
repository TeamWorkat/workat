package org.workat.workat_project.partner.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.workat.workat_project.partner.entity.PartnerPlaceDTO;
import org.workat.workat_project.partner.entity.PartnerPlaceVO;
import org.workat.workat_project.partner.service.PartnerPlaceService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/partner")
public class PartnerPlaceController {
	
	private final PartnerPlaceService placeService;
	
	@GetMapping("/placelist")
	public ResponseEntity<List<PartnerPlaceVO>> getPlaceList(){
		return ResponseEntity.ok(placeService.getPlaceList());
	}
	
	@GetMapping("/placeDetail")
	public ResponseEntity<PartnerPlaceDTO> getPlaceDetail(@RequestParam(name = "placeid") int placeid){
		return ResponseEntity.ok(placeService.getPlaceDetail(placeid));
	}
	
	@PostMapping("/placeUpdate")
	public ResponseEntity<Integer> updatePlace(@RequestBody PartnerPlaceDTO request){
		System.out.println(request);
		return ResponseEntity.ok(placeService.updatePlace(request));
	}
	
	@PostMapping("/placeInsert")
	public ResponseEntity<Integer> insertPlace(@RequestBody PartnerPlaceDTO request){
		System.out.println(request);
		return ResponseEntity.ok(placeService.insertPlace(request));
	}
	
	@GetMapping("/placeDelete")
	public ResponseEntity<Integer> deletePlace(@RequestParam (name = "placeid") int placeid){
		return ResponseEntity.ok(placeService.deletePlace(placeid));
	}
}
