/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.Ebanking.service;

import com.example.Ebanking.entities.UserEntity;
import java.util.Arrays;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

/**
 *
 * @author solid
 */
@Service
public class UserServiceSecurity implements UserDetailsService {

    @Autowired
    UserSevice userSevice;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        UserEntity userEntity = userSevice.getUserByUserName(userName);
        if (userEntity == null) {
            System.out.println("load user name is null" + userName);
            throw new UsernameNotFoundException("van a");
        }
        BCryptPasswordEncoder encoder = passwordEncoder();
        GrantedAuthority authority = new SimpleGrantedAuthority(userEntity.getRoleType());
        UserDetails userDetails = (UserDetails) new User(userEntity.getUserName(),
                encoder.encode(userEntity.getPassword()), Arrays.asList(authority));
        System.out.println("load user name not null" + userName);
        return userDetails;
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
