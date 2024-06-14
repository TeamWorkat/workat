package org.workat.workat_project.jwt;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;
import org.workat.workat_project.user.entity.CustomUserDetails;
import org.workat.workat_project.user.entity.UserVO;
import org.workat.workat_project.user.service.UserServiceImpl;

import java.io.IOException;

public class JWTFilter extends OncePerRequestFilter {
    private final JWTUtil jwtUtil;

    public JWTFilter(JWTUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        String authorization = request.getHeader("Authorization");
        System.out.println("Authorization header: " + authorization);

        if (authorization == null || !authorization.startsWith("Bearer ")) {
            System.out.println("Authorization header is null or does not start with 'Bearer '");
            filterChain.doFilter(request, response);
            return;
        }

        String token = authorization.split(" ")[1];

        if (jwtUtil.isExpired(token)) {
            System.out.println("Token is expired");
            filterChain.doFilter(request, response);
            return;
        }

        String user_email = jwtUtil.getUsername(token);
        String role = jwtUtil.getRole(token);

        UserVO userVO = new UserVO();
        userVO.setUser_email(user_email);
        userVO.setRole(role);
        CustomUserDetails customUserDetails = new CustomUserDetails(userVO);

        System.out.println(customUserDetails);

        Authentication authtoken = new UsernamePasswordAuthenticationToken(customUserDetails, null, customUserDetails.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authtoken);
        filterChain.doFilter(request, response);
    }
}