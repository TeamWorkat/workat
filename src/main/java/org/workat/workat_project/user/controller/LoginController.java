package org.workat.workat_project.user.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.workat.workat_project.user.entity.LoginDTO;
import org.workat.workat_project.user.service.UserServiceImpl;

@Controller
@CrossOrigin
@RequiredArgsConstructor
@RequestMapping("/user")
public class LoginController {

    private final UserServiceImpl userServiceImpl;

    @PostMapping("/login")
    public ResponseEntity<Boolean> login(@RequestBody LoginDTO loginDTO){
        System.out.println(loginDTO);
        boolean isValid = userServiceImpl.checkLoginInfo(loginDTO.getUser_email(), loginDTO.getUser_pwd());
        return ResponseEntity.ok(isValid);
    }
}