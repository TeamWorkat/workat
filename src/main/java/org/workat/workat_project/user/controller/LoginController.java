package org.workat.workat_project.user.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.workat.workat_project.user.entity.LoginDTO;
import org.workat.workat_project.user.entity.UserDetailDTO;
import org.workat.workat_project.user.service.UserServiceImpl;

@Controller
@CrossOrigin
@RequiredArgsConstructor
@ResponseBody
public class LoginController {

    private final UserServiceImpl userServiceImpl;

    @PostMapping("/signup")
    public ResponseEntity<Void> createUser(@RequestBody UserDetailDTO userDetailDTO) {
        userServiceImpl.createUser(userDetailDTO);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/login")
    public ResponseEntity<Boolean> login(@RequestBody LoginDTO loginDTO){
        System.out.println(loginDTO);
        boolean isValid = userServiceImpl.checkLoginInfo(loginDTO.getUser_email(), loginDTO.getUser_pwd());
        return ResponseEntity.ok(isValid);
    }
}