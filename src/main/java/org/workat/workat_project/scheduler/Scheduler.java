package org.workat.workat_project.scheduler;

import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.workat.workat_project.reservation.entity.ReservationVO;
import org.workat.workat_project.reservation.repository.ReservationMapper;
import org.workat.workat_project.room.entity.RoomVO;
import org.workat.workat_project.room.repository.RoomMapper;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@Component
public class Scheduler {
    private final ReservationMapper reservationMapper;
    private final RoomMapper roomMapper;

    @Scheduled(cron = "0 0 0 * * *")
    public void deleteReservationNotPaid() {
        reservationMapper.deleteReservationNotPaid();
    }

    @Scheduled(fixedDelay = 60000)
    public void setNotAvailableDate() {
        List<RoomVO> rooms = roomMapper.getAllRoomInfo();
        for (RoomVO room : rooms) {
            Map<LocalDate, Integer> dateCountMap = new HashMap<>();
            List<LocalDate> dates = new ArrayList<>();
            List<ReservationVO> reservationList = reservationMapper.getReservationVOList(room.getRoom_id());
            for (ReservationVO reservation : reservationList) {
                        LocalDate startDate = reservation.getCheck_in().toLocalDate();
                        LocalDate endDate = reservation.getCheck_out().toLocalDate();

                        while (!startDate.isAfter(endDate)) {
                            dates.add(startDate);
                            startDate = startDate.plusDays(1);
                        }
                    }
            for (LocalDate date : dates) {
                dateCountMap.put(date, dateCountMap.getOrDefault(date, 0) + 1);
            }
            for (Map.Entry<LocalDate, Integer> entry : dateCountMap.entrySet()) {
                if (entry.getValue() > 1) {
                    if(entry.getValue() == room.getRoom_qnt()){
                        roomMapper.insertUnavailableDate(room.getRoom_id(),entry.getKey());
                    }else{
                        roomMapper.deleteUnavailableDate(room.getRoom_id(),entry.getKey());
                    }
                }
            }
        }
    }
}
