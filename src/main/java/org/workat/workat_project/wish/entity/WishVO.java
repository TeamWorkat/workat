package org.workat.workat_project.wish.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class WishVO {
    private int wish_id;
    private String liked;
    private int user_id;
    private int place_id;
}
