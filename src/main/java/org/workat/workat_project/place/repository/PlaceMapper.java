package org.workat.workat_project.place.repository;



import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import org.workat.workat_project.place.entity.PlaceListDTO;

import java.util.List;

@Mapper
@Repository("placeMapper")
public interface PlaceMapper {

    List<PlaceListDTO> getMainViewPlaceList();
}