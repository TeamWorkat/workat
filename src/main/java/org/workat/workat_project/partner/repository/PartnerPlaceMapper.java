package org.workat.workat_project.partner.repository;

import org.apache.ibatis.annotations.Mapper;
import org.workat.workat_project.partner.entity.PartnerPlaceVO;

import java.util.List;

@Mapper
public interface PartnerPlaceMapper {
	List<PartnerPlaceVO> getPlaceList();
}