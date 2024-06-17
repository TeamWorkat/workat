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

	@Override
	public PartnerReviewDTO getReviewDetail(int review_id) {
	    PartnerReviewDTO reviewDTO = reviewMapper.getReviewDetail(review_id);
	   System.out.println(reviewDTO);

	    String pictureURLArray = reviewDTO.getPictureURLArray();
	    String[] picture_sources;
	    
	    if (pictureURLArray.equals("null")){
	    	reviewDTO.setPicture_sources(
	    			new String[]{"https://workatbucket.s3.amazonaws.com/place/ff16b058-c790-44de-9cdb-36199af0ee21.png"});
	    }else {
	    	picture_sources = pictureURLArray.split(",");
	        reviewDTO.setPicture_sources(picture_sources);
	    }
	    
	    return reviewDTO;
	}


	@Transactional
	@Override
	public Integer deleteReview(int review_id) {
		reviewMapper.deleteReview(review_id);
		reviewMapper.deleteReviewpic(review_id);
		return 1;
	}
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