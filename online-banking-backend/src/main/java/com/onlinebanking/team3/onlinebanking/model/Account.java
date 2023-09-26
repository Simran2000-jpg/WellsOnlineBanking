package com.onlinebanking.team3.onlinebanking.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Base64;
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

    private Boolean isActive;

    @OneToOne
    @JoinColumn(name = "mailingAddress_id")
    private Address mailingAddress;


    private double balance;

    @Setter(AccessLevel.NONE) 
    private String transactionPassword;

    @ManyToOne
    @JoinColumn(foreignKey = @ForeignKey(name = "user_id"), name = "user_id")
    private User user;

    public Account(@NonNull String ifscCode, Address mailingAddress, double balance, Boolean isActive ,User user) {
        this.ifscCode = ifscCode;
        this.mailingAddress = mailingAddress;
        this.balance = balance;
        this.isActive = isActive;
        this.user = user;
    }
    
    public void setTransactionPassword(String transactionPassword) {
        Base64.Encoder encoder = Base64.getEncoder();
        String normalString = transactionPassword;
        String encodedString = encoder.encodeToString(   // encrypt password in database field
                normalString.getBytes(StandardCharsets.UTF_8) );
        this.transactionPassword= encodedString;
    }

    
}
