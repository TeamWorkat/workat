package org.workat.workat_project.partner.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.workat.workat_project.partner.entity.PartnerPlaceVO;
import org.workat.workat_project.partner.service.PartnerPlaceService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/partner")
public class PartnerPlaceController {
	private final PartnerPlaceService placeService;
	@GetMapping("/placelist")
	public ResponseEntity<List<PartnerPlaceVO>> getPlaceList(){
		return ResponseEntity.ok(placeService.getPlaceList());
	}
}
