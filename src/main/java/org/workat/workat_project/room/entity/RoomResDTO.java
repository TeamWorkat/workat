package org.workat.workat_project.room.entity;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
public class RoomResDTO {
    private RoomVO roomVO;
    private List<String> room_picture_source;
    private List<LocalDate> reserved_date;
}
