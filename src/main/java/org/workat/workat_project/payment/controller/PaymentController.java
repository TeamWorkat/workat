package org.workat.workat_project.payment.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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

    @GetMapping("/toss/success")
    public ResponseEntity<PaymentSuccessDTO> tossPaySuccess(@RequestParam String paymentKey, @RequestParam String orderId, @RequestParam Long amount){
        paymentService.tossPaymentSuccess(paymentKey, orderId, amount);
        ReservationVO reservationVO = reservationMapper.findByOrderId(orderId);
        String redirectUrl = "http://localhost:8090/reservation/detail/" + reservationVO.getRes_id();
        return ResponseEntity.status(HttpStatus.FOUND).header("Location", redirectUrl).build();
    }

    @GetMapping("/toss/fail")
    public ResponseEntity<PaymentFailDTO> tossPaymentFail(@RequestParam String code, @RequestParam String message, @RequestParam String orderId){
        paymentService.tossPaymentFail(code, message, orderId);
        return ResponseEntity.ok().body(
                PaymentFailDTO.builder()
                        .errorCode(code)
                        .errorMessage(message)
                        .orderId(orderId)
                        .build()
        );
    }

}
