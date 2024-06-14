package org.workat.workat_project.partner.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.workat.workat_project.partner.entity.PartnerPlaceDTO;
import org.workat.workat_project.partner.entity.PartnerPlaceVO;
import org.workat.workat_project.partner.repository.PartnerPlaceMapper;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;


@Slf4j
@Service
@RequiredArgsConstructor
public class PartnerPlaceServiceImpl implements PartnerPlaceService {
	private final PartnerPlaceMapper placeMapper;

	private static final Logger logger = LoggerFactory.getLogger(PartnerPlaceServiceImpl.class);
	
	@Override
	public List<PartnerPlaceVO> getPlaceList() {
		return placeMapper.getPlaceList();
	}

	@Override
	public PartnerPlaceDTO getPlaceDetail(int placeid) {
		PartnerPlaceDTO partnerDTO = placeMapper.getPlaceDetail(placeid);
		String[] picture_sources = partnerDTO.getPicture_source().split(",");
		partnerDTO.setPicture_sources(picture_sources);
		return partnerDTO;
	}
	
	@Transactional
	@Override
	public Integer updatePlace(PartnerPlaceDTO request) {
		logger.debug("aaa");
//		int updateCount = 0;
//		updateCount += placeMapper.updatePlace(request);
//		updateCount += placeMapper.updatePlaceloc(request);
//		updateCount += placeMapper.updatePlacepic(request);
		
		
		placeMapper.updatePlace(request);
		
		placeMapper.updatePlacepic(request);
		placeMapper.inactivePlace(request);
		
		
		// TODO Auto-generated method stub
//		return updateCount == 3 ? 1 : 0;
		return 1;
	}

	@Override
	public Integer deletePlace(int placeid) {
		int deleteCount = 0;
		deleteCount += placeMapper.deletePlace(placeid);
		deleteCount += placeMapper.deletePlacepic(placeid);
		return deleteCount == 2 ? 1 : 0;
	}

	@Override
	public Integer deletePlacepic(int placeid) {
		return 0;
	}

	@Override
	public Integer insertPlace(PartnerPlaceDTO request) {
		int insertCount = 0;
		insertCount += placeMapper.insertPlace(request);
		
		System.out.println(request.getPlace_id());
		insertCount += placeMapper.insertPlacepic(request);
		return insertCount == 2 ? 1: 0;
	}
}