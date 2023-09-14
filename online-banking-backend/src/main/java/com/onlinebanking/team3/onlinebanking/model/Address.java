package com.onlinebanking.team3.onlinebanking.model;

import jakarta.persistence.*;
import lombok.*;

@NoArgsConstructor
@RequiredArgsConstructor
@Getter
@Setter
@Entity
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long addressId;
    private @NonNull String permanentAddress;
    private @NonNull String city;
    private @NonNull String state;
    private int pincode;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    public Address(@NonNull String permanentAddress, @NonNull String city, @NonNull String state, int pincode) {
        this.permanentAddress = permanentAddress;
        this.city = city;
        this.state = state;
        this.pincode = pincode;
    }
}
