/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.Ebanking.repository;

import com.example.Ebanking.entities.TransactionEntity;
import java.util.Date;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author solid
 */
@Repository
public interface TransactionRepositoryIF extends CrudRepository<TransactionEntity, Integer>{
    @Query(value = "SELECT * FROM ebankingdbtest.transaction_entity where (transaction_date between ?1 and ?2) and receive_accountid=?3" ,nativeQuery=true)
    public List<TransactionEntity> getTransactionWhereDateBetweenValue(Date start,Date end,int id);
}
