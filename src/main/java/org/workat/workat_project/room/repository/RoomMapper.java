package org.workat.workat_project.room.repository;

import org.apache.ibatis.annotations.Mapper;
import org.workat.workat_project.room.entity.RoomVO;

import java.time.LocalDate;
import java.util.List;

@Mapper
public interface RoomMapper {

    List<RoomVO> getRoomInfo(int placeId);

    List<LocalDate> getReservedDate(int roomId);
}
