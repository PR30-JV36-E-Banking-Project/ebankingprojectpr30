/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.Ebanking.service;

import com.example.Ebanking.entities.ConfirmationToken;
import com.example.Ebanking.entities.UserEntity;
import com.example.Ebanking.repository.ConfirmationTokenRepository;
import com.example.Ebanking.repository.UserRepositoryIF;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Hoang Duy Nhat
 */
@Service
public class ConfirmationTokenService {
    
    @Autowired
    UserRepositoryIF userRepositoryIF;
    
    @Autowired
    ConfirmationTokenService confirmationTokenService;
    
    @Autowired
    ConfirmationTokenRepository confirmationTokenRepository;

    void saveConfirmationToken(ConfirmationToken confirmationToken) {

        confirmationTokenRepository.save(confirmationToken);
    }

    void deleteConfirmationToken(Long id) {

        confirmationTokenRepository.deleteById(id);
    }

//    void confirmUser(ConfirmationToken confirmationToken) {
//
//        final UserEntity userEntity = confirmationToken.getUserEntity();
//
//        userRepositoryIF.save(userEntity);
//
//        confirmationTokenService.deleteConfirmationToken(confirmationToken.getId());
//
//    }

    public Optional<ConfirmationToken> findConfirmationTokenByToken(String token) {
        return confirmationTokenRepository.findConfirmationTokenByConfirmationToken(token);
    }
}
