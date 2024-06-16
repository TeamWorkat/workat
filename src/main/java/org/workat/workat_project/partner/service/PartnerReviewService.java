package org.workat.workat_project.partner.service;

import java.util.List;

import org.workat.workat_project.partner.entity.PartnerReviewDTO;




public interface PartnerReviewService {
	List<PartnerReviewDTO> getReviewList();
	PartnerReviewDTO getReviewDetail(int review_id);
//	Integer updateRoom(PartnerRoomDTO request);
	Integer deleteReview(int review_id);
//	Integer insertRoom(PartnerRoomDTO request);
}
