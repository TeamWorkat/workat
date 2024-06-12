package org.workat.workat_project.partner.service;

import java.util.List;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.workat.workat_project.partner.entity.PartnerReserveVO;
import org.workat.workat_project.partner.repository.PartnerReserveMapper;
import org.workat.workat_project.payment.repository.PaymentMapper;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;


@Slf4j
@Service
@RequiredArgsConstructor
public class PartnerReserveServiceImpl implements PartnerReserveService {
	private final PartnerReserveMapper reserveMapper;
	private final PaymentMapper paymentMapper;

	private static final Logger logger = LoggerFactory.getLogger(PartnerReserveServiceImpl.class);
	
	@Override
	public List<PartnerReserveVO> getReserveList() {
		return reserveMapper.getReserveList();
	}

	@Override
	public PartnerReserveVO getReserveDetail(int res_id) {
		PartnerReserveVO partnerReserveVO = reserveMapper.getReserveDetail(res_id);
		partnerReserveVO.setPaymentKey(paymentMapper.findPaymentKeyByReservationId(res_id));
		
		return partnerReserveVO;
	}

	@Override
	public List<PartnerReserveVO> getItemList(int page, int size, String search) {
		int offset = page * size;
		if(search != null && !search.isEmpty()) { // search 가 있을 경우
			return reserveMapper.findByNameContaining(search, offset, size);
		}
		
		return reserveMapper.findAll(offset,size); //아닐경우 다 찾아라
	}

	@Override
	public Integer getItemCount(String search) {
		if (search != null && !search.isEmpty()) { //아이템 개수를 정하고 search가 있을 경우
			return reserveMapper.countByNameContaining(search);
		}
		
		return reserveMapper.count(); //아닐경우 다 찾아라
	}

	
	
	
	
//	@Transactional
//	@Override
//	public Integer updatePlace(PartnerPlaceDTO request) {
//		logger.debug("aaa");
////		int updateCount = 0;
////		updateCount += placeMapper.updatePlace(request);
////		updateCount += placeMapper.updatePlaceloc(request);
////		updateCount += placeMapper.updatePlacepic(request);
//		
//		
//		placeMapper.updatePlace(request);
////		placeMapper.updatePlaceloc(request);
//		placeMapper.updatePlacepic(request);
//		
//		// TODO Auto-generated method stub
////		return updateCount == 3 ? 1 : 0;
//		return 1;
//	}
//
//	@Override
//	public Integer deletePlace(int placeid) {
//		int deleteCount = 0;
//		deleteCount += placeMapper.deletePlace(placeid);
//		deleteCount += placeMapper.deletePlacepic(placeid);
//		return deleteCount == 2 ? 1 : 0;
//	}
//
//	@Override
//	public Integer insertPlace(PartnerPlaceDTO request) {
//		int insertCount = 0;
//		insertCount += placeMapper.insertPlace(request);
//		
//		System.out.println(request.getPlace_id());
//		insertCount += placeMapper.insertPlacepic(request);
//		return insertCount == 2 ? 1: 0;
//	}
}