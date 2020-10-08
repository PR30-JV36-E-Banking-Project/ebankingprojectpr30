/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.Ebanking.repository;

import com.example.Ebanking.entities.ConfirmationToken;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author Hoang Duy Nhat
 */
public interface ConfirmationTokenRepository extends CrudRepository<ConfirmationToken, Long> {
    
}
