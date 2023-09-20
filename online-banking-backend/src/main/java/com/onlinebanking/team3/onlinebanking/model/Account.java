package com.onlinebanking.team3.onlinebanking.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
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

//    @OneToOne(mappedBy = "account", cascade = CascadeType.ALL)
//    private @NonNull Address mailingAddress;
    
    @ManyToOne
    @JoinColumn(foreignKey = @ForeignKey(name = "user_id"), name = "user_id")
    private Address mailingAddress;

    private @NonNull double balance;

    private String transactionPassword;

//    @JsonIgnore
//    @JsonBackReference
    @ManyToOne
    @JoinColumn(foreignKey = @ForeignKey(name = "user_id"), name = "user_id")
    private User user;

	public Account(@NonNull String ifscCode, @NonNull Address mailingAddress, @NonNull double balance,
		User user) {
		super();
		this.ifscCode = ifscCode;
		this.mailingAddress = mailingAddress;
		this.balance = balance;
		this.user = user;
	}


}
