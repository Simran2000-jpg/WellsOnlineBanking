package com.onlinebanking.team3.onlinebanking.service;

import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.anyDouble;
import static org.mockito.Mockito.anyLong;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.onlinebanking.team3.onlinebanking.exception.UserNotFoundException;
import com.onlinebanking.team3.onlinebanking.model.Account;
import com.onlinebanking.team3.onlinebanking.model.Address;
import com.onlinebanking.team3.onlinebanking.model.User;
import com.onlinebanking.team3.onlinebanking.repository.AccountRepository;
import com.onlinebanking.team3.onlinebanking.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;

import java.util.ArrayList;
import java.util.List;

import java.util.Optional;

import org.junit.jupiter.api.Disabled;

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
     * Methods under test:
     *
     * <ul>
     *   <li>default or parameterless constructor of {@link AccountService}
     *   <li>{@link AccountService#updateAccount(Account)}
     * </ul>
     */
    @Test
    void testConstructor() {
        AccountService actualAccountService = new AccountService();
        actualAccountService.updateAccount(new Account("Ifsc Code"));
        assertNull(actualAccountService.accountRepository);
        assertNull(actualAccountService.userRepository);
    }

    /**
     * Method under test: {@link AccountService#createAccount(Account)}
     */
    @Test
    void testCreateAccount() {
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
        account.setBalance(10.0d);
        account.setIfscCode("Ifsc Code");
        account.setIsActive(true);
        account.setMailingAddress(mailingAddress);
        account.setTransactionPassword("iloveyou");
        account.setUser(user);
        when(accountRepository.save(Mockito.<Account>any())).thenReturn(account);

        Address mailingAddress2 = new Address();
        mailingAddress2.setAddress("42 Main St");
        mailingAddress2.setAddressId(1L);
        mailingAddress2.setCity("Oxford");
        mailingAddress2.setPincode(1);
        mailingAddress2.setState("MD");

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

        Account account2 = new Account();
        account2.setAccountNo(1234567890L);
        account2.setBalance(10.0d);
        account2.setIfscCode("Ifsc Code");
        account2.setIsActive(true);
        account2.setMailingAddress(mailingAddress2);
        account2.setTransactionPassword("iloveyou");
        account2.setUser(user2);
        assertSame(account, accountService.createAccount(account2));
        verify(accountRepository).save(Mockito.<Account>any());
    }

    /**
     * Method under test: {@link AccountService#createAccount(Account)}
     */
    @Test
    void testCreateAccount2() {
        Account account = new Account("Ifsc Code");
        when(accountRepository.save(Mockito.<Account>any())).thenReturn(account);
        assertSame(account, accountService.createAccount(new Account("Ifsc Code")));
        verify(accountRepository).save(Mockito.<Account>any());
    }

    /**
     * Method under test: {@link AccountService#createAccount(Account)}
     */
    @Test
    void testCreateAccount3() {
        when(accountRepository.save(Mockito.<Account>any())).thenThrow(new UserNotFoundException("An error occurred"));
        assertThrows(UserNotFoundException.class, () -> accountService.createAccount(new Account("Ifsc Code")));
        verify(accountRepository).save(Mockito.<Account>any());
    }

    /**
     * Method under test: {@link AccountService#getAccountById(Long)}
     */
    @Test
    void testGetAccountById() {
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
        account.setBalance(10.0d);
        account.setIfscCode("Ifsc Code");
        account.setIsActive(true);
        account.setMailingAddress(mailingAddress);
        account.setTransactionPassword("iloveyou");
        account.setUser(user);
        Optional<Account> ofResult = Optional.of(account);
        when(accountRepository.findById(Mockito.<Long>any())).thenReturn(ofResult);
        assertSame(account, accountService.getAccountById(1L));
        verify(accountRepository).findById(Mockito.<Long>any());
    }

    /**
     * Method under test: {@link AccountService#getAccountById(Long)}
     */
    @Test
    void testGetAccountById2() {
        Optional<Account> emptyResult = Optional.empty();
        when(accountRepository.findById(Mockito.<Long>any())).thenReturn(emptyResult);
        assertThrows(EntityNotFoundException.class, () -> accountService.getAccountById(1L));
        verify(accountRepository).findById(Mockito.<Long>any());
    }

    /**
     * Method under test: {@link AccountService#getAccountById(Long)}
     */
    @Test
    void testGetAccountById3() {
        when(accountRepository.findById(Mockito.<Long>any())).thenThrow(new UserNotFoundException("An error occurred"));
        assertThrows(UserNotFoundException.class, () -> accountService.getAccountById(1L));
        verify(accountRepository).findById(Mockito.<Long>any());
    }

    /**
     * Method under test: {@link AccountService#getAccountById(Long)}
     */
    @Test
    void testGetAccountById4() {
        Account account = new Account("Ifsc Code");
        when(accountRepository.findById(Mockito.<Long>any())).thenReturn(Optional.of(account));
        assertSame(account, accountService.getAccountById(1L));
        verify(accountRepository).findById(Mockito.<Long>any());
    }

    /**
     * Method under test: {@link AccountService#getAccountById(Long)}
     */
    @Test
    void testGetAccountById5() {
        when(accountRepository.findById(Mockito.<Long>any())).thenReturn(Optional.empty());
        assertThrows(EntityNotFoundException.class, () -> accountService.getAccountById(1L));
        verify(accountRepository).findById(Mockito.<Long>any());
    }

    /**
     * Method under test: {@link AccountService#getAccountsByUser(Long)}
     */
    @Test
    void testGetAccountsByUser() {
        ArrayList<Account> accountList = new ArrayList<>();
        when(accountRepository.findActiveAccountsForUser(Mockito.<Long>any())).thenReturn(accountList);
        List<Account> actualAccountsByUser = accountService.getAccountsByUser(1L);
        assertSame(accountList, actualAccountsByUser);
        assertTrue(actualAccountsByUser.isEmpty());
        verify(accountRepository).findActiveAccountsForUser(Mockito.<Long>any());
    }

    /**
     * Method under test: {@link AccountService#getAccountsByUser(Long)}
     */
    @Test
    void testGetAccountsByUser2() {
        when(accountRepository.findActiveAccountsForUser(Mockito.<Long>any()))
                .thenThrow(new UserNotFoundException("An error occurred"));
        assertThrows(UserNotFoundException.class, () -> accountService.getAccountsByUser(1L));
        verify(accountRepository).findActiveAccountsForUser(Mockito.<Long>any());
    }

    /**
     * Method under test: {@link AccountService#listAll()}
     */
    @Test
    void testListAll() {
        ArrayList<Account> accountList = new ArrayList<>();
        when(accountRepository.findAll()).thenReturn(accountList);
        List<Account> actualListAllResult = accountService.listAll();
        assertSame(accountList, actualListAllResult);
        assertTrue(actualListAllResult.isEmpty());
        verify(accountRepository).findAll();
    }

    /**
     * Method under test: {@link AccountService#listAll()}
     */
    @Test
    void testListAll2() {
        when(accountRepository.findAll()).thenThrow(new UserNotFoundException("An error occurred"));
        assertThrows(UserNotFoundException.class, () -> accountService.listAll());
        verify(accountRepository).findAll();
    }

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
        fromAccount.setBalance(10.0d);
        fromAccount.setIfscCode("Ifsc Code");
        fromAccount.setIsActive(true);
        fromAccount.setMailingAddress(mailingAddress);
        fromAccount.setTransactionPassword("iloveyou");
        fromAccount.setUser(user);
        accountService.updateAccount(fromAccount);
    }

    /**
     * Method under test: {@link AccountService#getActiveAccountsForUser(Long)}
     */
    @Test
    void testGetActiveAccountsForUser() {
        ArrayList<Account> accountList = new ArrayList<>();
        when(accountRepository.findActiveAccountsForUser(Mockito.<Long>any())).thenReturn(accountList);
        List<Account> actualActiveAccountsForUser = accountService.getActiveAccountsForUser(1L);
        assertSame(accountList, actualActiveAccountsForUser);
        assertTrue(actualActiveAccountsForUser.isEmpty());
        verify(accountRepository).findActiveAccountsForUser(Mockito.<Long>any());
    }

    /**
     * Method under test: {@link AccountService#getActiveAccountsForUser(Long)}
     */
    @Test
    void testGetActiveAccountsForUser2() {
        when(accountRepository.findActiveAccountsForUser(Mockito.<Long>any()))
                .thenThrow(new UserNotFoundException("An error occurred"));
        assertThrows(UserNotFoundException.class, () -> accountService.getActiveAccountsForUser(1L));
        verify(accountRepository).findActiveAccountsForUser(Mockito.<Long>any());
    }

    /**
     * Method under test: {@link AccountService#getAccountsForUser(Long)}
     */
    @Test
    void testGetAccountsForUser() {
        Optional<List<Account>> ofResult = Optional.of(new ArrayList<>());
        when(accountRepository.findAccountsForUser(Mockito.<Long>any())).thenReturn(ofResult);
        Optional<List<Account>> actualAccountsForUser = accountService.getAccountsForUser(1L);
        assertSame(ofResult, actualAccountsForUser);
        assertTrue(actualAccountsForUser.isPresent());
        verify(accountRepository).findAccountsForUser(Mockito.<Long>any());
    }

    /**
     * Method under test: {@link AccountService#getAccountsForUser(Long)}
     */
    @Test
    void testGetAccountsForUser2() {
        when(accountRepository.findAccountsForUser(Mockito.<Long>any()))
                .thenThrow(new UserNotFoundException("An error occurred"));
        assertThrows(UserNotFoundException.class, () -> accountService.getAccountsForUser(1L));
        verify(accountRepository).findAccountsForUser(Mockito.<Long>any());
    }

    /**
     * Method under test: {@link AccountService#addNewAccount(Long)}
     */
    @Test
    void testAddNewAccount() {
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
        assertSame(account, accountService.addNewAccount(1L));
        verify(accountRepository).save(Mockito.<Account>any());
        verify(userRepository).findById(Mockito.<Long>any());
    }

    /**
     * Method under test: {@link AccountService#addNewAccount(Long)}
     */
    @Test
    void testAddNewAccount2() {
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
        assertThrows(UserNotFoundException.class, () -> accountService.addNewAccount(1L));
        verify(accountRepository).save(Mockito.<Account>any());
        verify(userRepository).findById(Mockito.<Long>any());
    }

    /**
     * Method under test: {@link AccountService#addNewAccount(Long)}
     */
    @Test
    void testAddNewAccount3() {
        Account account = new Account("Ifsc Code");
        when(accountRepository.save(Mockito.<Account>any())).thenReturn(account);
        when(userRepository.findById(Mockito.<Long>any())).thenReturn(Optional.of(new User()));
        assertSame(account, accountService.addNewAccount(1L));
        verify(accountRepository).save(Mockito.<Account>any());
        verify(userRepository).findById(Mockito.<Long>any());
    }

    /**
     * Method under test: {@link AccountService#addNewAccount(Long)}
     */
    @Test
    void testAddNewAccount4() {
        when(accountRepository.save(Mockito.<Account>any())).thenThrow(new UserNotFoundException("An error occurred"));
        when(userRepository.findById(Mockito.<Long>any())).thenReturn(Optional.of(new User()));
        assertThrows(UserNotFoundException.class, () -> accountService.addNewAccount(1L));
        verify(accountRepository).save(Mockito.<Account>any());
        verify(userRepository).findById(Mockito.<Long>any());
    }

    /**
     * Method under test: {@link AccountService#addNewAccount(Long)}
     */
    @Test
    void testAddNewAccount5() {
        User user = mock(User.class);
        when(user.getResidentialAddress()).thenThrow(new UserNotFoundException("An error occurred"));
        Optional<User> ofResult = Optional.of(user);
        when(userRepository.findById(Mockito.<Long>any())).thenReturn(ofResult);
        assertThrows(UserNotFoundException.class, () -> accountService.addNewAccount(1L));
        verify(userRepository).findById(Mockito.<Long>any());
        verify(user).getResidentialAddress();
    }

    /**
     * Method under test: {@link AccountService#addNewAccount(Long)}
     */
    @Test
    void testAddNewAccount6() {
        when(userRepository.findById(Mockito.<Long>any())).thenReturn(Optional.empty());
        new UserNotFoundException("An error occurred");
        assertThrows(UserNotFoundException.class, () -> accountService.addNewAccount(1L));
        verify(userRepository).findById(Mockito.<Long>any());
    }

    /**
     * Method under test: {@link AccountService#updateActiveStatus(Long)}
     */
    @Test
    void testUpdateActiveStatus() {
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
        account.setBalance(10.0d);
        account.setIfscCode("Ifsc Code");
        account.setIsActive(true);
        account.setMailingAddress(mailingAddress);
        account.setTransactionPassword("iloveyou");
        account.setUser(user);
        Optional<Account> ofResult = Optional.of(account);

        Address mailingAddress2 = new Address();
        mailingAddress2.setAddress("42 Main St");
        mailingAddress2.setAddressId(1L);
        mailingAddress2.setCity("Oxford");
        mailingAddress2.setPincode(1);
        mailingAddress2.setState("MD");

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

        Account account2 = new Account();
        account2.setAccountNo(1234567890L);
        account2.setBalance(10.0d);
        account2.setIfscCode("Ifsc Code");
        account2.setIsActive(true);
        account2.setMailingAddress(mailingAddress2);
        account2.setTransactionPassword("iloveyou");
        account2.setUser(user2);
        when(accountRepository.save(Mockito.<Account>any())).thenReturn(account2);
        when(accountRepository.findById(Mockito.<Long>any())).thenReturn(ofResult);
        assertSame(account2, accountService.updateActiveStatus(1L));
        verify(accountRepository).save(Mockito.<Account>any());
        verify(accountRepository).findById(Mockito.<Long>any());
    }

    /**
     * Method under test: {@link AccountService#updateActiveStatus(Long)}
     */
    @Test
    void testUpdateActiveStatus2() {
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
        account.setBalance(10.0d);
        account.setIfscCode("Ifsc Code");
        account.setIsActive(true);
        account.setMailingAddress(mailingAddress);
        account.setTransactionPassword("iloveyou");
        account.setUser(user);
        Optional<Account> ofResult = Optional.of(account);
        when(accountRepository.save(Mockito.<Account>any())).thenThrow(new UserNotFoundException("An error occurred"));
        when(accountRepository.findById(Mockito.<Long>any())).thenReturn(ofResult);
        assertThrows(UserNotFoundException.class, () -> accountService.updateActiveStatus(1L));
        verify(accountRepository).save(Mockito.<Account>any());
        verify(accountRepository).findById(Mockito.<Long>any());
    }

    /**
     * Method under test: {@link AccountService#updateActiveStatus(Long)}
     */
    @Test
    void testUpdateActiveStatus3() {
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
        Account account = mock(Account.class);
        when(account.getIsActive()).thenReturn(true);
        doNothing().when(account).setAccountNo(anyLong());
        doNothing().when(account).setBalance(anyDouble());
        doNothing().when(account).setIfscCode(Mockito.<String>any());
        doNothing().when(account).setIsActive(Mockito.<Boolean>any());
        doNothing().when(account).setMailingAddress(Mockito.<Address>any());
        doNothing().when(account).setTransactionPassword(Mockito.<String>any());
        doNothing().when(account).setUser(Mockito.<User>any());
        account.setAccountNo(1234567890L);
        account.setBalance(10.0d);
        account.setIfscCode("Ifsc Code");
        account.setIsActive(true);
        account.setMailingAddress(mailingAddress);
        account.setTransactionPassword("iloveyou");
        account.setUser(user);
        Optional<Account> ofResult = Optional.of(account);

        Address mailingAddress2 = new Address();
        mailingAddress2.setAddress("42 Main St");
        mailingAddress2.setAddressId(1L);
        mailingAddress2.setCity("Oxford");
        mailingAddress2.setPincode(1);
        mailingAddress2.setState("MD");

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

        Account account2 = new Account();
        account2.setAccountNo(1234567890L);
        account2.setBalance(10.0d);
        account2.setIfscCode("Ifsc Code");
        account2.setIsActive(true);
        account2.setMailingAddress(mailingAddress2);
        account2.setTransactionPassword("iloveyou");
        account2.setUser(user2);
        when(accountRepository.save(Mockito.<Account>any())).thenReturn(account2);
        when(accountRepository.findById(Mockito.<Long>any())).thenReturn(ofResult);
        assertSame(account2, accountService.updateActiveStatus(1L));
        verify(accountRepository).save(Mockito.<Account>any());
        verify(accountRepository).findById(Mockito.<Long>any());
        verify(account).getIsActive();
        verify(account).setAccountNo(anyLong());
        verify(account).setBalance(anyDouble());
        verify(account).setIfscCode(Mockito.<String>any());
        verify(account, atLeast(1)).setIsActive(Mockito.<Boolean>any());
        verify(account).setMailingAddress(Mockito.<Address>any());
        verify(account).setTransactionPassword(Mockito.<String>any());
        verify(account).setUser(Mockito.<User>any());
    }

    /**
     * Method under test: {@link AccountService#updateActiveStatus(Long)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testUpdateActiveStatus4() {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.NullPointerException: Cannot invoke "java.lang.Boolean.booleanValue()" because the return value of "com.onlinebanking.team3.onlinebanking.model.Account.getIsActive()" is null
        //       at com.onlinebanking.team3.onlinebanking.service.AccountService.updateActiveStatus(AccountService.java:67)
        //   See https://diff.blue/R013 to resolve this issue.

        when(accountRepository.findById(Mockito.<Long>any())).thenReturn(Optional.of(new Account("Ifsc Code")));
        accountService.updateActiveStatus(1L);
    }

    /**
     * Method under test: {@link AccountService#updateActiveStatus(Long)}
     */
    @Test
    void testUpdateActiveStatus5() {
        Account account = new Account("Ifsc Code");
        account.setIsActive(true);
        Optional<Account> ofResult = Optional.of(account);
        Account account2 = new Account("Ifsc Code");
        when(accountRepository.save(Mockito.<Account>any())).thenReturn(account2);
        when(accountRepository.findById(Mockito.<Long>any())).thenReturn(ofResult);
        assertSame(account2, accountService.updateActiveStatus(1L));
        verify(accountRepository).save(Mockito.<Account>any());
        verify(accountRepository).findById(Mockito.<Long>any());
    }

    /**
     * Method under test: {@link AccountService#updateActiveStatus(Long)}
     */
    @Test
    void testUpdateActiveStatus6() {
        Account account = mock(Account.class);
        when(account.getIsActive()).thenReturn(true);
        doNothing().when(account).setIsActive(Mockito.<Boolean>any());
        account.setIsActive(true);
        Optional<Account> ofResult = Optional.of(account);
        Account account2 = new Account("Ifsc Code");
        when(accountRepository.save(Mockito.<Account>any())).thenReturn(account2);
        when(accountRepository.findById(Mockito.<Long>any())).thenReturn(ofResult);
        assertSame(account2, accountService.updateActiveStatus(1L));
        verify(accountRepository).save(Mockito.<Account>any());
        verify(accountRepository).findById(Mockito.<Long>any());
        verify(account).getIsActive();
        verify(account, atLeast(1)).setIsActive(Mockito.<Boolean>any());
    }

    /**
     * Method under test: {@link AccountService#updateActiveStatus(Long)}
     */
    @Test
    void testUpdateActiveStatus7() {
        Account account = mock(Account.class);
        when(account.getIsActive()).thenReturn(false);
        doNothing().when(account).setIsActive(Mockito.<Boolean>any());
        account.setIsActive(true);
        Optional<Account> ofResult = Optional.of(account);
        Account account2 = new Account("Ifsc Code");
        when(accountRepository.save(Mockito.<Account>any())).thenReturn(account2);
        when(accountRepository.findById(Mockito.<Long>any())).thenReturn(ofResult);
        assertSame(account2, accountService.updateActiveStatus(1L));
        verify(accountRepository).save(Mockito.<Account>any());
        verify(accountRepository).findById(Mockito.<Long>any());
        verify(account).getIsActive();
        verify(account, atLeast(1)).setIsActive(Mockito.<Boolean>any());
    }

    /**
     * Method under test: {@link AccountService#updateActiveStatus(Long)}
     */
    @Test
    void testUpdateActiveStatus8() {
        when(accountRepository.findById(Mockito.<Long>any())).thenReturn(Optional.empty());
        assertThrows(UserNotFoundException.class, () -> accountService.updateActiveStatus(1L));
        verify(accountRepository).findById(Mockito.<Long>any());
    }
}

