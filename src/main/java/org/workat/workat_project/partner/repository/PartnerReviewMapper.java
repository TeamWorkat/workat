package org.workat.workat_project.partner.repository;

import org.apache.ibatis.annotations.Mapper;
import org.workat.workat_project.partner.entity.PartnerReviewDTO;
import org.workat.workat_project.partner.entity.PartnerRoomDTO;

import java.util.List;
import java.util.Map;

@Mapper
public interface PartnerReviewMapper {
	List<PartnerReviewDTO> getReviewList();
//	PartnerRoomDTO getRoomDetail(int room_id);
//	
//	Integer updateRoom(PartnerRoomDTO request);
//	void allInactive(PartnerRoomDTO request);
//	void inactiveRoom(Map<String, Object>  params);
//	void updateRoompic(Map<String, Object> params);
//	
//	Integer deleteRoom(int room_id);
//	Integer deleteRoompic(int room_id);
//
//	Integer insertRoom(PartnerRoomDTO request);
//	Integer insertRoompic(Map<String, Object>  params);

}