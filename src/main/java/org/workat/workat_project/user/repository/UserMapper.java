package org.workat.workat_project.user.repository;

import org.apache.ibatis.annotations.Mapper;
import org.workat.workat_project.user.entity.UserDetailDTO;
import org.workat.workat_project.user.entity.UserListDTO;
import org.workat.workat_project.user.entity.UserVO;

import java.util.List;

@Mapper
public interface UserMapper {
    List<UserListDTO> getUserList();
    UserVO findUserByEmail(String email);
    void deleteUser(int user_id);
    void updateUser(UserDetailDTO userDetailDTO);
    void updatePassword(UserVO user);
    void createUser(UserDetailDTO userDetailDTO);
    boolean checkEmail(String user_email);
}