package com.onlinebanking.team3.onlinebanking.service;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.onlinebanking.team3.onlinebanking.exception.UserNotFoundException;
import com.onlinebanking.team3.onlinebanking.model.Address;
import com.onlinebanking.team3.onlinebanking.model.User;
import com.onlinebanking.team3.onlinebanking.repository.UserRepository;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {UserService.class})
@ExtendWith(SpringExtension.class)
class UserServiceTest {
    @MockBean
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    /**
     * Method under test: {@link UserService#deleteUser(Long)}
     */
    @Test
    void testDeleteUser() {
        // Arrange
        Address permanentAddress = new Address();
        permanentAddress.setAddress("42 Main St");
        permanentAddress.setAddressId(1L);
        permanentAddress.setCity("Oxford");
        permanentAddress.setPincode(1);
        permanentAddress.setState("MD");

        Address residentialAddress = new Address();
        residentialAddress.setAddress("42 Main St");
        residentialAddress.setAddressId(1L);
        residentialAddress.setCity("Oxford");
        residentialAddress.setPincode(1);
        residentialAddress.setState("MD");

        User user = new User();
        user.setAadharNumber("42");
        user.setDob("Dob");
        user.setEmailId("42");
        user.setFatherName("Father Name");
        user.setFirstName("Jane");
        user.setGender("Gender");
        user.setGrossAnnualIncome("Gross Annual Income");
        user.setKyc(true);
        user.setLastName("Doe");
        user.setLoginPassword("iloveyou");
        user.setMiddleName("Middle Name");
        user.setOccupation("Occupation");
        user.setPanNumber("42");
        user.setPermanentAddress(permanentAddress);
        user.setPhoneNumber("6625550144");
        user.setResidentialAddress(residentialAddress);
        user.setSourceOfIncome("Source Of Income");
        user.setUid(1L);
        Optional<User> ofResult = Optional.of(user);
        doNothing().when(userRepository).deleteById(Mockito.<Long>any());
        when(userRepository.findById(Mockito.<Long>any())).thenReturn(ofResult);

        // Act
        userService.deleteUser(1L);

        // Assert that nothing has changed
        verify(userRepository).findById(Mockito.<Long>any());
        verify(userRepository).deleteById(Mockito.<Long>any());
    }

    /**
     * Method under test: {@link UserService#deleteUser(Long)}
     */
    @Test
    void testDeleteUser2() {
        // Arrange
        Address permanentAddress = new Address();
        permanentAddress.setAddress("42 Main St");
        permanentAddress.setAddressId(1L);
        permanentAddress.setCity("Oxford");
        permanentAddress.setPincode(1);
        permanentAddress.setState("MD");

        Address residentialAddress = new Address();
        residentialAddress.setAddress("42 Main St");
        residentialAddress.setAddressId(1L);
        residentialAddress.setCity("Oxford");
        residentialAddress.setPincode(1);
        residentialAddress.setState("MD");

        User user = new User();
        user.setAadharNumber("42");
        user.setDob("Dob");
        user.setEmailId("42");
        user.setFatherName("Father Name");
        user.setFirstName("Jane");
        user.setGender("Gender");
        user.setGrossAnnualIncome("Gross Annual Income");
        user.setKyc(true);
        user.setLastName("Doe");
        user.setLoginPassword("iloveyou");
        user.setMiddleName("Middle Name");
        user.setOccupation("Occupation");
        user.setPanNumber("42");
        user.setPermanentAddress(permanentAddress);
        user.setPhoneNumber("6625550144");
        user.setResidentialAddress(residentialAddress);
        user.setSourceOfIncome("Source Of Income");
        user.setUid(1L);
        Optional<User> ofResult = Optional.of(user);
        doThrow(new UserNotFoundException("An error occurred")).when(userRepository).deleteById(Mockito.<Long>any());
        when(userRepository.findById(Mockito.<Long>any())).thenReturn(ofResult);

        // Act and Assert
        assertThrows(UserNotFoundException.class, () -> userService.deleteUser(1L));
        verify(userRepository).findById(Mockito.<Long>any());
        verify(userRepository).deleteById(Mockito.<Long>any());
    }

    /**
     * Method under test: {@link UserService#deleteUser(Long)}
     */
    @Test
    void testDeleteUser3() {
        // Arrange
        Optional<User> emptyResult = Optional.empty();
        when(userRepository.findById(Mockito.<Long>any())).thenReturn(emptyResult);

        // Act and Assert
        assertThrows(UserNotFoundException.class, () -> userService.deleteUser(1L));
        verify(userRepository).findById(Mockito.<Long>any());
    }
}

