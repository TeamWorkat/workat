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

        try {
            if (jwtUtil.isExpired(token)) {
                System.out.println("Token is expired");
                response.sendError(HttpServletResponse.SC_FORBIDDEN, "Token is expired");
                return;
            }

            String user_email = jwtUtil.getUsername(token);
            String role = jwtUtil.getRole(token);

            UserVO userVO = new UserVO();
            userVO.setUser_email(user_email);
            userVO.setRole(role);
            CustomUserDetails customUserDetails = new CustomUserDetails(userVO);

            System.out.println("USER ROLE: " + role);

            Authentication authentication = new UsernamePasswordAuthenticationToken(customUserDetails, null, customUserDetails.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authentication);
        } catch (Exception e) {
            System.out.println("Token validation error: " + e.getMessage());
            response.sendError(HttpServletResponse.SC_FORBIDDEN, "Invalid token");
            return;
        }

        filterChain.doFilter(request, response);
    }
}
