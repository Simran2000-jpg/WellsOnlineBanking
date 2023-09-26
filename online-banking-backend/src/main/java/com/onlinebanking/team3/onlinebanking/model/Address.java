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
    private @NonNull String address;
    private @NonNull String city;
    private @NonNull String state;
    private int pincode;

    public Address(@NonNull String address, @NonNull String city, @NonNull String state, int pincode) {
        this.address = address;
        this.city = city;
        this.state = state;
        this.pincode = pincode;
    }
}
