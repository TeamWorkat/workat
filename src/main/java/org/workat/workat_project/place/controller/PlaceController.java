package org.workat.workat_project.place.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.workat.workat_project.place.entity.PlaceDetailDTO;
import org.workat.workat_project.place.entity.PlaceListDTO;
import org.workat.workat_project.place.entity.SearchVO;
import org.workat.workat_project.place.service.PlaceService;

import lombok.RequiredArgsConstructor;

@CrossOrigin
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/place")
public class PlaceController {

	private final PlaceService placeService;

	@GetMapping("/items")
	public ResponseEntity<List<PlaceListDTO>> getMainViewPlaceList() {
		return ResponseEntity.ok(placeService.getMainViewPlaceList());
	}

	@GetMapping("/placeDetail")
	public ResponseEntity<PlaceDetailDTO> getPlaceDetail(@RequestParam(name = "place_id") int placeId) {
		return ResponseEntity.ok(placeService.getPlaceDetail(placeId));
	}

	@PostMapping("/search")
	public ResponseEntity<List<PlaceListDTO>> getSearchPlaceList(@RequestBody SearchVO request) {

		System.out.println(request + "!!!!!!!!");

		return ResponseEntity.ok(placeService.getSearchPlaceList(request));
	}

	@GetMapping("/category")
	public ResponseEntity<List<PlaceListDTO>> getCategoryViewPlaceList(@RequestParam(name = "category") String category) {
		System.out.println(category);
		return ResponseEntity.ok(placeService.getCategoryViewPlaceList(category));
	}

}