package org.workat.workat_project.place.service;

import java.util.List;

import org.workat.workat_project.place.entity.PlaceDetailDTO;
import org.workat.workat_project.place.entity.PlaceListDTO;
import org.workat.workat_project.place.entity.SearchVO;


public interface PlaceService {

	List<PlaceListDTO> getMainViewPlaceList();

	PlaceDetailDTO getPlaceDetail(int placeId);
	
	List<PlaceListDTO> getSearchPlaceList(SearchVO request);
	
	List<PlaceListDTO> getCategoryViewPlaceList(String category);

}
