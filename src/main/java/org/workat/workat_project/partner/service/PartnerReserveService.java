package org.workat.workat_project.partner.service;

import java.util.List;

import org.workat.workat_project.partner.entity.PartnerReserveVO;




public interface PartnerReserveService {
	List<PartnerReserveVO> getReserveList();
	PartnerReserveVO getReserveDetail(int res_id);
	List<PartnerReserveVO> getItemList(int page, int size, String search);
	Integer getItemCount(String search);
//	Integer updatePlace(PartnerPlaceDTO request);
//	Integer deletePlace(int placeid);
//	Integer insertPlace(PartnerPlaceDTO request);
}
