package org.workat.workat_project.partner.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PartnerReplyDTO {
	private int reply_id;
	private int review_id;
	private int res_id;
	private String reply_content;
	private String created_date;
	private String modified_date;
	private String status;
	
}
