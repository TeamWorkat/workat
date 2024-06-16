package org.workat.workat_project.partner.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PartnerReviewDTO {
	private int review_id;
	private int res_id;
	private String res_name;
	private String check_in;
	private String check_out;
	private String place_nm;
	private String room_name; 
	private String reply_exist; 
	private int rating;
	private String content;
	private String reply_content;
	private String created_date;
	private String modified_date;
	private String status;
	private int user_id;
	private int place_id;
	private String pictureURLArray;
	private String[] picture_sources;
}