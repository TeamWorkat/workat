package org.workat.workat_project.partner.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.workat.workat_project.partner.entity.PartnerPlaceDTO;
import org.workat.workat_project.partner.entity.PartnerPlaceVO;
import org.workat.workat_project.partner.repository.PartnerPlaceMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PartnerPlaceServiceImpl implements PartnerPlaceService {
	private final PartnerPlaceMapper placeMapper;

	@Override
	public List<PartnerPlaceVO> getPlaceList() {
		return placeMapper.getPlaceList();
	}

	@Override
	public PartnerPlaceDTO getPlaceDetail(int placeid) {
		return placeMapper.getPlaceDetail(placeid);
	}

	@Override
	public Integer updatePlace(PartnerPlaceDTO request) {
		int updateCount = 0;
		updateCount += placeMapper.updatePlace(request);
		updateCount += placeMapper.updatePlaceloc(request);
		updateCount += placeMapper.updatePlacepic(request);
		
		
		// TODO Auto-generated method stub
		return updateCount == 3 ? 1 : 0;
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