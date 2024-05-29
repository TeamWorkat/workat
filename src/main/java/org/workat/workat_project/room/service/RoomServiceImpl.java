package org.workat.workat_project.room.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.workat.workat_project.picture.repository.PictureMapper;
import org.workat.workat_project.room.entity.RoomResDTO;
import org.workat.workat_project.room.entity.RoomVO;
import org.workat.workat_project.room.repository.RoomMapper;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RoomServiceImpl implements RoomService{

    private final RoomMapper roomMapper;
    private final PictureMapper pictureMapper;

    @Override
    public List<RoomResDTO> getRoomInfoList(int placeId){
        List<RoomResDTO> roomInfoList = new ArrayList<>();
        List<RoomVO> roomVOList = roomMapper.getRoomInfo(placeId);
        for(RoomVO roomVO : roomVOList){
            RoomResDTO roomResDTO = new RoomResDTO();
            if(roomVO.getRoom_qnt() != roomVO.getSold_num() && roomVO.getRoom_qnt() > roomVO.getSold_num()){ //품절이 아닌 경우에만 가져온다
                roomResDTO.setRoomVO(roomVO);
                roomResDTO.setRoom_picture_source(pictureMapper.getRoomPictureSources(roomVO.getRoom_id()));
                roomResDTO.setReserved_date(roomMapper.getReservedDate(roomVO.getRoom_id()));
                roomInfoList.add(roomResDTO);
            }
        }
        return roomInfoList;
    }
}
