package org.workat.workat_project.wish.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class WishListDTO {
    private int place_id;
    private String place_nm;
    private String liked;
    private String[] picture_sources;
}
