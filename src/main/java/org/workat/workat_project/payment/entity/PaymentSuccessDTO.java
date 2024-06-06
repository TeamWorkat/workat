package org.workat.workat_project.payment.entity;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PaymentSuccessDTO {
    private String mId;
  	 private String version;
  	 private String paymentKey;
  	 private String orderId;
	 private String payType;
  	 private String orderName;
  	 private String currency;
  	 private String method;
  	 private String status;
  	 private String requestedAt;
  	 private String approvedAt;
  	 private String useEscrow;
  	 private String cultureExpense;
  	 private String totalAmount;
  	 private String balanceAmount;
  	 private String suppliedAmount;
  	 private String vat;
  	 private String taxFreeAmount;
  	 private PaymentCardResponseDTO card;
  	 private String type;
}
