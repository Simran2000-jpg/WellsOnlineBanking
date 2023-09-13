package com.onlinebanking.team3.onlinebanking.model;

import jakarta.persistence.*;
import org.springframework.web.bind.annotation.GetMapping;

@Entity
public class User {
//    private String firstName,lastName,phoneNumber,emailId,permanentAddress,city,state,pincode,
//    pan,dob,aadharNumber,occupation,middleName,gender,loginPassword,kyc;
    @SequenceGenerator(name="user_seq",initialValue = 100,allocationSize = 1)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY,generator = "user_seq")
    private long uid;


    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String middleName;

    @Column(nullable = false)
    private String lastName;

    @Column(nullable = false)
    private String phoneNumber;

    @Column(nullable = false)
    private String emailId;

    @Column(nullable = false)
    private String permanentAddress;

    @Column(nullable = false)
    private String city;

    @Column(nullable = false)
    private String state;

    @Column(nullable = false)
    private long pincode;

    @Column(nullable = false)
    private String panNumber;

    @Column(nullable = false)
    private String aadharNumber;

    @Column(nullable = false)
    private String dob;

    @Column(nullable = false)
    private String occupation;

    @Column(nullable = false)
    private String gender;

    @Column(nullable = false)
    private String loginPassword;

    @Column(nullable = false)
    private String kyc;

    public User() {

    }

    public long getUid() {
        return uid;
    }

    public void setUid(long uid) {
        this.uid = uid;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public String getPermanentAddress() {
        return permanentAddress;
    }

    public void setPermanentAddress(String permanentAddress) {
        this.permanentAddress = permanentAddress;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public long getPincode() {
        return pincode;
    }

    public void setPincode(long pincode) {
        this.pincode = pincode;
    }

    public String getPanNumber() {
        return panNumber;
    }

    public void setPanNumber(String panNumber) {
        this.panNumber = panNumber;
    }

    public String getAadharNumber() {
        return aadharNumber;
    }

    public void setAadharNumber(String aadharNumber) {
        this.aadharNumber = aadharNumber;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getOccupation() {
        return occupation;
    }

    public void setOccupation(String occupation) {
        this.occupation = occupation;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }


    public String getLoginPassword() {
        return loginPassword;
    }

    public void setLoginPassword(String loginPassword) {
        this.loginPassword = loginPassword;
    }

    public String getKyc() {
        return kyc;
    }

    public void setKyc(String kyc) {
        this.kyc = kyc;
    }

    public User(long uid, String firstName, String middleName, String lastName, String phoneNumber, String emailId, String permanentAddress, String city, String state, long pincode, String panNumber, String aadharNumber, String dob, String occupation, String gender, String loginPassword, String kyc) {
        this.uid = uid;
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.emailId = emailId;
        this.permanentAddress = permanentAddress;
        this.city = city;
        this.state = state;
        this.pincode = pincode;
        this.panNumber = panNumber;
        this.aadharNumber = aadharNumber;
        this.dob = dob;
        this.occupation = occupation;
        this.gender = gender;
        this.loginPassword = loginPassword;
        this.kyc = kyc;
    }


}
