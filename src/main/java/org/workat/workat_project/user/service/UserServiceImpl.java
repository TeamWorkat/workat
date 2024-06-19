package org.workat.workat_project.user.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.workat.workat_project.user.entity.UserDetailDTO;
import org.workat.workat_project.user.entity.UserListDTO;
import org.workat.workat_project.user.entity.UserVO;
import org.workat.workat_project.user.repository.UserMapper;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;

    @Override
    public List<UserListDTO> getList() {
        return userMapper.getUserList();
    }

    @Override
    public UserDetailDTO getUserDetail(String user_id) {
        UserVO userVO = userMapper.findUserByEmail(user_id);
        return convertToUserDetailDTO(userVO);
    }

    @Override
    public void deleteUser(int user_id) {
        userMapper.deleteUser(user_id);
    }
    @Override
    public void updateUser(UserDetailDTO userDetailDTO) {
        if (!isPasswordEncoded(userDetailDTO.getUser_pwd())) {
            String encodedPassword = passwordEncoder.encode(userDetailDTO.getUser_pwd());
            userDetailDTO.setUser_pwd(encodedPassword);
        }
        userMapper.updateUser(userDetailDTO);
    }
    @Override
    public void createUser(UserDetailDTO userDetailDTO) {
        if (!isPasswordEncoded(userDetailDTO.getUser_pwd())) {
            String encodedPassword = passwordEncoder.encode(userDetailDTO.getUser_pwd());
            userDetailDTO.setUser_pwd(encodedPassword);
        }
        userMapper.createUser(userDetailDTO);
    }

    @Override
    public boolean checkPassword(String rawPassword, String user_email) {
        System.out.println("******* 서비스 단 user_email *******"+user_email);
        UserVO user = userMapper.findUserByEmail(user_email);
        System.out.println("******* 서비스 단 user *******"+user);
        System.out.println("******* 서비스 단 check pwd *******"+user.getUser_pwd());
        if (user != null) {
            String storedPassword = user.getUser_pwd();
            if (!isPasswordEncoded(storedPassword)) {
                if (storedPassword.equals(rawPassword)) {
                    String encodedPassword = passwordEncoder.encode(rawPassword);
                    user.setUser_pwd(encodedPassword);
                    userMapper.updatePassword(user);
                    System.out.println(user.getUser_pwd());
                    return true;
                } else {
                    return false;
                }
            } else {
                System.out.println(user.getUser_pwd());
                return passwordEncoder.matches(rawPassword, storedPassword);
            }
        } else {
            throw new RuntimeException("User not found");
        }
    }

    @Override
    public UserVO checkLoginInfo(String user_email, String role) {
        log.debug("Checking login info for user_email: {}, role: {}", user_email, role);
        return userMapper.checkLoginInfo(user_email, role);
    }

    @Override
    public boolean isEmailAlreadyExists(String user_email, String role) {
        return userMapper.checkEmail(user_email, role);
    }

    private UserDetailDTO convertToUserDetailDTO(UserVO userVO) {
        UserDetailDTO userDetailDTO = new UserDetailDTO();
        userDetailDTO.setUser_id(userVO.getUser_id());
        userDetailDTO.setUser_email(userVO.getUser_email());
        userDetailDTO.setUser_pwd(userVO.getUser_pwd());
        userDetailDTO.setUser_nm(userVO.getUser_nm());
        userDetailDTO.setUser_tel(userVO.getUser_tel());
        userDetailDTO.setCreated_date(userVO.getCreated_date());
        userDetailDTO.setModified_date(userVO.getModified_date());
        return userDetailDTO;
    }

    private boolean isPasswordEncoded(String password) {
        return password.startsWith("$2a$") || password.startsWith("$2b$") || password.startsWith("$2y$");
    }
}