package org.workat.workat_project.partner.repository;

import org.apache.ibatis.annotations.Mapper;
import org.workat.workat_project.partner.entity.PartnerReplyDTO;


@Mapper
public interface PartnerReplyMapper {
	Integer updateReplyExistN(int review_id);
	Integer deleteReply(int review_id);
	Integer insertReply(PartnerReplyDTO request);
	Integer updateReplyexist(PartnerReplyDTO request);


}