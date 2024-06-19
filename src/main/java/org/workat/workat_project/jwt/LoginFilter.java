package org.workat.workat_project.jwt;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.workat.workat_project.user.entity.CustomUserDetails;
import org.workat.workat_project.user.entity.LoginDTO;
import org.workat.workat_project.user.entity.UserVO;
import org.workat.workat_project.user.repository.UserMapper;

import java.io.IOException;

public class LoginFilter extends UsernamePasswordAuthenticationFilter {

    private final AuthenticationManager authenticationManager;
    private final JWTUtil jwtUtil;
    private final UserMapper userMapper;

    public LoginFilter(AuthenticationManager authenticationManager, JWTUtil jwtUtil, UserMapper userMapper) {
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
        this.userMapper = userMapper;
        setFilterProcessesUrl("/login");
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        try {
            System.out.println("Attempting authentication");
            LoginDTO loginDTO = new ObjectMapper().readValue(request.getInputStream(), LoginDTO.class);
            System.out.println("LoginDTO: " + loginDTO);

            String email = loginDTO.getUser_email();
            String password = loginDTO.getUser_pwd();
            String role = loginDTO.getRole();
            System.out.println("Email: " + email + ", Role: " + role);

            UserVO userVO = userMapper.checkLoginInfo(email, role);
            System.out.println("UserVO: " + userVO);

            if (userVO == null) {
                System.out.println("User not found or role mismatch");
                throw new AuthenticationServiceException("Invalid email, password, or role");
            }

            if (!new BCryptPasswordEncoder().matches(password, userVO.getUser_pwd())) {
                System.out.println("Password mismatch");
                throw new AuthenticationServiceException("Invalid email, password, or role");
            }

            // Check if the roles match
            if (!role.equals(userVO.getRole())) {
                System.out.println("Role mismatch: Expected " + role + " but got " + userVO.getRole());
                throw new AuthenticationServiceException("Invalid email, password, or role");
            }

            UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(email, password);
            return authenticationManager.authenticate(authToken);
        } catch (IOException e) {
            System.out.println("Failed to parse authentication request body: " + e.getMessage());
            throw new AuthenticationServiceException("Failed to parse authentication request body", e);
        }
    }


    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authentication) throws IOException, ServletException {
        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
        String username = userDetails.getUsername();
        String role = userDetails.getAuthorities().iterator().next().getAuthority();

        String token = jwtUtil.createJwt(username, role, 86400000L);

        response.setStatus(HttpServletResponse.SC_OK);
        response.setContentType("application/json");
        response.getWriter().write("{\"token\":\"" + token + "\"}");
    }

    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed) throws IOException {
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.setContentType("application/json");
        response.getWriter().write("{\"message\":\"Login failed: " + failed.getMessage() + "\"}");
    }
}
