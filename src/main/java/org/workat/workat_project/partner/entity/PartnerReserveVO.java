package org.workat.workat_project.partner.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PartnerReserveVO {
	private int res_id;
	private String res_name;
	private String user_tel;
	private String check_in;
	private String check_out;
	private String res_message;
	private String room_name;
	private String res_cancel_yn;
	private String paymentKey; 
	private String cancelReason;
}
