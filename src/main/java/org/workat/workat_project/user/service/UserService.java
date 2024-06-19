package org.workat.workat_project.user.service;

import org.workat.workat_project.user.entity.UserDetailDTO;
import org.workat.workat_project.user.entity.UserListDTO;
import org.workat.workat_project.user.entity.UserVO;

import java.util.List;

public interface UserService {
    List<UserListDTO> getList();
    UserDetailDTO getUserDetail(String user_id);
    boolean checkPassword(String user_pwd, String user_email);
    UserVO checkLoginInfo(String user_id, String role);
    void deleteUser(int user_id);
    void updateUser(UserDetailDTO userDetailDTO);
    void createUser(UserDetailDTO userDetailDTO);
    boolean isEmailAlreadyExists(String user_email, String role);
}