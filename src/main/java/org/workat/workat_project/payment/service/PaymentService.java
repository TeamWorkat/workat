package org.workat.workat_project.payment.service;

import org.workat.workat_project.payment.entity.PaymentDTO;
import org.workat.workat_project.payment.entity.PaymentSuccessDTO;
import org.workat.workat_project.reservation.entity.ReservationVO;

public interface PaymentService {

    PaymentDTO preparePayment(ReservationVO reservationVO);

    PaymentSuccessDTO tossPaymentSuccess(String paymentKey, String orderId, Long amount);

    void tossPaymentFail(String code, String message, String orderId);

    Object cancelPayment(String paymentKey, String cancelReason);
}
