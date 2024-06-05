package org.workat.workat_project.payment.repository;

import org.apache.ibatis.annotations.Mapper;
import org.workat.workat_project.payment.entity.PaymentDTO;
import org.workat.workat_project.payment.entity.PaymentVO;

@Mapper
public interface PaymentMapper {
    void saveTempPayment(PaymentDTO paymentDTO);

    PaymentVO findPaymentById(String orderId);

    void updatePaymentFail(PaymentVO paymentVO);

    void updatePaymentSuccess(PaymentVO paymentVO);

    void updatePaymentCancel(PaymentVO paymentVO);

    String findPaymentKeyByReservationId(int reservationId);

    PaymentVO findPaymentByPaymentKey(String paymentKey);
}
