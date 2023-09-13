package com.onlinebanking.team3.onlinebanking.model;

import jakarta.persistence.*;
import lombok.*;

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

    private @NonNull double balance;

    private String transactionPassword;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public Account(@NonNull String ifscCode, @NonNull String mailingAddress, @NonNull double balance, String transactionPassword) {
        this.ifscCode = ifscCode;
        this.mailingAddress = mailingAddress;
        this.balance = balance;
        this.transactionPassword = transactionPassword;
    }
}
