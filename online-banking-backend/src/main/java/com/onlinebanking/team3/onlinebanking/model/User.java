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
    @SequenceGenerator(name="user_seq",initialValue = 100,allocationSize = 1)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY,generator = "user_seq")
    private long uid;

    @Column(nullable = false)
    private String firstName;

    private String middleName;

    @Column(nullable = false)
    private String lastName;

    @Column(unique = true)
    private String phoneNumber;

    @Column(unique = true)
    private String emailId;

    @Column(unique = true)
    private String panNumber;

    @Column(unique = true)
    private String aadharNumber;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private String dob;

    @Column(nullable = false)
    private String occupation;

    @Column(nullable = false)
    private String gender;

    private String loginPassword;

    @Column(nullable = false)
    private String kyc;

    @OneToMany(mappedBy = "user",cascade = CascadeType.ALL)
    private List<Account> accounts = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "residentialAddress_id")
    private Address residentialAddress;

    @ManyToOne
    @JoinColumn(name = "permanentAddress_id")
    private Address permanentAddress;

    @OneToMany(mappedBy = "user",cascade = CascadeType.ALL)
    private List<Beneficiary> beneficiaries = new ArrayList<>();


    public User() {

    }

    public User(long uid, String firstName, String middleName, String lastName, String phoneNumber, String emailId, String panNumber, String aadharNumber, String dob, String occupation, String gender, String loginPassword, String kyc, List<Account> accounts, Address residentialAddress, Address permanentAddress, List<Beneficiary> beneficiaries) {
        this.uid = uid;
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.emailId = emailId;
        this.panNumber = panNumber;
        this.aadharNumber = aadharNumber;
        this.dob = dob;
        this.occupation = occupation;
        this.gender = gender;
        this.loginPassword = loginPassword;
        this.kyc = kyc;
        this.accounts = accounts;
        this.residentialAddress = residentialAddress;
        this.permanentAddress = permanentAddress;
        this.beneficiaries = beneficiaries;
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
        Base64.Encoder encoder = Base64.getEncoder();
        String normalString = loginPassword;
        String encodedString = encoder.encodeToString(   // encrypt password in database field
                normalString.getBytes(StandardCharsets.UTF_8) );
        this.loginPassword = encodedString;
    }

    public String getKyc() {
        return kyc;
    }

    public void setKyc(String kyc) {
        this.kyc = kyc;
    }

    public List<Account> getAccounts() {
        return accounts;
    }

    public void setAccounts(List<Account> accounts) {
        this.accounts = accounts;
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

    public List<Beneficiary> getBeneficiaries() {
        return beneficiaries;
    }

    public void setBeneficiaries(List<Beneficiary> beneficiaries) {
        this.beneficiaries = beneficiaries;
    }
}
