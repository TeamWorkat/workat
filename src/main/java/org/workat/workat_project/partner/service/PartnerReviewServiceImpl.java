package org.workat.workat_project.partner.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.workat.workat_project.partner.entity.PartnerReviewDTO;
import org.workat.workat_project.partner.entity.PartnerRoomDTO;
import org.workat.workat_project.partner.repository.PartnerReviewMapper;
import org.workat.workat_project.partner.repository.PartnerRoomMapper;
import lombok.RequiredArgsConstructor;



@Service
@RequiredArgsConstructor
public class PartnerReviewServiceImpl implements PartnerReviewService {
	private final PartnerReviewMapper reviewMapper;


	@Override
	public List<PartnerReviewDTO> getReviewList() {
		return reviewMapper.getReviewList();
	}

//	@Override
//	public PartnerRoomDTO getRoomDetail(int room_id) {
//		PartnerRoomDTO roomDTO = roomMapper.getRoomDetail(room_id);
//		String[] picture_sources = roomDTO.getPictureURLArray().split(",");
//		roomDTO.setPicture_sources(picture_sources);
//		return roomDTO;
//	}
//
//	@Transactional
//	@Override
//	public Integer updateRoom(PartnerRoomDTO request) {
//		roomMapper.updateRoom(request);
//		roomMapper.allInactive(request);
//
//		for (String pic : request.getPicture_sources()) {
//			Map<String, Object> params = new HashMap<>();
//			params.put("picture_source", pic);
//			System.out.println(pic);
//			params.put("room_id", request.getRoom_id());
//			roomMapper.updateRoompic(params);
//			roomMapper.inactiveRoom(params);
//		}
//		return 1;
//	}
//	
//	@Transactional
//	@Override
//	public Integer deleteRoom(int room_id) {
//		roomMapper.deleteRoom(room_id);
//		roomMapper.deleteRoompic(room_id);
//		return 1;
//	}
//
//
//	@Transactional
//	@Override
//	public Integer insertRoom(PartnerRoomDTO request) {
//
//		roomMapper.insertRoom(request);
//
//		System.out.println(request.getRoom_id());
//		
//		
//		for (String pic : request.getPicture_sources()) {
//			Map<String, Object> params = new HashMap<>();
//			params.put("picture_source", pic);
//			System.out.println(pic);
//			params.put("room_id", request.getRoom_id());
//			roomMapper.insertRoompic(params);		
//		}
//		return 1;
//	}
}