package com.onlinebanking.team3.onlinebanking.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@RequiredArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "Account")
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long accountNo;

    private @NonNull String ifscCode;

    private @NonNull String mailingAddress;

    private @NonNull BigDecimal balance;

    private String transactionPassword;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(foreignKey = @ForeignKey(name = "user_id"), name = "user_id")
    private User user;

    @JsonIgnore
    @OneToMany(mappedBy = "fromAccount", cascade = CascadeType.ALL)
    private List<Transaction> transactions = new ArrayList<>();

    public Account(@NonNull String ifscCode, @NonNull String mailingAddress, @NonNull BigDecimal balance, String transactionPassword,List<Transaction> transactions) {
        this.ifscCode = ifscCode;
        this.mailingAddress = mailingAddress;
        this.balance = balance;
        this.transactionPassword = transactionPassword;
        this.transactions = transactions;
    }
}
