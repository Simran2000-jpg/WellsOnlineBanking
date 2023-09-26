package com.onlinebanking.team3.onlinebanking.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

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
    private @NonNull String name;

    @ManyToOne
    // @JsonBackReference
    @JoinColumn(foreignKey = @ForeignKey(name = "uid"), name = "uid")
    private User user;

    // @JsonBackReference
    // @OneToMany(mappedBy = "beneficiary", cascade = CascadeType.ALL)
    // private List<Transaction> transactions = new ArrayList<>();

    public Beneficiary(String ifscCode, @NonNull String accountNo, @NonNull String name) {
        this.ifscCode = ifscCode;
        this.accountNo = accountNo;
        this.name = name;
    }

}
