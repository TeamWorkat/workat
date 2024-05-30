package org.workat.workat_project.user.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.workat.workat_project.user.entity.UserDetailDTO;
import org.workat.workat_project.user.entity.UserListDTO;
import org.workat.workat_project.user.entity.UserVO;
import org.workat.workat_project.user.repository.UserMapper;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public List<UserListDTO> getList() {
        return userMapper.getUserList();
    }

    @Override
    public UserDetailDTO getUserDetail(int user_id) {
        return userMapper.getUserDetail(user_id);
    }

    @Override
    public boolean checkPassword(int user_id, String rawPassword) {
        UserVO user = userMapper.findById(user_id);
        if (user != null) {
            System.out.println("user" + user);
            System.out.println(passwordEncoder.encode(rawPassword));
            return passwordEncoder.matches(rawPassword, user.getUser_pwd());
        } else {
            throw new RuntimeException("User not found");
        }
    }

    @Override
    public void deleteUser(int user_id) {
        userMapper.deleteById(user_id);
    }
}