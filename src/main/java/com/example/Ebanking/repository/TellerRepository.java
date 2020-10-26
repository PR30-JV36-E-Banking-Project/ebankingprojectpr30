/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.Ebanking.repository;

import com.example.Ebanking.entities.TellerEntity;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Hoang Duy Nhat
 */
@Repository
public interface TellerRepository extends  CrudRepository<TellerEntity, Integer>{
    @Query("SELECT p FROM TellerEntity p WHERE p.address LIKE %?1%"
            + " OR p.email LIKE %?1%")
    public List<TellerEntity> search(String keyword);
    @Query("SELECT COUNT(tellerID) FROM TellerEntity")
    public int getNumberOfTellers();
}
