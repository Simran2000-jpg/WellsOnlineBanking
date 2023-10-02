package com.onlinebanking.team3.onlinebanking.service;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.onlinebanking.team3.onlinebanking.exception.UserNotFoundException;
import com.onlinebanking.team3.onlinebanking.model.Account;
import com.onlinebanking.team3.onlinebanking.model.Address;
import com.onlinebanking.team3.onlinebanking.model.User;
import com.onlinebanking.team3.onlinebanking.repository.AccountRepository;
import com.onlinebanking.team3.onlinebanking.repository.UserRepository;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {AccountService.class})
@ExtendWith(SpringExtension.class)
class AccountServiceTest {
    @MockBean
    private AccountRepository accountRepository;

    @Autowired
    private AccountService accountService;

    @MockBean
    private UserRepository userRepository;

    /**
     * Method under test: {@link AccountService#updateAccount(Account)}
     */
    @Test
    void testUpdateAccount() {
        // TODO: Complete this test.
        //   Reason: R002 Missing observers.
        //   Diffblue Cover was unable to create an assertion.
        //   Add getters for the following fields or make them package-private:
        //     AccountService.accountRepository
        //     AccountService.userRepository

        // Arrange
        AccountService accountService = new AccountService();

        Address mailingAddress = new Address();
        mailingAddress.setAddress("42 Main St");
        mailingAddress.setAddressId(1L);
        mailingAddress.setCity("Oxford");
        mailingAddress.setPincode(1);
        mailingAddress.setState("MD");

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

        Account fromAccount = new Account();
        fromAccount.setAccountNo(1234567890L);
        fromAccount.setAccountType("3");
        fromAccount.setBalance(10.0d);
        fromAccount.setIfscCode("Ifsc Code");
        fromAccount.setIsActive(true);
        fromAccount.setMailingAddress(mailingAddress);
        fromAccount.setTransactionPassword("iloveyou");
        fromAccount.setUser(user);

        // Act
        accountService.updateAccount(fromAccount);
    }

    /**
     * Method under test: {@link AccountService#addNewAccount(Long)}
     */
    @Test
    void testAddNewAccount() {
        // Arrange
        Address mailingAddress = new Address();
        mailingAddress.setAddress("42 Main St");
        mailingAddress.setAddressId(1L);
        mailingAddress.setCity("Oxford");
        mailingAddress.setPincode(1);
        mailingAddress.setState("MD");

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

        Account account = new Account();
        account.setAccountNo(1234567890L);
        account.setAccountType("3");
        account.setBalance(10.0d);
        account.setIfscCode("Ifsc Code");
        account.setIsActive(true);
        account.setMailingAddress(mailingAddress);
        account.setTransactionPassword("iloveyou");
        account.setUser(user);
        when(accountRepository.save(Mockito.<Account>any())).thenReturn(account);

        Address permanentAddress2 = new Address();
        permanentAddress2.setAddress("42 Main St");
        permanentAddress2.setAddressId(1L);
        permanentAddress2.setCity("Oxford");
        permanentAddress2.setPincode(1);
        permanentAddress2.setState("MD");

        Address residentialAddress2 = new Address();
        residentialAddress2.setAddress("42 Main St");
        residentialAddress2.setAddressId(1L);
        residentialAddress2.setCity("Oxford");
        residentialAddress2.setPincode(1);
        residentialAddress2.setState("MD");

        User user2 = new User();
        user2.setAadharNumber("42");
        user2.setDob("Dob");
        user2.setEmailId("42");
        user2.setFatherName("Father Name");
        user2.setFirstName("Jane");
        user2.setGender("Gender");
        user2.setGrossAnnualIncome("Gross Annual Income");
        user2.setKyc(true);
        user2.setLastName("Doe");
        user2.setLoginPassword("iloveyou");
        user2.setMiddleName("Middle Name");
        user2.setOccupation("Occupation");
        user2.setPanNumber("42");
        user2.setPermanentAddress(permanentAddress2);
        user2.setPhoneNumber("6625550144");
        user2.setResidentialAddress(residentialAddress2);
        user2.setSourceOfIncome("Source Of Income");
        user2.setUid(1L);
        Optional<User> ofResult = Optional.of(user2);
        when(userRepository.findById(Mockito.<Long>any())).thenReturn(ofResult);

        // Act and Assert
        assertSame(account, accountService.addNewAccount(1L));
        verify(accountRepository).save(Mockito.<Account>any());
        verify(userRepository).findById(Mockito.<Long>any());
    }

    /**
     * Method under test: {@link AccountService#addNewAccount(Long)}
     */
    @Test
    void testAddNewAccount2() {
        // Arrange
        when(accountRepository.save(Mockito.<Account>any())).thenThrow(new UserNotFoundException("An error occurred"));

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
        when(userRepository.findById(Mockito.<Long>any())).thenReturn(ofResult);

        // Act and Assert
        assertThrows(UserNotFoundException.class, () -> accountService.addNewAccount(1L));
        verify(accountRepository).save(Mockito.<Account>any());
        verify(userRepository).findById(Mockito.<Long>any());
    }
}

