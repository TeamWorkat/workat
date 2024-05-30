package org.workat.workat_project.user.repository;

import org.apache.ibatis.annotations.Mapper;
import org.workat.workat_project.user.entity.UserDetailDTO;
import org.workat.workat_project.user.entity.UserListDTO;
import org.workat.workat_project.user.entity.UserVO;

import java.util.List;
import java.util.Optional;

@Mapper
public interface UserMapper {
    List<UserListDTO> getUserList();
    UserDetailDTO getUserDetail(int user_id);
    UserVO findById(int userId);
    void deleteById(int userId);
}