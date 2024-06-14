package org.workat.workat_project.reservation.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.workat.workat_project.payment.entity.PaymentDTO;
import org.workat.workat_project.payment.service.PaymentService;
import org.workat.workat_project.reservation.entity.ReservationListDTO;
import org.workat.workat_project.reservation.entity.ReservationVO;
import org.workat.workat_project.reservation.entity.ReserveInfoRequestDTO;
import org.workat.workat_project.reservation.service.ReservationService;

import java.util.List;

@Controller
@AllArgsConstructor
@RequestMapping("/reserve")
public class ReservationController {

    private final ReservationService reservationService;
    private final PaymentService paymentService;

    @ResponseBody
    @PostMapping("/saveInfo")
    public ResponseEntity<PaymentDTO> saveReservationInfo(@RequestBody ReservationVO reserveInfo, Authentication principal) {
        ReservationVO savedReservationInfo = reservationService.saveReservationInfo(reserveInfo, principal.getName());
        return ResponseEntity.ok(paymentService.preparePayment(savedReservationInfo));
    }

    @ResponseBody
    @GetMapping("/resDetail")
    public ResponseEntity<ReserveInfoRequestDTO> reservationDetail(@RequestParam(name = "res_id") int reservationId) {
        System.err.println(reservationId);
        return ResponseEntity.ok(reservationService.reservationDetail(reservationId));
    }

    @GetMapping("/list")
    @ResponseBody
    public ResponseEntity<List<ReservationListDTO>> getReservations(Authentication principal) {
        return ResponseEntity.ok(reservationService.userReservationList(principal.getName()));
    }
}
