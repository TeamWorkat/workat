package org.workat.workat_project.partner.service;

import java.util.List;

import org.workat.workat_project.partner.entity.PartnerPlaceDTO;
import org.workat.workat_project.partner.entity.PartnerPlaceVO;
import org.workat.workat_project.partner.entity.PartnerReviewDTO;
import org.workat.workat_project.partner.entity.PartnerRoomDTO;



public interface PartnerReviewService {
	List<PartnerReviewDTO> getReviewList();
//	PartnerRoomDTO getRoomDetail(int room_id);
//	Integer updateRoom(PartnerRoomDTO request);
//	Integer deleteRoom(int room_id);
//	Integer insertRoom(PartnerRoomDTO request);
}
