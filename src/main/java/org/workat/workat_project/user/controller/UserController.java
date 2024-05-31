package org.workat.workat_project.user.controller;

import lombok.RequiredArgsConstructor;
import org.apache.ibatis.annotations.Update;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.workat.workat_project.user.entity.PasswordCheckDTO;
import org.workat.workat_project.user.entity.UserDetailDTO;
import org.workat.workat_project.user.entity.UserListDTO;
import org.workat.workat_project.user.service.UserServiceImpl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@CrossOrigin
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {

    private final UserServiceImpl userServiceImpl;

    @GetMapping("/detail")
    public ResponseEntity<UserDetailDTO> getUserDetail(@RequestParam(name= "user_id") int user_id) {
        System.out.println("Received user_id: " + user_id);
        UserDetailDTO userDetail = userServiceImpl.getUserDetail(user_id);
        System.out.println("Retrieved user detail: " + userDetail);
        return ResponseEntity.ok(userServiceImpl.getUserDetail(user_id));
    }

    @PostMapping("/check-password")
    public ResponseEntity<Boolean> checkPassword(@RequestBody PasswordCheckDTO passwordCheckDTO) {
        boolean isValid = userServiceImpl.checkPassword(passwordCheckDTO.getUser_id(), passwordCheckDTO.getUser_pwd());
        System.out.println(passwordCheckDTO);
        System.out.println(ResponseEntity.ok(isValid));
        return ResponseEntity.ok(isValid);
    }

    @DeleteMapping("/{user_id}")
    public ResponseEntity<Void> deleteUser(@PathVariable int user_id) {
        userServiceImpl.deleteUser(user_id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/update")
    public ResponseEntity<Void> updateUser(@RequestBody UserDetailDTO userDetailDTO) {
        userServiceImpl.updateUser(userDetailDTO);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/signup")
    public ResponseEntity<Void> createUser(@RequestBody UserDetailDTO userDetailDTO) {
        userServiceImpl.createUser(userDetailDTO);
        return ResponseEntity.noContent().build();
    }
}