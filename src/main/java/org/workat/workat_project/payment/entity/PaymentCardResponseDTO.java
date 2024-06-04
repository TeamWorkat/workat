package org.workat.workat_project.payment.entity;

import lombok.Data;

@Data
public class PaymentCardResponseDTO {

	private String company;
	private String number;
	private String installmentPlanMonths;
	private String isInterestFree;
	private String interestPayer;
	private String approveNo;
	private String useCardPoint;
	private String cardType;
	private String ownerType;
	private String acquireStatus;
	private String receiptUrl;

}
