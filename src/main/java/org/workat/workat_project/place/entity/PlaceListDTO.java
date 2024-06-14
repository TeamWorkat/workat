package org.workat.workat_project.place.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PlaceListDTO {
	private int place_id;
	private List<String> picture_source;
	private String place_nm;
	private String liked;
}
