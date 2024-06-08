package org.workat.workat_project.reservation.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ReservationListDTO {
    private int res_id;
    private String place_nm;
    private LocalTime place_in;
    private LocalTime place_out;
    private String res_cancel_yn;
    private String room_name;
    private LocalDate check_in;
    private LocalDate check_out;
    private int res_people_num;
    private String res_name;
    private int amount;
    private LocalDateTime createdAt;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
}
