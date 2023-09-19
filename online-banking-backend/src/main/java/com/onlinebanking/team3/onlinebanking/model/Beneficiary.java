package com.onlinebanking.team3.onlinebanking.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name = "Beneficiary")

public class Beneficiary {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long bid;

    private @NonNull String ifscCode;

    private @NonNull Long accountNo;

    @ManyToOne
    @JoinColumn(foreignKey = @ForeignKey(name = "uid"),name = "uid")
    private User user;

    @OneToMany(mappedBy = "toBeneficiary", cascade = CascadeType.ALL)
    private List<Transaction> transactions = new ArrayList<>();

    public Beneficiary() {

    }

    public Beneficiary(long bid, @NonNull String ifscCode, @NonNull Long accountNo, User user, List<Transaction> transactions) {
        this.bid = bid;
        this.ifscCode = ifscCode;
        this.accountNo = accountNo;
        this.user = user;
        this.transactions = transactions;
    }

    public long getBid() {
        return bid;
    }

    public void setBid(long bid) {
        this.bid = bid;
    }

    public String getIfscCode() {
        return ifscCode;
    }

    public void setIfscCode(String ifscCode) {
        this.ifscCode = ifscCode;
    }

    public Long getAccountNo() {
        return accountNo;
    }

    public void setAccountNo(Long accountNo) {
        this.accountNo = accountNo;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }

    public void setTransactions(List<Transaction> transactions) {
        this.transactions = transactions;
    }
}
