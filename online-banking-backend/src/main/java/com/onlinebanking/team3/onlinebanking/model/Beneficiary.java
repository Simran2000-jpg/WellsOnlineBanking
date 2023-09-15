package com.onlinebanking.team3.onlinebanking.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

@NoArgsConstructor
@RequiredArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "beneficiaries")
public class Beneficiary {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long bId;

    private String ifscCode;

    private @NonNull String accountNo;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(foreignKey = @ForeignKey(name = "user_id"), name = "user_id")
    private User user;

    public Beneficiary(@NonNull String accountNo, String ifscCode) {
        this.accountNo = accountNo;
        this.ifscCode = ifscCode;

    }
}
