package org.workat.workat_project.place.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PlaceVO {
   private int place_id;
   private String place_nm;
   private String category;
   private String place_addr;
   private String place_tel;
   private String place_content;
   private String created_date;
   private String modified_date;
   private String status;
   private LocalDateTime place_in;
   private LocalDateTime place_out;
   private String field;
   private int loc_id;
   private int user_id;
}