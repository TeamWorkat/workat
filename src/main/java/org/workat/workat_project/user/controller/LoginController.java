package org.workat.workat_project.user.controller;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.workat.workat_project.jwt.JWTUtil;
import org.workat.workat_project.user.entity.UserDetailDTO;
import org.workat.workat_project.user.entity.UserVO;
import org.workat.workat_project.user.service.UserServiceImpl;
import java.util.Map;

@Controller
@CrossOrigin
@RequiredArgsConstructor
@ResponseBody
public class LoginController {

    private final UserServiceImpl userServiceImpl;
    private final JWTUtil jwtUtil;

    @PostMapping("/signup")
    public ResponseEntity<Void> createUser(@RequestBody UserDetailDTO userDetailDTO) {
        userServiceImpl.createUser(userDetailDTO);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Map<String, String> params, HttpServletResponse res) {
        UserVO user = userServiceImpl.findUserByEmail(params.get("user_email"));

        if (user != null && userServiceImpl.checkPassword(user.getUser_id(), params.get("user_pwd"))) {
            String token = jwtUtil.createJwt(user.getUser_email(), user.getRole(), 86400000L);

            Cookie cookie = new Cookie("token", token);
            cookie.setHttpOnly(true);
            cookie.setPath("/");
            res.addCookie(cookie);
            return new ResponseEntity<>(user.getUser_id(), HttpStatus.OK);
        }

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid email or password");
    }
}