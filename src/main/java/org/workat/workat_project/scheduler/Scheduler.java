package org.workat.workat_project.scheduler;

import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.workat.workat_project.reservation.repository.ReservationMapper;

@RequiredArgsConstructor
@Component
public class Scheduler {
    private final ReservationMapper reservationMapper;
    /*
     * [Scheduler 설정 참고사항]
     * 초 : 0~59
     * 분 : 0~59
     * 시 : 0~23
     * 일 : 1~31
     * 월 : 1~12 혹은 JAN FEB MAR APR MAY JUN JUL AUG SEP OCT NOV DEC
     * 요일 : 1~7 혹은 SUN MON TUE WED THU FRI SAT
     * <p>
     * * : 모든 값
     * ? : 설정 없음 (일과 요일만 사용 가능)
     * , : 배열 ex) 1,5,8 : 1,5,8에만
     * - : 앞부터 뒤까지 ex) 1-3 : 1부터 3까지
     * / : 앞부터 뒤마다 ex) 1/3 : 1부터 매3마다 1,4,7,11...
     */

    //결제까지 이어지지 않는 예약기록 삭제
    @Scheduled(cron = "0 0 0 * * *")
    public void deleteReservationNotPaid() {
        reservationMapper.deleteReservationNotPaid();
    }
}
