package org.workat.workat_project.picture.entity;

import org.workat.workat_project.place.entity.PlaceVO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PlacePictureVO {
  private int picture_id;
  private String picture_source;
  private String picture_origin_name;
  private String status;
  private int place_id;
}
