package org.workat.workat_project.user.service;

import org.workat.workat_project.user.entity.UserDetailDTO;
import org.workat.workat_project.user.entity.UserListDTO;
import org.workat.workat_project.user.entity.UserVO;

import java.util.List;

public interface UserService {
    List<UserListDTO> getList();
    UserDetailDTO getUserDetail(int user_id);
    boolean checkPassword(int user_id, String user_pwd);
    void deleteUser(int user_id);
    void updateUser(UserDetailDTO userDetailDTO);
    void createUser(UserDetailDTO userDetailDTO);

    boolean checkLoginInfo(String user_email, String user_pwd);
}