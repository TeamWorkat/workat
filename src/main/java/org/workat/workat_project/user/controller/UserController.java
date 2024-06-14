package org.workat.workat_project.user.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.workat.workat_project.user.entity.LoginDTO;
import org.workat.workat_project.user.entity.UserDetailDTO;
import org.workat.workat_project.user.service.UserServiceImpl;


@Controller
@CrossOrigin
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserServiceImpl userServiceImpl;

    @GetMapping("/detail")
    public ResponseEntity<UserDetailDTO> getUserDetail(@RequestParam(name= "user_id") int user_id) {
        return ResponseEntity.ok(userServiceImpl.getUserDetail(user_id));
    }

    @PostMapping("/check-password")
    public ResponseEntity<Boolean> checkPassword(@RequestBody LoginDTO loginDTO) {
        boolean isValid = userServiceImpl.checkPassword(loginDTO.getUser_id(), loginDTO.getUser_pwd());
        System.out.println(loginDTO);
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

}