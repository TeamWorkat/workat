package org.workat.workat_project.reservation.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.workat.workat_project.payment.repository.PaymentMapper;
import org.workat.workat_project.picture.repository.PictureMapper;
import org.workat.workat_project.place.repository.PlaceMapper;
import org.workat.workat_project.reservation.entity.ReservationListDTO;
import org.workat.workat_project.reservation.entity.ReservationVO;
import org.workat.workat_project.reservation.entity.ReserveInfoRequestDTO;
import org.workat.workat_project.reservation.repository.ReservationMapper;
import org.workat.workat_project.room.repository.RoomMapper;
import org.workat.workat_project.user.entity.UserVO;
import org.workat.workat_project.user.repository.UserMapper;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ReservationServiceImpl implements ReservationService{

    private final ReservationMapper reservationMapper;
    private final UserMapper userMapper;
    private final PictureMapper pictureMapper;
    private final RoomMapper roomMapper;
    private final PaymentMapper paymentMapper;
    private final PlaceMapper placeMapper;

    @Override
    public ReservationVO saveReservationInfo(ReservationVO reservation, String userEmail){
        UserVO userVO = userMapper.findUserByEmail(userEmail);
        reservation.setUser_id(userVO.getUser_id());
        reservation.setRes_name(userVO.getUser_nm());
        reservationMapper.insertTempReservation(reservation);
        return reservationMapper.selectReservationById(reservation.getRes_id());
    }

    @Override
    public ReserveInfoRequestDTO reservationDetail(int reservationId) {
        ReserveInfoRequestDTO reserveInfoRequestDTO = new ReserveInfoRequestDTO();
        reserveInfoRequestDTO.setReservationVO(reservationMapper.selectReservationById(reservationId));
        reserveInfoRequestDTO.setPlace_nm(placeMapper.getPlaceInfo(reservationMapper.selectReservationById(reservationId).getPlace_id()).getPlace_nm());
        reserveInfoRequestDTO.setRoomPictureList(pictureMapper.getRoomPictureSources(reserveInfoRequestDTO.getReservationVO().getRoom_id()));
        reserveInfoRequestDTO.setRoomVO(roomMapper.getRoomInfo(reserveInfoRequestDTO.getReservationVO().getPlace_id()).get(0));
        reserveInfoRequestDTO.setPaymentKey(paymentMapper.findPaymentKeyByReservationId(reservationId));
        return reserveInfoRequestDTO;
    }

    @Override
    public List<ReservationListDTO> userReservationList(String name) {
        List<ReservationListDTO> reservationList = new ArrayList<>();
        UserVO userVO = userMapper.findUserByEmail(name);
        List<ReservationListDTO> reservationListDTO = reservationMapper.findReservationByUserId(userVO.getUser_id());
        for(ReservationListDTO reservationDTO : reservationListDTO){
            reservationDTO.setStartDate(reservationDTO.getCheck_in().atTime(reservationDTO.getPlace_in()));
            reservationDTO.setEndDate(reservationDTO.getCheck_out().atTime(reservationDTO.getPlace_out()));
            reservationList.add(reservationDTO);
        }
        return reservationList;
    }
}
