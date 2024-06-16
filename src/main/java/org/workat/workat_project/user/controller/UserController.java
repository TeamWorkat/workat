package org.workat.workat_project.user.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.workat.workat_project.user.entity.LoginDTO;
import org.workat.workat_project.user.entity.UserDetailDTO;
import org.workat.workat_project.user.repository.UserMapper;
import org.workat.workat_project.user.service.UserServiceImpl;


@Controller
@CrossOrigin
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserServiceImpl userServiceImpl;
    @Autowired
    private UserMapper userMapper;

    @GetMapping("/detail")
    public ResponseEntity<UserDetailDTO> getUserDetail(Authentication authentication) {
        return ResponseEntity.ok(userServiceImpl.getUserDetail(authentication.getName()));
    }

    @PostMapping("/check-password")
    public ResponseEntity<Boolean> checkPassword(@RequestBody LoginDTO loginDTO, Authentication principal) {
        boolean isValid = userServiceImpl.checkPassword(loginDTO.getUser_pwd(), principal.getName());
        System.out.println(loginDTO);
        System.out.println(ResponseEntity.ok(isValid));
        return ResponseEntity.ok(isValid);
    }

    @PostMapping("/delete")
    public ResponseEntity<Void> deleteUser(@RequestBody UserDetailDTO userDetailDTO) {
        int user_id = userDetailDTO.getUser_id();
        System.out.println(user_id);
        userServiceImpl.deleteUser(user_id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/update")
    public ResponseEntity<Void> updateUser(@RequestBody UserDetailDTO userDetailDTO) {
        userServiceImpl.updateUser(userDetailDTO);
        return ResponseEntity.noContent().build();
    }

}
