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
	int place_id;
	String place_nm;
	String category;
	String place_addr;
	String place_tel;
	String place_in ;
	String place_out;
	int loc_id;
}
