/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.Ebanking.service;

import com.example.Ebanking.entities.TellerEntity;
import com.example.Ebanking.repository.TellerRepository;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.ValidatorFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Hoang Duy Nhat
 */
@Service
public class TellerService implements TellerServiceIF{
    
    @Autowired
    TellerRepository tellerRepository;
    
    @Override
    public List<TellerEntity> getTellers() {
        return (List)tellerRepository.findAll();
    }

    @Override
    public void saveTeller(TellerEntity theTeller) {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Set<ConstraintViolation<TellerEntity>> violations = factory.getValidator().validate(theTeller);
        if (violations.isEmpty()) {
            tellerRepository.save(theTeller);
        } else {
            System.out.println("validate wrong, do not execute database script");
        }
    }

    @Override
    public TellerEntity getTeller(int theId) {
        Optional<TellerEntity> tellerOpt = tellerRepository.findById(theId);
	return tellerOpt.isPresent() ? tellerOpt.get() : null;
    }

    @Override
    public void deleteTeller(int theId) {
        tellerRepository.deleteById(theId);
    }
    
}
