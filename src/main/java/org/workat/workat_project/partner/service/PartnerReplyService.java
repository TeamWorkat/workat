package org.workat.workat_project.partner.service;



import org.workat.workat_project.partner.entity.PartnerReplyDTO;





public interface PartnerReplyService {
	
	Integer deleteReply(int review_id);
	Integer insertReply(PartnerReplyDTO request);
}
