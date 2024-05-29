package org.workat.workat_project.place.controller;


import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.workat.workat_project.place.entity.PlaceListDTO;

import org.workat.workat_project.place.service.PlaceService;

import lombok.RequiredArgsConstructor;


@CrossOrigin
@RestController
@RequiredArgsConstructor
public class PlaceController {

    
    private final PlaceService placeService ;

    @GetMapping("/api/items")
    public ResponseEntity<List<PlaceListDTO>> getMainViewPlaceList() {
        return ResponseEntity.ok(placeService.getMainViewPlaceList());
    }

    @GetMapping("/placeDetail")
    public ResponseEntity<PlaceDetailDTO> getPlaceDetail(@RequestParam(name="place_id") int placeId){
        System.err.println("들어옴");
        return ResponseEntity.ok(placeService.getPlaceDetail(placeId));
    }

}