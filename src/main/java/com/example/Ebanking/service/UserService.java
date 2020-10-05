/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.Ebanking.service;

import com.example.Ebanking.entities.Customer;
import java.util.Arrays;
import static net.bytebuddy.matcher.ElementMatchers.is;
import static net.bytebuddy.matcher.ElementMatchers.is;
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
public class UserService implements UserDetailsService {

    @Autowired
    CustomerService customerService;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        Customer customer = customerService.getCustomerByUserName(userName);
        if(customer == null){
            System.out.println("username"+userName);
            throw new UsernameNotFoundException("van a");
        }
        BCryptPasswordEncoder encoder = passwordEncoder();
        GrantedAuthority authority = new SimpleGrantedAuthority(customer.getRole().getRoleType());
        UserDetails userDetails = (UserDetails) new User(customer.getUserName(),
                encoder.encode(customer.getPassword()), Arrays.asList(authority));
        return userDetails;
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
