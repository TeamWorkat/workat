package org.workat.workat_project.place.entity;

import lombok.Getter;
import lombok.Setter;
import org.workat.workat_project.review.entity.ReviewResDTO;
import org.workat.workat_project.review.entity.StarPointDTO;
import org.workat.workat_project.room.entity.RoomResDTO;
import java.util.List;

@Getter
@Setter
public class PlaceDetailDTO {

    private PlaceVO placeVO;
    private List<String> place_picture_source;
    private String roadAddr;
    private String jibunAddr;
    private double latitude;
    private double longitude;
    private double rating;
    private List<StarPointDTO> star_points;
    private List<ReviewResDTO> reviewList;
    private List<RoomResDTO> roomList;

}
