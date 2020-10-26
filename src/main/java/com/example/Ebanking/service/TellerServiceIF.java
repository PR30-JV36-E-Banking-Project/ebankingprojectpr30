/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.Ebanking.service;

import com.example.Ebanking.entities.TellerEntity;
import java.util.List;

/**
 *
 * @author Hoang Duy Nhat
 */
public interface TellerServiceIF {
    public List <TellerEntity> getTellers(String keyword);
    public void saveTeller(TellerEntity theTeller);
    public TellerEntity getTeller(int theId);
    public void deleteTeller(int theId);
}
