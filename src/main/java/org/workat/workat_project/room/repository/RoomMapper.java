package org.workat.workat_project.room.repository;

import org.apache.ibatis.annotations.Mapper;
import org.workat.workat_project.room.entity.RoomVO;

import java.time.LocalDate;
import java.util.List;

@Mapper
public interface RoomMapper {

    List<RoomVO> getRoomInfo(int placeId);

    List<LocalDate> getReservedDate(int roomId);

    void updateSoldNum(RoomVO roomVO);

    RoomVO getRoomInfoByPlaceAndRoom(int placeId, int roomId);

    List<RoomVO> getAllRoomInfo();

    void insertUnavailableDate(int roomId, LocalDate key);

    void deleteUnavailableDate(int roomId, LocalDate key);
}
