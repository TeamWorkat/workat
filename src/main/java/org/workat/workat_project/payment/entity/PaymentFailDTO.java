package org.workat.workat_project.payment.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PaymentFailDTO {
   String errorCode;
   String errorMessage;
   String orderId;
}
