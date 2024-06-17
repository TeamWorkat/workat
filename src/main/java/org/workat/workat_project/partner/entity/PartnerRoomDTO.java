package org.workat.workat_project.partner.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PartnerRoomDTO {
	private int room_id;
	private int room_qnt;
	private String room_name;
	private int room_price;
	private String room_content;
	private int sold_num;
	private int max_people;
	private int picture_id;
	private int place_id;
	private String place_nm;
	private int add_price;
	private int min_people;
	private String status;
	private String pictureURLArray;
	private String[] picture_sources;
}
