package org.workat.workat_project.user.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.workat.workat_project.user.entity.UserDetailDTO;
import org.workat.workat_project.user.entity.UserListDTO;
import org.workat.workat_project.user.repository.UserMapper;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public List<UserListDTO> getList() {
        return userMapper.getUserList();
    }

    @Override
    public UserDetailDTO getUserDetail(int user_id) {
        return userMapper.getUserDetail(user_id);
    }
}