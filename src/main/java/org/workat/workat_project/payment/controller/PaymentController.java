package org.workat.workat_project.payment.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.workat.workat_project.payment.entity.PaymentFailDTO;
import org.workat.workat_project.payment.entity.PaymentSuccessDTO;
import org.workat.workat_project.payment.service.PaymentService;
import org.workat.workat_project.reservation.entity.ReservationVO;
import org.workat.workat_project.reservation.repository.ReservationMapper;

@Controller
@RequiredArgsConstructor
@RequestMapping("/payment")
public class PaymentController {

    private final PaymentService paymentService;
    private final ReservationMapper reservationMapper;
    String redirectUrl = "http://localhost:8090/";

    @GetMapping("/toss/success")
    public ResponseEntity<PaymentSuccessDTO> tossPaySuccess(@RequestParam String paymentKey, @RequestParam String orderId, @RequestParam Long amount) {
        paymentService.tossPaymentSuccess(paymentKey, orderId, amount);
        ReservationVO reservationVO = reservationMapper.findByOrderId(orderId);
        return ResponseEntity.status(HttpStatus.FOUND).header("Location", redirectUrl + "reservation/detail/ "+reservationVO.getRes_id()).build();
    }

    @GetMapping("/toss/fail")
    public ResponseEntity<PaymentFailDTO> tossPaymentFail(@RequestParam String code, @RequestParam String message, @RequestParam String orderId) {
        paymentService.tossPaymentFail(code, message, orderId);
        return ResponseEntity.ok().body(
                PaymentFailDTO.builder()
                        .errorCode(code)
                        .errorMessage(message)
                        .orderId(orderId)
                        .build()
        );
    }

    @ResponseBody
    @PostMapping("/toss/cancel")
    public ResponseEntity<Object> tossPaymentCancelPoint(String paymentKey, String cancelReason) {
        System.err.println(paymentKey);
        return  ResponseEntity.ok().body((paymentService.cancelPayment(paymentKey, cancelReason)));
    }
}
