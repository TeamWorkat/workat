package org.workat.workat_project.wish.repository;

import org.apache.ibatis.annotations.Mapper;
import org.workat.workat_project.wish.entity.WishListDTO;
import org.workat.workat_project.wish.entity.WishVO;

import java.util.List;

@Mapper
public interface WishMapper {
    List<WishListDTO> getUserWishPlace(int userId, String category);

    WishVO getUserWish(int userId, int placeId);

    void insertUserWish(int userId, int placeId, String liked);

    void updateUserWish(int userId, int placeId, String liked);
}
