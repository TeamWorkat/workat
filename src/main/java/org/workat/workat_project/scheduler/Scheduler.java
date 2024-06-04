package org.workat.workat_project.scheduler;

import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.workat.workat_project.reservation.repository.ReservationMapper;
import org.workat.workat_project.room.entity.RoomVO;
import org.workat.workat_project.room.repository.RoomMapper;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.concurrent.TimeUnit;

@RequiredArgsConstructor
@Component
public class Scheduler {
    private final ReservationMapper reservationMapper;
    private final ScheduledExecutor scheduledExecutor;
    private final RoomMapper roomMapper;

    //결제까지 이어지지 않는 예약기록 삭제
    @Scheduled(cron = "0 0 0 * * *")
    public void deleteReservationNotPaid() {
        reservationMapper.deleteReservationNotPaid();
    }

    //재고 관리 스케줄러
    public void soldNumManage(LocalDateTime placeIn, LocalDateTime placeOut, LocalDateTime checkIn, LocalDateTime checkOut, RoomVO roomVO) {
        LocalDateTime startTime = checkIn.plusHours(placeIn.getHour()).plusMinutes(placeIn.getMinute());
        LocalDateTime endTime = checkOut.plusHours(placeOut.getHour()).plusMinutes(placeOut.getMinute());
        long delay = Duration.between(startTime, endTime).toMinutes();
        Runnable run = () -> {
            int soldNum = roomVO.getSold_num() - 1;;
            roomVO.setSold_num(soldNum);
            roomMapper.updateSoldNum(roomVO);
        };
        scheduledExecutor.scheduledExecutorService().schedule(run, delay, TimeUnit.SECONDS);
    }
}
