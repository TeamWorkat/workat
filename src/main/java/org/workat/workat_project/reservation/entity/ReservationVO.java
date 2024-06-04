package org.workat.workat_project.reservation.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReservationVO {
    private int res_id;
    private int res_price;
    private String res_name;
    private LocalDateTime check_in;
    private LocalDateTime check_out;
    private String res_yn;
    private int res_people_num;
    private String res_message;
    private int place_id;
    private int user_id;
    private int room_id;
}
