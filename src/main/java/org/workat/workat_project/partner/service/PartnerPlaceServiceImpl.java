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
}