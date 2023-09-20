package com.onlinebanking.team3.onlinebanking.model;

import jakarta.persistence.*;
import lombok.*;

@NoArgsConstructor
@RequiredArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "Address")
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long addressId;
    private @NonNull String street;
    private @NonNull String city;
    private @NonNull String state;
    private @NonNull int pincode;
    
    
	public Address(long addressId, @NonNull String street, @NonNull String city, @NonNull String state,
			@NonNull int pincode) {
		super();
		this.addressId = addressId;
		this.street = street;
		this.city = city;
		this.state = state;
		this.pincode = pincode;
	}
    
//    @OneToOne
//    @JoinColumn(name = "user_id")
//    private User user;
//    
//    @OneToOne
//    @JoinColumn(name = "account_id")
//    private Account account;

//	public Address(long addressId, @NonNull String street, @NonNull String city, @NonNull String state,
//			@NonNull int pincode, User user, Account account) {
//		super();
//		this.addressId = addressId;
//		this.street = street;
//		this.city = city;
//		this.state = state;
//		this.pincode = pincode;
//		this.user = user;
//		this.account = account;
//	}
    
    
	
}
