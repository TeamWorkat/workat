package org.workat.workat_project.jwt;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.filter.OncePerRequestFilter;
import org.workat.workat_project.user.entity.CustomUserDetails;
import org.workat.workat_project.user.entity.UserVO;
import org.workat.workat_project.user.repository.UserMapper;
import org.workat.workat_project.user.service.CustomUserDetailsService;
import org.workat.workat_project.user.service.UserService;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

public class JWTFilter extends OncePerRequestFilter {

    private final JWTUtil jwtUtil;
    private final CustomUserDetailsService customUserDetailsService;
    private final UserMapper userMapper;

    public JWTFilter(JWTUtil jwtUtil, CustomUserDetailsService customUserDetailsService, UserMapper userMapper) {
        this.jwtUtil = jwtUtil;
        this.customUserDetailsService = customUserDetailsService;
        this.userMapper = userMapper;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        String token = jwtUtil.resolveToken(request);
        System.out.println("JWT 필터******** JWT Token: " + token);

        if (token != null && jwtUtil.validateToken(token)) {
            String username = jwtUtil.getUsername(token);
            System.out.println("JWT 필터******** Username from token: " + username);

            UserVO userVO = userMapper.findUserByEmail(username);
            System.out.println("JWT 필터******** UserVO from DB: " + userVO);

            if (userVO == null) {
                throw new UsernameNotFoundException("JWT 필터******** User not found with email: " + username);
            }

            String role = "ROLE_" + userVO.getRole();
            List<SimpleGrantedAuthority> authorities = Collections.singletonList(new SimpleGrantedAuthority(role));

            CustomUserDetails customUserDetails = new CustomUserDetails(userVO.getUser_email(), userVO.getUser_pwd(), authorities);

            UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(customUserDetails, null, customUserDetails.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(auth);
        }

        filterChain.doFilter(request, response);
    }
}
