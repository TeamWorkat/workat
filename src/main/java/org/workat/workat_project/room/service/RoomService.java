package org.workat.workat_project.room.service;

import org.workat.workat_project.room.entity.RoomResDTO;

import java.util.List;

public interface RoomService {
    List<RoomResDTO> getRoomInfoList(int placeId);
}
