package org.workat.workat_project.partner.repository;

import org.apache.ibatis.annotations.Mapper;
import org.workat.workat_project.partner.entity.PartnerPlaceDTO;
import org.workat.workat_project.partner.entity.PartnerPlaceVO;

import java.util.List;
import java.util.Map;

@Mapper
public interface PartnerPlaceMapper {
	List<PartnerPlaceVO> getPlaceList();
	PartnerPlaceDTO getPlaceDetail(int placeid);
	
	Integer updatePlace(PartnerPlaceDTO request);
	
	void allInactive(PartnerPlaceDTO request);
//	Integer updatePlacepic(PartnerPlaceDTO request);
	
//	void inactivePlace(PartnerPlaceDTO request);
	void inactivePlace(Map<String, Object>  params);
//	void updatePlacepic(PartnerPlaceDTO request);
	
	void updatePlacepic(Map<String, Object> params);
	
	Integer deletePlace(int placeid);
	Integer deletePlacepic(int placeid);
	
	Integer insertPlace(PartnerPlaceDTO request);
	Integer insertPlacepic(PartnerPlaceDTO request);

}