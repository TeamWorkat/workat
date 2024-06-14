package org.workat.workat_project.partner.repository;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.workat.workat_project.partner.entity.PartnerPlaceDTO;
import org.workat.workat_project.partner.entity.PartnerPlaceVO;
import org.workat.workat_project.partner.entity.PartnerReserveVO;

import java.util.List;

@Mapper
public interface PartnerReserveMapper {
	List<PartnerReserveVO> getReserveList();
	PartnerReserveVO getReserveDetail(int res_id);
	
	
	List<PartnerReserveVO> findAll(@Param("offset") int offset, @Param("limit") int limit);
	List<PartnerReserveVO> findByNameContaining(@Param("search") String search, @Param("offset") int offset, @Param("limit") int limit);
	int count();
	int countByNameContaining(@Param("search") String search);
	
//	Integer updatePlace(PartnerPlaceDTO request);
//	Integer updatePlaceloc(PartnerPlaceDTO request);
//	Integer updatePlacepic(PartnerPlaceDTO request);
//	
//	Integer deletePlace(int placeid);
//	Integer deletePlacepic(int placeid);
//	
//	Integer insertPlace(PartnerPlaceDTO request);
//	Integer insertPlacepic(PartnerPlaceDTO request);

}