/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.Ebanking.service;

import com.example.Ebanking.entities.ConfirmationToken;
import com.example.Ebanking.entities.UserEntity;
import com.example.Ebanking.repository.UserRepositoryIF;
import java.text.MessageFormat;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 *
 * @author solid
 */
@Service
public class UserSevice implements UserServiceIF {

    @Autowired
    UserRepositoryIF userRepositoryIF;
    
    @Autowired
    ConfirmationTokenService confirmationTokenService;
    
    @Override
    public UserEntity getUserByUserName(String username) {
        System.out.println(username);
        Optional<UserEntity> userOptional = userRepositoryIF.findByUserName(username);
        return userOptional.isPresent() ? userOptional.get() : null;
    }

    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        final Optional<User> optionalUser = userRepositoryIF.findByEmail(email);

        if (optionalUser.isPresent()) {
            return optionalUser.get();
        } else {
            throw new UsernameNotFoundException(MessageFormat.format("User with email {0} cannot be found.", email));
        }
    }
    
//    void signUpUser(User user) {
//
//	final User createdUser = userRepositoryIF.save(user);
//
//	final ConfirmationToken confirmationToken = new ConfirmationToken(user);
//
//	confirmationTokenService.saveConfirmationToken(confirmationToken);

//}

}
