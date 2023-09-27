package com.onlinebanking.team3.onlinebanking.service;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.onlinebanking.team3.onlinebanking.model.Address;
import com.onlinebanking.team3.onlinebanking.repository.AddressRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {AddressService.class})
@ExtendWith(SpringExtension.class)
class AddressServiceTest {
    @MockBean
    private AddressRepository addressRepository;

    @Autowired
    private AddressService addressService;

    /**
     * Method under test: {@link AddressService#saveAddress(Address)}
     */
    @Test
    void testSaveAddress() {
        Address address = new Address();
        address.setAddress("42 Main St");
        address.setAddressId(1L);
        address.setCity("Oxford");
        address.setPincode(1);
        address.setState("MD");
        when(addressRepository.save(Mockito.<Address>any())).thenReturn(address);

        Address address2 = new Address();
        address2.setAddress("42 Main St");
        address2.setAddressId(1L);
        address2.setCity("Oxford");
        address2.setPincode(1);
        address2.setState("MD");
        assertSame(address, addressService.saveAddress(address2));
        verify(addressRepository).save(Mockito.<Address>any());
    }
}

