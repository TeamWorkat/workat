package org.workat.workat_project.place.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.workat.workat_project.place.entity.PlaceListDTO;

import org.workat.workat_project.place.repository.PlaceMapper;

import lombok.RequiredArgsConstructor;

@Service("placeService")
@RequiredArgsConstructor
public class PlaceServiceImpl implements PlaceService{
	
	private final PlaceMapper placeMapper;
	
	@Override
	public List<PlaceListDTO> getMainViewPlaceList(){
		return placeMapper.getMainViewPlaceList();
	}
}
