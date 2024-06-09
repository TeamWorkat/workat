package org.workat.workat_project.wish.repository;

import org.apache.ibatis.annotations.Mapper;
import org.workat.workat_project.wish.entity.WishListDTO;

import java.util.List;

@Mapper
public interface WishMapper {
    List<WishListDTO> getUserWishPlace(int userId, String category);
}
