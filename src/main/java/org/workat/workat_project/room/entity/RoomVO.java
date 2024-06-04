package org.workat.workat_project.room.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RoomVO {
    private int room_id;
    private int room_qnt; //상품개수
    private String room_name;
    private int room_price;
    private String room_content;
    private int sold_num;
    private int max_people;
    private int min_people;
    private int add_price;
    private int place_id;
}
