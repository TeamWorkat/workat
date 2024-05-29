package org.workat.workat_project.user.service;

import org.workat.workat_project.user.entity.UserDetailDTO;
import org.workat.workat_project.user.entity.UserListDTO;

import java.util.List;

public interface UserService {
    List<UserListDTO> getList();
    UserDetailDTO getUserDetail(int user_id);
}