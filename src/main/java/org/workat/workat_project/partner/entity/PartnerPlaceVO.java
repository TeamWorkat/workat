package org.workat.workat_project.partner.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PartnerPlaceVO {
	private int place_id;
	private String place_nm;
	private String category;
	private String place_addr;
	private String place_tel;
	private String place_in ;
	private String place_out;
	private String loc_nm;
}
