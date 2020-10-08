/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.Ebanking.entities;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author solid
 */
@Entity
public class TellerEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NotNull
    private int tellerID;
    @NotNull
    @Size(max = 30, message = "Your Email must under 30 character")
    private String email;
    @NotNull
    @Size(max = 30, message = "Address must under 50 character")
    private String address;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "bankID")
    private BankEntity bankEntity;
    @OneToOne
    @JoinColumn(name = "userID")
    private UserEntity userEntity1;

    public TellerEntity() {
    }

    public int getTellerID() {
        return tellerID;
    }

    public void setTellerID(int tellerID) {
        this.tellerID = tellerID;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public BankEntity getBankEntity() {
        return bankEntity;
    }

    public void setBankEntity(BankEntity bankEntity) {
        this.bankEntity = bankEntity;
    }

    public UserEntity getUserEntity1() {
        return userEntity1;
    }

    public void setUserEntity1(UserEntity userEntity1) {
        this.userEntity1 = userEntity1;
    }

}
