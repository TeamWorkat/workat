package org.workat.workat_project.reservation.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.workat.workat_project.room.entity.RoomVO;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReserveInfoRequestDTO {
    private ReservationVO reservationVO;
    private RoomVO roomVO;
    private List<String> roomPictureList;
    private String paymentKey;
}
