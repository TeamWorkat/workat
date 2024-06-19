package org.workat.workat_project.place.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PlaceListDTO {
	private int place_id;
	private String picture_source;
	private String place_nm;
	private String place_content;
	private String picString;
	private String[] picture_sources;
	private String liked;
}
