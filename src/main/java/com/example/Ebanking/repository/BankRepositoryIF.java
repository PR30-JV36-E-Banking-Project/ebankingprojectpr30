/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.Ebanking.repository;

import com.example.Ebanking.entities.BankEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Hoang Duy Nhat
 */
@Repository
public interface BankRepositoryIF extends CrudRepository<BankEntity, Double>{
    BankEntity findByBankID(int bankID);
}
