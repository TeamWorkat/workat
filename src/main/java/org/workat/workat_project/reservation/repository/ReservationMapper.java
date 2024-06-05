package org.workat.workat_project.reservation.repository;

import org.apache.ibatis.annotations.Mapper;
import org.workat.workat_project.reservation.entity.ReservationVO;
import org.workat.workat_project.reservation.entity.ReserveInfoRequestDTO;

@Mapper
public interface ReservationMapper {
    void deleteReservationNotPaid();
    void insertTempReservation(ReservationVO reservationVO);
    ReservationVO selectReservationById(int reservationId);
    ReservationVO findByOrderId(String orderId);
}
