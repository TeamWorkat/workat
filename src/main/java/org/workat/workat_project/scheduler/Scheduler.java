package org.workat.workat_project.scheduler;

import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.workat.workat_project.reservation.repository.ReservationMapper;
import org.workat.workat_project.room.repository.RoomMapper;

@RequiredArgsConstructor
@Component
public class Scheduler {
    private final ReservationMapper reservationMapper;
    private final RoomMapper roomMapper;

    //결제까지 이어지지 않는 예약기록 삭제
    @Scheduled(cron = "0 0 0 * * *")
    public void deleteReservationNotPaid() {
        reservationMapper.deleteReservationNotPaid();
    }

}
