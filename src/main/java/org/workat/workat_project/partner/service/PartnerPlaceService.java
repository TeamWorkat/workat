package org.workat.workat_project.partner.service;

import java.util.List;

import org.workat.workat_project.partner.entity.PartnerPlaceDTO;
import org.workat.workat_project.partner.entity.PartnerPlaceVO;



public interface PartnerPlaceService {
	List<PartnerPlaceVO> getPlaceList();
	PartnerPlaceDTO getPlaceDetail(int placeid);
}
