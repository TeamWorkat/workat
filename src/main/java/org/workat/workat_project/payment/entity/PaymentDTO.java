package org.workat.workat_project.payment.entity;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PaymentDTO {
    @NonNull
    private Integer amount;
    @NonNull
    private String orderName;
    @NonNull
    private String orderId;

    private String userName;

    private int res_id;

    private String yourSuccessUrl;
    private String yourFailUrl;
}
