package org.workat.workat_project.place.controller;


import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.workat.workat_project.place.entity.PlaceDetailDTO;
import org.workat.workat_project.place.entity.PlaceListDTO;

import org.workat.workat_project.place.service.PlaceService;

import lombok.RequiredArgsConstructor;


@CrossOrigin
@Controller
@RequiredArgsConstructor
@RequestMapping("/place")
public class PlaceController {

    
    private final PlaceService placeService ;

    @GetMapping("/items")
    public ResponseEntity<List<PlaceListDTO>> getMainViewPlaceList() {
        return ResponseEntity.ok(placeService.getMainViewPlaceList());
    }

    @GetMapping("/placeDetail")
    public ResponseEntity<PlaceDetailDTO> getPlaceDetail(@RequestParam(name="place_id") int placeId){
        System.err.println("들어옴");
        return ResponseEntity.ok(placeService.getPlaceDetail(placeId));
    }

}