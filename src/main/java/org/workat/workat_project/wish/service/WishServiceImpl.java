package org.workat.workat_project.wish.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.workat.workat_project.picture.repository.PictureMapper;
import org.workat.workat_project.user.entity.UserVO;
import org.workat.workat_project.user.repository.UserMapper;
import org.workat.workat_project.wish.entity.WishListDTO;
import org.workat.workat_project.wish.entity.WishVO;
import org.workat.workat_project.wish.repository.WishMapper;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
@AllArgsConstructor
public class WishServiceImpl implements WishService {

    private final WishMapper wishMapper;
    private final UserMapper userMapper;
    private final PictureMapper pictureMapper;

    @Override
    public List<WishListDTO> userWishList(String name, String category) {
        List<WishListDTO> list = new ArrayList<>();
            UserVO userVO = userMapper.findUserByEmail(name);
            List<WishListDTO> wishListDTO = wishMapper.getUserWishPlace(userVO.getUser_id(), category);
        for (WishListDTO wishListDTOWithOutPicture : wishListDTO) {
            List<String> wishPictureList = pictureMapper.getPlacePictureSources(wishListDTOWithOutPicture.getPlace_id());
            String[] stream = wishPictureList.toArray(String[]::new);
            System.err.println(Arrays.toString(stream));
            wishListDTOWithOutPicture.setPicture_sources(stream);
            list.add(wishListDTOWithOutPicture);
        }
        return list;
    }

    @Override
    public WishVO updateUserWish(String name, int placeId, String liked) {
        WishVO wishVO = wishMapper.getUserWish(userMapper.findUserByEmail(name).getUser_id(),placeId);
        if(wishVO == null){
            wishMapper.insertUserWish(userMapper.findUserByEmail(name).getUser_id(), placeId, liked);
        }else{
            wishMapper.updateUserWish(userMapper.findUserByEmail(name).getUser_id(), placeId, liked);
        }
        return wishMapper.getUserWish(userMapper.findUserByEmail(name).getUser_id(),placeId);
    }
}
