package org.workat.workat_project.reservation.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.workat.workat_project.reservation.entity.ReservationVO;
import org.workat.workat_project.reservation.repository.ReservationMapper;
import org.workat.workat_project.user.entity.UserVO;
import org.workat.workat_project.user.repository.UserMapper;

@Service
@RequiredArgsConstructor
public class ReservationServiceImpl implements ReservationService{

    private final ReservationMapper reservationMapper;
    private final UserMapper userMapper;

    @Override
    public ReservationVO saveReservationInfo(ReservationVO reservation, String userEmail){
        UserVO userVO = userMapper.findUserByEmail(userEmail);
        reservation.setUser_id(userVO.getUser_id());
        reservation.setRes_name(userVO.getUser_nm());
        reservationMapper.insertTempReservation(reservation);
        return reservationMapper.selectReservationById(reservation.getRes_id());
    }
}
