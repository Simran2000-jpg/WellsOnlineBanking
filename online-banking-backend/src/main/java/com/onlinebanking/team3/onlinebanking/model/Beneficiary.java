package com.onlinebanking.team3.onlinebanking.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

@NoArgsConstructor
@RequiredArgsConstructor
@Getter
@Setter
@Entity

public class Beneficiary {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long bid;

    private String ifscCode;
    private @NonNull String accountNo;

    @ManyToOne
    @JsonBackReference
    @JoinColumn(foreignKey = @ForeignKey(name = "uid"),name = "uid")
    private User user;

    public Beneficiary(String ifscCode, @NonNull String accountNo) {
        this.ifscCode = ifscCode;
        this.accountNo = accountNo;
    }
}
