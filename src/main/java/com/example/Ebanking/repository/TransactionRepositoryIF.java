/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.Ebanking.repository;

import com.example.Ebanking.entities.TransactionEntity;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author solid
 */
@Repository
public interface TransactionRepositoryIF extends CrudRepository<TransactionEntity, Integer> {

    public Optional<TransactionEntity> findByTransactionID(int id);

    @Query(value = "SELECT * FROM ebankingdbtest123.transaction_entity where (transaction_date between ?1 and ?2) and sender_accountid=?3", nativeQuery = true)
    public List<TransactionEntity> getTransactionWhereDateBetweenValue(Date start, Date end, double id);
    
    @Query(value = "SELECT * FROM ebankingdbtest123.transaction_entity where (transaction_date between ?1 and ?2)", nativeQuery = true)
    public List<TransactionEntity> getTransactionWhereDateBetweenValue(Date start, Date end);

    @Query("SELECT p FROM TransactionEntity p WHERE p.content LIKE %?1%"
            + " OR p.transactionType LIKE %?1%")
    public List<TransactionEntity> search(String keyword);

    @Query("SELECT SUM(amount) FROM TransactionEntity")
    public int getAmountOfTransactions();
}
