package org.workat.workat_project.reservation.service;

import org.workat.workat_project.reservation.entity.ReservationVO;

public interface ReservationService {

    ReservationVO saveReservationInfo(ReservationVO reservationInfo, String userEmail);

}
