package com.onlinebanking.team3.onlinebanking.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
@NoArgsConstructor
@RequiredArgsConstructor
@Getter
@Setter
@Entity
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private @NonNull LocalDateTime transactionDateTime;
    private double amount;
    
    private @NonNull String transactionType;

//    @JsonBackReference
    @ManyToOne
    @JoinColumn(foreignKey = @ForeignKey(name = "aid"), name = "aid")
    private Account account; // Foreign key reference to the User entity

    @JsonBackReference
    @ManyToOne
    @JoinColumn(foreignKey = @ForeignKey(name = "bid"), name = "bid")
    private Beneficiary beneficiary;


    public Transaction(@NonNull LocalDateTime transactionDateTime, double amount) {
        this.transactionDateTime = transactionDateTime;
        this.amount = amount;
    }
}
