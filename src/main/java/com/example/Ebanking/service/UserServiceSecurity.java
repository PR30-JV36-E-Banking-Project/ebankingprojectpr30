/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.Ebanking.service;

import com.example.Ebanking.entities.ConfirmationToken;
import com.example.Ebanking.entities.CustomerEntity;
import com.example.Ebanking.entities.TellerEntity;
import com.example.Ebanking.entities.UserEntity;
import com.example.Ebanking.repository.CustomerRepositoryIF;
import com.example.Ebanking.repository.TellerRepository;
import com.example.Ebanking.repository.UserRepositoryIF;
import java.util.Arrays;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 *
 * @author solid
 */
@Service
public class UserServiceSecurity implements UserDetailsService {

    @Autowired
    UserSevice userSevice;

    @Autowired
    ConfirmationTokenService confirmationTokenService;

    @Autowired
    UserRepositoryIF userRepositoryIF;
    
    @Autowired
    CustomerRepositoryIF customerRepositoryIF;
    
    @Autowired
    TellerRepository tellerRepository;
    
    @Autowired
    EmailService emailService;
    
    @Autowired
    private PasswordEncoder passwordEncoder;

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
                userEntity.getPassword(), Arrays.asList(authority));
        System.out.println("load user name not null" + userName);
        return userDetails;
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    public void signUpUser(UserEntity userEntity, CustomerEntity customerEntity) {

        final String encryptedPassword = passwordEncoder.encode(userEntity.getPassword());
        
        userEntity.setPassword(encryptedPassword);
        
        userEntity.setRoleType("ROLE_USER");
        
//        CustomerEntity linkingCustomer = userEntity.getCustomerEntity();
//        
//        userEntity.setUserID(linkingCustomer.getUserEntity().getUserID());
        
        userRepositoryIF.save(userEntity);
        
        customerRepositoryIF.save(customerEntity);
        
        final ConfirmationToken confirmationToken = new ConfirmationToken(userEntity);

        confirmationTokenService.saveConfirmationToken(confirmationToken);

        String userMail = userEntity.getEmail();

        String token = confirmationToken.getConfirmationToken();

        sendConfirmationMail(userMail, token);
    }

    void sendConfirmationMail(String userMail, String token) {
        emailService.sendTokenMessage(userMail, "Mail Confirmation Link!", "Thank you for registering. Please click on the below link to activate your account." + "http://localhost:8082/account/confirm?token="
                + token);
    }

    public void confirmUser(ConfirmationToken confirmationToken) {

        UserEntity userEntity = confirmationToken.getUserEntity();
        CustomerEntity customerEntity = userEntity.getCustomerEntity();
        userEntity.setIsActived(true);

        userRepositoryIF.save(userEntity);
        customerRepositoryIF.save(customerEntity);
        confirmationTokenService.deleteConfirmationToken(confirmationToken.getId());

    }
    
    public void addNewTeller(UserEntity userEntity, TellerEntity tellerEntity) {

        BCryptPasswordEncoder bCryptPasswordEncoder = passwordEncoder();

        final String encryptedPassword = bCryptPasswordEncoder.encode(userEntity.getPassword());
        
        userEntity.setPassword(encryptedPassword);
        
        userEntity.setRoleType("ROLE_TELLER");
        userEntity.setIsActived(true);
        userRepositoryIF.save(userEntity);
        
        tellerRepository.save(tellerEntity);
    }
    
    public String getBCryptPassword(UserEntity userEntity){
        BCryptPasswordEncoder bCryptPasswordEncoder = passwordEncoder();

        String encryptedPassword = bCryptPasswordEncoder.encode(userEntity.getPassword());
        
        return encryptedPassword;  
    }
    
    public void setBCryptPassword(UserEntity userEntity){
        BCryptPasswordEncoder bCryptPasswordEncoder = passwordEncoder();

        String encryptedPassword = bCryptPasswordEncoder.encode(userEntity.getPassword());
        
        userEntity.setPassword(encryptedPassword);
    }
    
    public void updatePassword(UserEntity currentUser, String password) {
        BCryptPasswordEncoder bCryptPasswordEncoder = passwordEncoder();

        currentUser.setPassword(bCryptPasswordEncoder.encode(password));
        
        userRepositoryIF.save(currentUser);
    }
}
