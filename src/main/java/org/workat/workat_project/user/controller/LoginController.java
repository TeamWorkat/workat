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

    @PostMapping("/check-email")
    public ResponseEntity<Boolean> checkEmail(@RequestBody Map<String, String> request) {
        String user_email = request.get("user_email");
        String role = request.get("role");
        boolean exists = userServiceImpl.isEmailAlreadyExists(user_email, role);
        return ResponseEntity.ok(exists);
    }

    @PostMapping("/signup")
    public ResponseEntity<Void> createUser(@RequestBody UserDetailDTO userDetailDTO) {
        userServiceImpl.createUser(userDetailDTO);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/login")
    public ResponseEntity<?> userLogin(@RequestBody Map<String, String> params, HttpServletResponse res) {
        UserVO user = userServiceImpl.checkLoginInfo(params.get("user_email"), params.get("role"));

        if (user != null && userServiceImpl.checkPassword(params.get("user_pwd"), user.getUser_email())) {
            System.out.println("Controller 비밀번호"+params.get("user_pwd"));
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