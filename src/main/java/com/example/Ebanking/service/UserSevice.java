/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.Ebanking.service;

import com.example.Ebanking.entities.UserEntity;
import com.example.Ebanking.repository.UserRepositoryIF;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author solid
 */
@Service
public class UserSevice implements UserServiceIF {

    @Autowired
    UserRepositoryIF userRepositoryIF;

    @Override
    public UserEntity getUserByUserName(String username) {
        System.out.println(username);
        Optional<UserEntity> userOptional = userRepositoryIF.findByUserName(username);
        return userOptional.isPresent() ? userOptional.get() : null;
    }

}
