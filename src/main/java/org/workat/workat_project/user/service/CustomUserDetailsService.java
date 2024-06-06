package org.workat.workat_project.user.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.workat.workat_project.user.entity.CustomUserDetails;
import org.workat.workat_project.user.entity.UserVO;
import org.workat.workat_project.user.repository.UserMapper;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UserMapper userMapper;

    public CustomUserDetailsService(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    @Override
    public UserDetails loadUserByUsername(String user_email) throws UsernameNotFoundException {
        UserVO userdata = userMapper.findUserByEmail(user_email);

        if (userdata == null) {
            throw new UsernameNotFoundException("User not found with email: " + user_email);
        }

        return new CustomUserDetails(userdata);
    }

}
