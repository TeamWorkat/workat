package org.workat.workat_project.partner.repository;

import org.apache.ibatis.annotations.Mapper;
import org.workat.workat_project.partner.entity.PartnerPlaceDTO;
import org.workat.workat_project.partner.entity.PartnerPlaceVO;

import java.util.List;

@Mapper
public interface PartnerPlaceMapper {
	List<PartnerPlaceVO> getPlaceList();
	PartnerPlaceDTO getPlaceDetail(int placeid);
	
	Integer updatePlace(PartnerPlaceDTO request);
	
	Integer updatePlacepic(PartnerPlaceDTO request);
	
	void inactivePlace(PartnerPlaceDTO request);
	
	Integer deletePlace(int placeid);
	Integer deletePlacepic(int placeid);
	
	Integer insertPlace(PartnerPlaceDTO request);
	Integer insertPlacepic(PartnerPlaceDTO request);

}