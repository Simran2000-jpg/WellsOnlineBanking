package com.onlinebanking.team3.onlinebanking.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS", timezone = "UTC")
    private LocalDateTime transactionDateTime;
    private BigDecimal amount;

    @ManyToOne
    @JoinColumn(name = "from_account_id")
    private Account fromAccount; // Foreign key reference to the Account entity

    @ManyToOne
    @JoinColumn(name = "to_beneficiary_id")
    private Beneficiary toBeneficiary;

    public Transaction() {

    }

    public Transaction(LocalDateTime transactionDateTime, BigDecimal amount, Account fromAccount, Beneficiary toBeneficiary) {
        this.transactionDateTime = transactionDateTime;
        this.amount = amount;
        this.fromAccount = fromAccount;
        this.toBeneficiary = toBeneficiary;
    }

    public Transaction(Long id, LocalDateTime transactionDateTime, BigDecimal amount, Account fromAccount, Beneficiary toBeneficiary) {
        this.id = id;
        this.transactionDateTime = transactionDateTime;
        this.amount = amount;
        this.fromAccount = fromAccount;
        this.toBeneficiary = toBeneficiary;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getTransactionDateTime() {
        return transactionDateTime;
    }

    public void setTransactionDateTime(LocalDateTime transactionDateTime) {
        this.transactionDateTime = transactionDateTime;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public Account getFromAccount() {
        return fromAccount;
    }

    public void setFromAccount(Account fromAccount) {
        this.fromAccount = fromAccount;
    }

    public Beneficiary getToBeneficiary() {
        return toBeneficiary;
    }

    public void setToBeneficiary(Beneficiary toBeneficiary) {
        this.toBeneficiary = toBeneficiary;
    }
}
