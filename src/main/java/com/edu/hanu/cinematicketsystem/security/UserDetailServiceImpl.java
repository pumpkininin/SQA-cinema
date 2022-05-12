package com.edu.hanu.cinematicketsystem.security;

import com.edu.hanu.cinematicketsystem.model.User;
import com.edu.hanu.cinematicketsystem.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailServiceImpl implements UserDetailsService{

    @Autowired
    private UserService userService;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //Login with username or email
        User user = userService.findByUsername(username);

        //return custom UserDetail
        return new UserDetail(user);
    }

}
