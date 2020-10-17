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
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;



@Entity
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int userID;
    @NotNull
    @Size(max = 30, message = "Your UserName must under 30 character")
    private String userName;
    @NotNull
    //@Size(max = 20, message = "Your Password must under 20 character")
    private String password;
    private String roleType;
    private String email;
    private boolean isActived;
    
    @OneToOne(mappedBy="userEntity",cascade = CascadeType.ALL)
    private CustomerEntity customerEntity;
    @OneToOne(mappedBy="userEntity1",cascade = CascadeType.ALL)
    private TellerEntity tellerEntity;

    public UserEntity() {
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public CustomerEntity getCustomerEntity() {
        return customerEntity;
    }

    public void setCustomerEntity(CustomerEntity customerEntity) {
        this.customerEntity = customerEntity;
    }

    public TellerEntity getTellerEntity() {
        return tellerEntity;
    }

    public void setTellerEntity(TellerEntity tellerEntity) {
        this.tellerEntity = tellerEntity;
    }

    public String getRoleType() {
        return roleType;
    }

    public void setRoleType(String roleType) {
        this.roleType = roleType;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isIsActived() {
        return isActived;
    }

    public void setIsActived(boolean isActived) {
        this.isActived = isActived;
    }
    
}
