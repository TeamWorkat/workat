package org.workat.workat_project.payment.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PaymentVO {
   private String payment_id;
   private int res_id;
   private int amount;
   private String order_name;
   private String paySuccessYN;
   private String paymentKey;
   private String failReason;
   private String cancelYN;
   private String cancelReason;
}
