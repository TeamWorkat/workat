package org.workat.workat_project.wish.service;

import org.workat.workat_project.wish.entity.WishListDTO;
import org.workat.workat_project.wish.entity.WishVO;

import java.util.List;

public interface WishService {
    List<WishListDTO> userWishList(String name, String category);

    WishVO updateUserWish(String name, int placeId, String liked);
}
