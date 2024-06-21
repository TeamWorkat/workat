package org.workat.workat_project.reservation.repository;

import org.apache.ibatis.annotations.Mapper;
import org.workat.workat_project.reservation.entity.ReservationListDTO;
import org.workat.workat_project.reservation.entity.ReservationVO;
import org.workat.workat_project.reservation.entity.ReserveInfoRequestDTO;

import java.util.List;

@Mapper
public interface ReservationMapper {
    void deleteReservationNotPaid();
    void insertTempReservation(ReservationVO reservationVO);
    ReservationVO selectReservationById(int reservationId);
    ReservationVO findByOrderId(String orderId);

    List<ReservationListDTO> findReservationByUserId(int userId);

    List<ReservationVO> getReservationVOList(int roomId);

    void updateReservationStatus(ReservationVO reservationVO);
}
