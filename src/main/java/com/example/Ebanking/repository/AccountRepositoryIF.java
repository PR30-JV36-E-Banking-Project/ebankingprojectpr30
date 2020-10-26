/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.Ebanking.repository;

import com.example.Ebanking.entities.AccountEntity;
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
public interface AccountRepositoryIF extends CrudRepository<AccountEntity, Integer>{
    public Optional<AccountEntity> findByAccountID(int ID);
    public Optional<AccountEntity> findByAccountType(String accountType);
    @Query("SELECT p FROM AccountEntity p WHERE p.accountType LIKE %?1%"
            + " OR p.status LIKE %?1%")
    public List<AccountEntity> search(String keyword);
}
