package org.workat.workat_project.user.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.workat.workat_project.user.entity.CustomUserDetails;
import org.workat.workat_project.user.entity.UserVO;
import org.workat.workat_project.user.repository.UserMapper;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UserMapper userMapper;

    public CustomUserDetailsService(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    @Override
    public UserDetails loadUserByUsername(String user_email) throws UsernameNotFoundException {
        System.out.println("******Custom User Details Servie ******** Loading user by email: " + user_email);
        UserVO userdata = userMapper.findUserByEmail(user_email);
        System.out.println("******Custom User Details Servie ******** User data from DB: " + userdata);

        if (userdata == null) {
            throw new UsernameNotFoundException("******Custom User Details Servie ******** User not found with email: " + user_email);
        }

        String role = "ROLE_" + userdata.getRole();
        System.out.println("******Custom User Details Servie ******** User role: " + role);

        return new CustomUserDetails(userdata.getUser_email(), userdata.getUser_pwd(), Collections.singletonList(new SimpleGrantedAuthority(role)));
    }
}
