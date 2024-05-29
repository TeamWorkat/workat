package org.workat.workat_project.user.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.workat.workat_project.user.entity.UserListDTO;
import org.workat.workat_project.user.service.UserServiceImpl;

import java.util.List;

@Controller
@CrossOrigin
@RequiredArgsConstructor
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private UserServiceImpl userServiceImpl;

    @GetMapping("/users")
    public ResponseEntity<List<UserListDTO>> getUserList() {
        return ResponseEntity.ok(userServiceImpl.getList());
    }
}