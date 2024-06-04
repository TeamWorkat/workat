package org.workat.workat_project.payment.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.workat.workat_project.payment.config.TossPaymentConfig;
import org.workat.workat_project.payment.entity.PaymentDTO;
import org.workat.workat_project.payment.entity.PaymentSuccessDTO;
import org.workat.workat_project.payment.entity.PaymentVO;
import org.workat.workat_project.payment.repository.PaymentMapper;
import org.workat.workat_project.reservation.entity.ReservationVO;
import org.workat.workat_project.reservation.repository.ReservationMapper;
import org.workat.workat_project.room.entity.RoomVO;
import org.workat.workat_project.room.repository.RoomMapper;

import java.nio.charset.StandardCharsets;
import java.time.temporal.ChronoUnit;
import java.util.*;


@Service
@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService {

    private final RoomMapper roomMapper;
    private final TossPaymentConfig tossPaymentConfig;
    private final PaymentMapper paymentMapper;
    private final ReservationMapper reservationMapper;

    @Override
    public PaymentDTO preparePayment(ReservationVO reservationVO) {
        PaymentDTO paymentDTO = new PaymentDTO();

        paymentDTO.setOrderId(UUID.randomUUID().toString());

        RoomVO roomVO = roomMapper.getRoomInfo(reservationVO.getPlace_id()).get(0);
        long daysBetween = ChronoUnit.DAYS.between(reservationVO.getCheck_in().toLocalDate(), reservationVO.getCheck_out().toLocalDate());
        paymentDTO.setOrderName(roomVO.getRoom_name() + " " + daysBetween + "박");

        paymentDTO.setAmount(reservationVO.getRes_price());

        paymentDTO.setYourSuccessUrl(tossPaymentConfig.getSuccessUrl());
        paymentDTO.setYourFailUrl(tossPaymentConfig.getFailUrl());

        paymentDTO.setUserName(reservationVO.getRes_name());
        paymentDTO.setRes_id(reservationVO.getRes_id());
        paymentMapper.saveTempPayment(paymentDTO);
        return paymentDTO;
    }

    @Override
    public PaymentSuccessDTO tossPaymentSuccess(String paymentKey, String orderId, Long amount) {
        PaymentVO paymentVO = paymentMapper.findPaymentById(orderId);
        if (paymentVO == null) {
            throw new RuntimeException("유효하지 않은 결제 입니다.");
        }
        ReservationVO reservationVO = reservationMapper.selectReservationById(paymentVO.getRes_id());
        if (reservationVO.getRes_price() != amount) {
            throw new RuntimeException("결제 금액이 요청했을때와 다릅니다");
        }
        paymentVO.setPaymentKey(paymentKey);
        paymentVO.setPaySuccessYN("Y");
        paymentMapper.updatePaymentSuccess(paymentVO);
        return requestPaymentAccept(paymentKey, orderId, amount);
    }

    @Override
    public void tossPaymentFail(String code, String message, String orderId) {
        PaymentVO paymentVO = paymentMapper.findPaymentById(orderId);
        if (paymentVO == null) {
            throw new RuntimeException("유효하지 않은 결제 입니다.");
        }
        paymentVO.setFailReason(message);
        paymentVO.setPaySuccessYN("N");
        paymentMapper.updatePaymentFail(paymentVO);
    }

    private PaymentSuccessDTO requestPaymentAccept(String paymentKey, String orderId, Long amount) {
        RestTemplate restTemplate = new RestTemplate();

        ObjectMapper objectMapper = new ObjectMapper();
        HttpHeaders headers = getHeaders();

        Map<String, Object> payload = new HashMap<>();
        payload.put("orderId", orderId);
        payload.put("amount", amount);

        try {
            String jsonPayload = objectMapper.writeValueAsString(payload);

            ResponseEntity<PaymentSuccessDTO> responseEntity = restTemplate.exchange(
                    TossPaymentConfig.URL + paymentKey,
                    HttpMethod.POST,
                    new HttpEntity<>(jsonPayload, headers),
                    PaymentSuccessDTO.class);

            return responseEntity.getBody();
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }

    private HttpHeaders getHeaders() {
        HttpHeaders headers = new HttpHeaders();
        String encodedAuthKey = new String(
                Base64.getEncoder().encode((tossPaymentConfig.getTestSecretKey() + ":").getBytes(StandardCharsets.UTF_8)));
        headers.setBasicAuth(encodedAuthKey);
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        return headers;
    }
}
