package com.onlinebanking.team3.onlinebanking.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import org.springframework.web.bind.annotation.GetMapping;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "users")
public class User {
    @SequenceGenerator(name = "user_seq", initialValue = 100, allocationSize = 1)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "user_seq")
    private long uid;

    @Column(nullable = false)
    private String firstName;

    private String middleName;

    @Column(nullable = false)
    private String lastName;

    @Column(nullable = false)
    private String gender;

    private String fatherName;

    @Column(unique = true)
    private String phoneNumber;

    @Column(unique = true)
    private String emailId;

    @Column(unique = true)
    private String panNumber;

    @Column(unique = true)
    private String aadharNumber;

    @JsonFormat(pattern = "dd-MM-yyyy")
    private String dob;

    @Column(nullable = false)
    private String occupation;

    private String sourceOfIncome;

    private String grossAnnualIncome;

    private String loginPassword;

    @Column(nullable = false)
    private boolean kyc;

    @ManyToOne
    @JoinColumn(name = "residentialAddress_id")
    private Address residentialAddress;

    @ManyToOne
    @JoinColumn(name = "permanentAddress_id")
    private Address permanentAddress;

    public User() {

    }

    public User(long uid, String firstName, String middleName, String lastName, String phoneNumber, String emailId,
            String panNumber, String aadharNumber, String dob, String occupation, String sourceOfIncome,
            String grossAnnualIncome, String gender, String loginPassword, Boolean kyc, String fatherName,
            Address residentialAddress, Address permanentAddress) {
        this.uid = uid;
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.gender = gender;
        this.fatherName = fatherName;
        this.phoneNumber = phoneNumber;
        this.emailId = emailId;
        this.panNumber = panNumber;
        this.aadharNumber = aadharNumber;
        this.dob = dob;
        this.occupation = occupation;
        this.sourceOfIncome = sourceOfIncome;
        this.grossAnnualIncome = grossAnnualIncome;
        this.loginPassword = loginPassword;
        this.kyc = kyc;
        this.residentialAddress = residentialAddress;
        this.permanentAddress = permanentAddress;
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

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getFatherName() {
        return fatherName;
    }

    public void setFatherName(String fatherName) {
        this.fatherName = fatherName;
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

    public String getSourceOfIncome() {
        return sourceOfIncome;
    }

    public void setSourceOfIncome(String sourceOfIncome) {
        this.sourceOfIncome = sourceOfIncome;
    }

    public String getGrossAnnualIncome() {
        return grossAnnualIncome;
    }

    public void setGrossAnnualIncome(String grossAnnualIncome) {
        this.grossAnnualIncome = grossAnnualIncome;
    }

    public String getLoginPassword() {
        return loginPassword;
    }

    public void setLoginPassword(String loginPassword) {
        Base64.Encoder encoder = Base64.getEncoder();
        String normalString = loginPassword;
        String encodedString = encoder.encodeToString( // encrypt password in database field
                normalString.getBytes(StandardCharsets.UTF_8));
        this.loginPassword = encodedString;
    }

    public Boolean getKyc() {
        return kyc;
    }

    public void setKyc(Boolean kyc) {
        this.kyc = kyc;
    }

    public Address getResidentialAddress() {
        return residentialAddress;
    }

    public void setResidentialAddress(Address residentialAddress) {
        this.residentialAddress = residentialAddress;
    }

    public Address getPermanentAddress() {
        return permanentAddress;
    }

    public void setPermanentAddress(Address permanentAddress) {
        this.permanentAddress = permanentAddress;
    }
}
