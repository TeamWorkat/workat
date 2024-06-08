package org.workat.workat_project.wish.service;

import org.workat.workat_project.wish.entity.WishListDTO;

import java.util.List;

public interface WishService {
    List<WishListDTO> userWishList(String name, String category);
}
