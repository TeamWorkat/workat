package org.workat.workat_project.partner.service;

import java.util.List;

import org.workat.workat_project.partner.entity.PartnerPlaceDTO;
import org.workat.workat_project.partner.entity.PartnerPlaceVO;
import org.workat.workat_project.partner.entity.PartnerRoomDTO;



public interface PartnerRoomService {
	List<PartnerRoomDTO> getRoomList();
	PartnerRoomDTO getRoomDetail(int room_id);
	Integer updateRoom(PartnerRoomDTO request);
	Integer deleteRoom(int room_id);
	Integer insertRoom(PartnerRoomDTO request);
}
