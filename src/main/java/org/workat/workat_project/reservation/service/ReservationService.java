package org.workat.workat_project.reservation.service;

import org.workat.workat_project.reservation.entity.ReservationListDTO;
import org.workat.workat_project.reservation.entity.ReservationVO;
import org.workat.workat_project.reservation.entity.ReserveInfoRequestDTO;

import java.util.List;

public interface ReservationService {

    ReservationVO saveReservationInfo(ReservationVO reservationInfo, String userEmail);

    ReserveInfoRequestDTO reservationDetail(int reservationId);

    List<ReservationListDTO> userReservationList(String name);
}
