/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.Ebanking.service;

import com.example.Ebanking.entities.UserEntity;

/**
 *
 * @author solid
 */

public interface UserServiceIF {
    public UserEntity getUserByUserName(String username);
}
