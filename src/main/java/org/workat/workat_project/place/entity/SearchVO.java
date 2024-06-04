package org.workat.workat_project.place.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SearchVO {
	private String category;
	private int location;
	private String startDate;
	private String endDate;
	private int guests;
}