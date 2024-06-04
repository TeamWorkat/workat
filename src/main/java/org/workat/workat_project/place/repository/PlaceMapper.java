package org.workat.workat_project.place.repository;



import org.apache.ibatis.annotations.Mapper;
import org.workat.workat_project.place.entity.PlaceListDTO;
import org.workat.workat_project.place.entity.PlaceVO;
import org.workat.workat_project.place.entity.SearchVO;

import java.util.List;

@Mapper
public interface PlaceMapper {

    List<PlaceListDTO> getMainViewPlaceList();

    PlaceVO getPlaceInfo(int placeId);
    
    List<PlaceListDTO> getSearchPlaceList(SearchVO request);
}