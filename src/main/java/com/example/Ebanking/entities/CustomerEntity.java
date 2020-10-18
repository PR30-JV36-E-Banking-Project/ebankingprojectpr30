/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.Ebanking.entities;

import java.time.LocalDate;
import java.util.Date;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;
import org.springframework.format.annotation.DateTimeFormat;

/**
 *
 * @author solid
 */
@Entity
public class CustomerEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @NotNull
    private int customerID;
//    @NotNull
//    @Size(max = 30, message = "FullName must under 30 character")
    private String fullName;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date birthDay;
//    @NotNull
    private String sex;
//    @NotNull
//    @Size(max = 50, message = "Adrress must under 50 character")
    private String address;
//    @NotNull
    private String country;
//    @NotNull
    private String nationlaty;
//    @NotNull
    private String district;
//    @NotNull
    private String city;
//    @NotNull
//    @Size(max = 11, message = "Phone must under 11 number")
    private String phone;
//    @NotNull
    private String email;
//    @NotNull
    private String IDcard;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate dateIssueIDCard;
//    @NotNull
    private String issueIDCardOffice;
    private boolean status;

    @OneToMany(mappedBy = "customerEntity")
    private Set<AccountEntity> accountEntitys;

    @OneToOne(cascade = CascadeType.ALL)
    @NotFound(action = NotFoundAction.IGNORE)
    @JoinColumn(name = "userID")
    private UserEntity userEntity;

    public int getCustomerID() {
        return customerID;
    }

    public void setCustomerID(int customerID) {
        this.customerID = customerID;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public Date getBirthDay() {
        return birthDay;
    }

    public void setBirthDay(Date birthDay) {
        this.birthDay = birthDay;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getNationlaty() {
        return nationlaty;
    }

    public void setNationlaty(String nationlaty) {
        this.nationlaty = nationlaty;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getIDcard() {
        return IDcard;
    }

    public void setIDcard(String IDcard) {
        this.IDcard = IDcard;
    }

    public LocalDate getDateIssueIDCard() {
        return dateIssueIDCard;
    }

    public void setDateIssueIDCard(LocalDate dateIssueIDCard) {
        this.dateIssueIDCard = dateIssueIDCard;
    }

    public String getIssueIDCardOffice() {
        return issueIDCardOffice;
    }

    public void setIssueIDCardOffice(String issueIDCardOffice) {
        this.issueIDCardOffice = issueIDCardOffice;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public Set<AccountEntity> getAccountEntitys() {
        return accountEntitys;
    }

    public void setAccountEntitys(Set<AccountEntity> accountEntitys) {
        this.accountEntitys = accountEntitys;
    }

    public UserEntity getUserEntity() {
        return userEntity;
    }

    public void setUserEntity(UserEntity userEntity) {
        this.userEntity = userEntity;
    }

    public CustomerEntity() {
    }

}
