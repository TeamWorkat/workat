package org.workat.workat_project.partner.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.workat.workat_project.partner.entity.PartnerReplyDTO;
import org.workat.workat_project.partner.entity.PartnerReviewDTO;
import org.workat.workat_project.partner.entity.PartnerRoomDTO;
import org.workat.workat_project.partner.repository.PartnerReplyMapper;
import org.workat.workat_project.partner.repository.PartnerReviewMapper;
import org.workat.workat_project.partner.repository.PartnerRoomMapper;
import lombok.RequiredArgsConstructor;



@Service
@RequiredArgsConstructor
public class PartnerReplyServiceImpl implements PartnerReplyService {
	private final PartnerReplyMapper replyMapper;


	
	@Transactional
	@Override
	public Integer deleteReply(int review_id) {
		replyMapper.updateReplyExistN(review_id);
		replyMapper.deleteReply(review_id);
		return 1;
	}


	@Transactional
	@Override
	public Integer insertReply(PartnerReplyDTO request) {
		replyMapper.insertReply(request);	
		replyMapper.updateReplyexist(request);
		return 1;
	}
}