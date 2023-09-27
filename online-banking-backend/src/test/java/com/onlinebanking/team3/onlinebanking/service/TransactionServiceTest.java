package com.onlinebanking.team3.onlinebanking.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.anyDouble;
import static org.mockito.Mockito.anyLong;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.onlinebanking.team3.onlinebanking.model.Account;
import com.onlinebanking.team3.onlinebanking.model.Address;
import com.onlinebanking.team3.onlinebanking.model.Transaction;
import com.onlinebanking.team3.onlinebanking.model.User;
import com.onlinebanking.team3.onlinebanking.repository.TransactionRepository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.hibernate.TransactionException;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {TransactionService.class})
@ExtendWith(SpringExtension.class)
class TransactionServiceTest {
    @MockBean
    private AccountService accountService;

    @MockBean
    private BeneficiaryService beneficiaryService;

    @MockBean
    private TransactionRepository transactionRepository;

    @Autowired
    private TransactionService transactionService;

    /**
     * Method under test: {@link TransactionService#createTransaction(Transaction)}
     */
    @Test
    void testCreateTransaction() {
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

        Account toAccount = new Account();
        toAccount.setAccountNo(1234567890L);
        toAccount.setBalance(10.0d);
        toAccount.setIfscCode("Ifsc Code");
        toAccount.setIsActive(true);
        toAccount.setMailingAddress(mailingAddress2);
        toAccount.setTransactionPassword("iloveyou");
        toAccount.setUser(user2);

        Transaction transaction = new Transaction();
        transaction.setAmount(10.0d);
        transaction.setFromAccount(fromAccount);
        transaction.setId(1L);
        transaction.setRemarks("Remarks");
        transaction.setToAccount(toAccount);
        transaction.setTransactionDateTime(LocalDate.of(1970, 1, 1).atStartOfDay());
        transaction.setTransactionType("Transaction Type");
        when(transactionRepository.save(Mockito.<Transaction>any())).thenReturn(transaction);

        Address mailingAddress3 = new Address();
        mailingAddress3.setAddress("42 Main St");
        mailingAddress3.setAddressId(1L);
        mailingAddress3.setCity("Oxford");
        mailingAddress3.setPincode(1);
        mailingAddress3.setState("MD");

        Address permanentAddress3 = new Address();
        permanentAddress3.setAddress("42 Main St");
        permanentAddress3.setAddressId(1L);
        permanentAddress3.setCity("Oxford");
        permanentAddress3.setPincode(1);
        permanentAddress3.setState("MD");

        Address residentialAddress3 = new Address();
        residentialAddress3.setAddress("42 Main St");
        residentialAddress3.setAddressId(1L);
        residentialAddress3.setCity("Oxford");
        residentialAddress3.setPincode(1);
        residentialAddress3.setState("MD");

        User user3 = new User();
        user3.setAadharNumber("42");
        user3.setDob("Dob");
        user3.setEmailId("42");
        user3.setFatherName("Father Name");
        user3.setFirstName("Jane");
        user3.setGender("Gender");
        user3.setGrossAnnualIncome("Gross Annual Income");
        user3.setKyc(true);
        user3.setLastName("Doe");
        user3.setLoginPassword("iloveyou");
        user3.setMiddleName("Middle Name");
        user3.setOccupation("Occupation");
        user3.setPanNumber("42");
        user3.setPermanentAddress(permanentAddress3);
        user3.setPhoneNumber("6625550144");
        user3.setResidentialAddress(residentialAddress3);
        user3.setSourceOfIncome("Source Of Income");
        user3.setUid(1L);

        Account fromAccount2 = new Account();
        fromAccount2.setAccountNo(1234567890L);
        fromAccount2.setBalance(10.0d);
        fromAccount2.setIfscCode("Ifsc Code");
        fromAccount2.setIsActive(true);
        fromAccount2.setMailingAddress(mailingAddress3);
        fromAccount2.setTransactionPassword("iloveyou");
        fromAccount2.setUser(user3);

        Address mailingAddress4 = new Address();
        mailingAddress4.setAddress("42 Main St");
        mailingAddress4.setAddressId(1L);
        mailingAddress4.setCity("Oxford");
        mailingAddress4.setPincode(1);
        mailingAddress4.setState("MD");

        Address permanentAddress4 = new Address();
        permanentAddress4.setAddress("42 Main St");
        permanentAddress4.setAddressId(1L);
        permanentAddress4.setCity("Oxford");
        permanentAddress4.setPincode(1);
        permanentAddress4.setState("MD");

        Address residentialAddress4 = new Address();
        residentialAddress4.setAddress("42 Main St");
        residentialAddress4.setAddressId(1L);
        residentialAddress4.setCity("Oxford");
        residentialAddress4.setPincode(1);
        residentialAddress4.setState("MD");

        User user4 = new User();
        user4.setAadharNumber("42");
        user4.setDob("Dob");
        user4.setEmailId("42");
        user4.setFatherName("Father Name");
        user4.setFirstName("Jane");
        user4.setGender("Gender");
        user4.setGrossAnnualIncome("Gross Annual Income");
        user4.setKyc(true);
        user4.setLastName("Doe");
        user4.setLoginPassword("iloveyou");
        user4.setMiddleName("Middle Name");
        user4.setOccupation("Occupation");
        user4.setPanNumber("42");
        user4.setPermanentAddress(permanentAddress4);
        user4.setPhoneNumber("6625550144");
        user4.setResidentialAddress(residentialAddress4);
        user4.setSourceOfIncome("Source Of Income");
        user4.setUid(1L);

        Account toAccount2 = new Account();
        toAccount2.setAccountNo(1234567890L);
        toAccount2.setBalance(10.0d);
        toAccount2.setIfscCode("Ifsc Code");
        toAccount2.setIsActive(true);
        toAccount2.setMailingAddress(mailingAddress4);
        toAccount2.setTransactionPassword("iloveyou");
        toAccount2.setUser(user4);

        Transaction transaction2 = new Transaction();
        transaction2.setAmount(10.0d);
        transaction2.setFromAccount(fromAccount2);
        transaction2.setId(1L);
        transaction2.setRemarks("Remarks");
        transaction2.setToAccount(toAccount2);
        transaction2.setTransactionDateTime(LocalDate.of(1970, 1, 1).atStartOfDay());
        transaction2.setTransactionType("Transaction Type");
        assertSame(transaction, transactionService.createTransaction(transaction2));
        verify(transactionRepository).save(Mockito.<Transaction>any());
    }

    /**
     * Method under test: {@link TransactionService#transferFunds(Long, Long, String, double, String, String)}
     */
    @Test
    void testTransferFunds() {
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
        doNothing().when(accountService).updateAccount(Mockito.<Account>any());
        when(accountService.getAccountById(Mockito.<Long>any())).thenReturn(account);

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

        Account fromAccount = new Account();
        fromAccount.setAccountNo(1234567890L);
        fromAccount.setBalance(10.0d);
        fromAccount.setIfscCode("Ifsc Code");
        fromAccount.setIsActive(true);
        fromAccount.setMailingAddress(mailingAddress2);
        fromAccount.setTransactionPassword("iloveyou");
        fromAccount.setUser(user2);

        Address mailingAddress3 = new Address();
        mailingAddress3.setAddress("42 Main St");
        mailingAddress3.setAddressId(1L);
        mailingAddress3.setCity("Oxford");
        mailingAddress3.setPincode(1);
        mailingAddress3.setState("MD");

        Address permanentAddress3 = new Address();
        permanentAddress3.setAddress("42 Main St");
        permanentAddress3.setAddressId(1L);
        permanentAddress3.setCity("Oxford");
        permanentAddress3.setPincode(1);
        permanentAddress3.setState("MD");

        Address residentialAddress3 = new Address();
        residentialAddress3.setAddress("42 Main St");
        residentialAddress3.setAddressId(1L);
        residentialAddress3.setCity("Oxford");
        residentialAddress3.setPincode(1);
        residentialAddress3.setState("MD");

        User user3 = new User();
        user3.setAadharNumber("42");
        user3.setDob("Dob");
        user3.setEmailId("42");
        user3.setFatherName("Father Name");
        user3.setFirstName("Jane");
        user3.setGender("Gender");
        user3.setGrossAnnualIncome("Gross Annual Income");
        user3.setKyc(true);
        user3.setLastName("Doe");
        user3.setLoginPassword("iloveyou");
        user3.setMiddleName("Middle Name");
        user3.setOccupation("Occupation");
        user3.setPanNumber("42");
        user3.setPermanentAddress(permanentAddress3);
        user3.setPhoneNumber("6625550144");
        user3.setResidentialAddress(residentialAddress3);
        user3.setSourceOfIncome("Source Of Income");
        user3.setUid(1L);

        Account toAccount = new Account();
        toAccount.setAccountNo(1234567890L);
        toAccount.setBalance(10.0d);
        toAccount.setIfscCode("Ifsc Code");
        toAccount.setIsActive(true);
        toAccount.setMailingAddress(mailingAddress3);
        toAccount.setTransactionPassword("iloveyou");
        toAccount.setUser(user3);

        Transaction transaction = new Transaction();
        transaction.setAmount(10.0d);
        transaction.setFromAccount(fromAccount);
        transaction.setId(1L);
        transaction.setRemarks("Remarks");
        transaction.setToAccount(toAccount);
        transaction.setTransactionDateTime(LocalDate.of(1970, 1, 1).atStartOfDay());
        transaction.setTransactionType("Transaction Type");
        when(transactionRepository.save(Mockito.<Transaction>any())).thenReturn(transaction);
        Transaction actualTransferFundsResult = transactionService.transferFunds(1L, 1L, "Transaction Type", 10.0d,
                "Remarks", "iloveyou");
        assertEquals(10.0d, actualTransferFundsResult.getAmount());
        assertEquals("Transaction Type", actualTransferFundsResult.getTransactionType());
        Account toAccount2 = actualTransferFundsResult.getToAccount();
        assertSame(account, toAccount2);
        assertSame(toAccount2, actualTransferFundsResult.getFromAccount());
        assertEquals("Remarks", actualTransferFundsResult.getRemarks());
        assertEquals(0.0d, toAccount2.getBalance());
        verify(accountService, atLeast(1)).getAccountById(Mockito.<Long>any());
        verify(accountService, atLeast(1)).updateAccount(Mockito.<Account>any());
        verify(transactionRepository).save(Mockito.<Transaction>any());
    }

    /**
     * Method under test: {@link TransactionService#transferFunds(Long, Long, String, double, String, String)}
     */
    @Test
    void testTransferFunds2() {
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
        when(accountService.getAccountById(Mockito.<Long>any())).thenReturn(account);
        when(transactionRepository.save(Mockito.<Transaction>any()))
                .thenThrow(new TransactionException("An error occurred"));
        assertThrows(TransactionException.class,
                () -> transactionService.transferFunds(1L, 1L, "Transaction Type", 10.0d, "Remarks", "iloveyou"));
        verify(accountService, atLeast(1)).getAccountById(Mockito.<Long>any());
        verify(transactionRepository).save(Mockito.<Transaction>any());
    }

    /**
     * Method under test: {@link TransactionService#transferFunds(Long, Long, String, double, String, String)}
     */
    @Test
    void testTransferFunds3() {
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
        when(account.getTransactionPassword()).thenReturn("iloveyou");
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
        when(accountService.getAccountById(Mockito.<Long>any())).thenReturn(account);
        assertThrows(TransactionException.class,
                () -> transactionService.transferFunds(1L, 1L, "Transaction Type", 10.0d, "Remarks", "iloveyou"));
        verify(accountService, atLeast(1)).getAccountById(Mockito.<Long>any());
        verify(account).getTransactionPassword();
        verify(account).setAccountNo(anyLong());
        verify(account).setBalance(anyDouble());
        verify(account).setIfscCode(Mockito.<String>any());
        verify(account).setIsActive(Mockito.<Boolean>any());
        verify(account).setMailingAddress(Mockito.<Address>any());
        verify(account).setTransactionPassword(Mockito.<String>any());
        verify(account).setUser(Mockito.<User>any());
    }

    /**
     * Method under test: {@link TransactionService#transferFunds(Long, Long, String, double, String, String)}
     */
    @Test
    void testTransferFunds4() {
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
        when(account.getBalance()).thenReturn(10.0d);
        when(account.getIfscCode()).thenReturn("Ifsc Code");
        when(account.getTransactionPassword()).thenReturn("aWxvdmV5b3U=");
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
        doNothing().when(accountService).updateAccount(Mockito.<Account>any());
        when(accountService.getAccountById(Mockito.<Long>any())).thenReturn(account);

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

        Account fromAccount = new Account();
        fromAccount.setAccountNo(1234567890L);
        fromAccount.setBalance(10.0d);
        fromAccount.setIfscCode("Ifsc Code");
        fromAccount.setIsActive(true);
        fromAccount.setMailingAddress(mailingAddress2);
        fromAccount.setTransactionPassword("iloveyou");
        fromAccount.setUser(user2);

        Address mailingAddress3 = new Address();
        mailingAddress3.setAddress("42 Main St");
        mailingAddress3.setAddressId(1L);
        mailingAddress3.setCity("Oxford");
        mailingAddress3.setPincode(1);
        mailingAddress3.setState("MD");

        Address permanentAddress3 = new Address();
        permanentAddress3.setAddress("42 Main St");
        permanentAddress3.setAddressId(1L);
        permanentAddress3.setCity("Oxford");
        permanentAddress3.setPincode(1);
        permanentAddress3.setState("MD");

        Address residentialAddress3 = new Address();
        residentialAddress3.setAddress("42 Main St");
        residentialAddress3.setAddressId(1L);
        residentialAddress3.setCity("Oxford");
        residentialAddress3.setPincode(1);
        residentialAddress3.setState("MD");

        User user3 = new User();
        user3.setAadharNumber("42");
        user3.setDob("Dob");
        user3.setEmailId("42");
        user3.setFatherName("Father Name");
        user3.setFirstName("Jane");
        user3.setGender("Gender");
        user3.setGrossAnnualIncome("Gross Annual Income");
        user3.setKyc(true);
        user3.setLastName("Doe");
        user3.setLoginPassword("iloveyou");
        user3.setMiddleName("Middle Name");
        user3.setOccupation("Occupation");
        user3.setPanNumber("42");
        user3.setPermanentAddress(permanentAddress3);
        user3.setPhoneNumber("6625550144");
        user3.setResidentialAddress(residentialAddress3);
        user3.setSourceOfIncome("Source Of Income");
        user3.setUid(1L);

        Account toAccount = new Account();
        toAccount.setAccountNo(1234567890L);
        toAccount.setBalance(10.0d);
        toAccount.setIfscCode("Ifsc Code");
        toAccount.setIsActive(true);
        toAccount.setMailingAddress(mailingAddress3);
        toAccount.setTransactionPassword("iloveyou");
        toAccount.setUser(user3);

        Transaction transaction = new Transaction();
        transaction.setAmount(10.0d);
        transaction.setFromAccount(fromAccount);
        transaction.setId(1L);
        transaction.setRemarks("Remarks");
        transaction.setToAccount(toAccount);
        transaction.setTransactionDateTime(LocalDate.of(1970, 1, 1).atStartOfDay());
        transaction.setTransactionType("Transaction Type");
        when(transactionRepository.save(Mockito.<Transaction>any())).thenReturn(transaction);
        Transaction actualTransferFundsResult = transactionService.transferFunds(1L, 1L, "Transaction Type", 10.0d,
                "Remarks", "iloveyou");
        assertEquals(10.0d, actualTransferFundsResult.getAmount());
        assertEquals("Transaction Type", actualTransferFundsResult.getTransactionType());
        assertEquals("Remarks", actualTransferFundsResult.getRemarks());
        verify(accountService, atLeast(1)).getAccountById(Mockito.<Long>any());
        verify(accountService, atLeast(1)).updateAccount(Mockito.<Account>any());
        verify(account).getBalance();
        verify(account, atLeast(1)).getIfscCode();
        verify(account).getTransactionPassword();
        verify(account).setAccountNo(anyLong());
        verify(account, atLeast(1)).setBalance(anyDouble());
        verify(account).setIfscCode(Mockito.<String>any());
        verify(account).setIsActive(Mockito.<Boolean>any());
        verify(account).setMailingAddress(Mockito.<Address>any());
        verify(account).setTransactionPassword(Mockito.<String>any());
        verify(account).setUser(Mockito.<User>any());
        verify(transactionRepository).save(Mockito.<Transaction>any());
    }

    /**
     * Method under test: {@link TransactionService#transferFunds(Long, Long, String, double, String, String)}
     */
    @Test
    void testTransferFunds5() {
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
        when(account.getBalance()).thenThrow(new TransactionException("An error occurred"));
        when(account.getIfscCode()).thenReturn("Ifsc Code");
        when(account.getTransactionPassword()).thenReturn("aWxvdmV5b3U=");
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
        when(accountService.getAccountById(Mockito.<Long>any())).thenReturn(account);
        assertThrows(TransactionException.class,
                () -> transactionService.transferFunds(1L, 1L, "Transaction Type", 10.0d, "Remarks", "iloveyou"));
        verify(accountService, atLeast(1)).getAccountById(Mockito.<Long>any());
        verify(account).getBalance();
        verify(account).getIfscCode();
        verify(account).getTransactionPassword();
        verify(account).setAccountNo(anyLong());
        verify(account).setBalance(anyDouble());
        verify(account).setIfscCode(Mockito.<String>any());
        verify(account).setIsActive(Mockito.<Boolean>any());
        verify(account).setMailingAddress(Mockito.<Address>any());
        verify(account).setTransactionPassword(Mockito.<String>any());
        verify(account).setUser(Mockito.<User>any());
    }

    /**
     * Method under test: {@link TransactionService#transferFunds(Long, Long, String, double, String, String)}
     */
    @Test
    void testTransferFunds6() {
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
        when(account.getBalance()).thenReturn(10.0d);
        when(account.getIfscCode()).thenReturn("NX1845");
        when(account.getTransactionPassword()).thenReturn("aWxvdmV5b3U=");
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
        doNothing().when(accountService).updateAccount(Mockito.<Account>any());
        when(accountService.getAccountById(Mockito.<Long>any())).thenReturn(account);

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

        Account fromAccount = new Account();
        fromAccount.setAccountNo(1234567890L);
        fromAccount.setBalance(10.0d);
        fromAccount.setIfscCode("Ifsc Code");
        fromAccount.setIsActive(true);
        fromAccount.setMailingAddress(mailingAddress2);
        fromAccount.setTransactionPassword("iloveyou");
        fromAccount.setUser(user2);

        Address mailingAddress3 = new Address();
        mailingAddress3.setAddress("42 Main St");
        mailingAddress3.setAddressId(1L);
        mailingAddress3.setCity("Oxford");
        mailingAddress3.setPincode(1);
        mailingAddress3.setState("MD");

        Address permanentAddress3 = new Address();
        permanentAddress3.setAddress("42 Main St");
        permanentAddress3.setAddressId(1L);
        permanentAddress3.setCity("Oxford");
        permanentAddress3.setPincode(1);
        permanentAddress3.setState("MD");

        Address residentialAddress3 = new Address();
        residentialAddress3.setAddress("42 Main St");
        residentialAddress3.setAddressId(1L);
        residentialAddress3.setCity("Oxford");
        residentialAddress3.setPincode(1);
        residentialAddress3.setState("MD");

        User user3 = new User();
        user3.setAadharNumber("42");
        user3.setDob("Dob");
        user3.setEmailId("42");
        user3.setFatherName("Father Name");
        user3.setFirstName("Jane");
        user3.setGender("Gender");
        user3.setGrossAnnualIncome("Gross Annual Income");
        user3.setKyc(true);
        user3.setLastName("Doe");
        user3.setLoginPassword("iloveyou");
        user3.setMiddleName("Middle Name");
        user3.setOccupation("Occupation");
        user3.setPanNumber("42");
        user3.setPermanentAddress(permanentAddress3);
        user3.setPhoneNumber("6625550144");
        user3.setResidentialAddress(residentialAddress3);
        user3.setSourceOfIncome("Source Of Income");
        user3.setUid(1L);

        Account toAccount = new Account();
        toAccount.setAccountNo(1234567890L);
        toAccount.setBalance(10.0d);
        toAccount.setIfscCode("Ifsc Code");
        toAccount.setIsActive(true);
        toAccount.setMailingAddress(mailingAddress3);
        toAccount.setTransactionPassword("iloveyou");
        toAccount.setUser(user3);

        Transaction transaction = new Transaction();
        transaction.setAmount(10.0d);
        transaction.setFromAccount(fromAccount);
        transaction.setId(1L);
        transaction.setRemarks("Remarks");
        transaction.setToAccount(toAccount);
        transaction.setTransactionDateTime(LocalDate.of(1970, 1, 1).atStartOfDay());
        transaction.setTransactionType("Transaction Type");
        when(transactionRepository.save(Mockito.<Transaction>any())).thenReturn(transaction);
        Transaction actualTransferFundsResult = transactionService.transferFunds(1L, 1L, "Transaction Type", 10.0d,
                "Remarks", "iloveyou");
        assertEquals(10.0d, actualTransferFundsResult.getAmount());
        assertEquals("Transaction Type", actualTransferFundsResult.getTransactionType());
        assertEquals("Remarks", actualTransferFundsResult.getRemarks());
        verify(accountService, atLeast(1)).getAccountById(Mockito.<Long>any());
        verify(accountService, atLeast(1)).updateAccount(Mockito.<Account>any());
        verify(account, atLeast(1)).getBalance();
        verify(account, atLeast(1)).getIfscCode();
        verify(account).getTransactionPassword();
        verify(account).setAccountNo(anyLong());
        verify(account, atLeast(1)).setBalance(anyDouble());
        verify(account).setIfscCode(Mockito.<String>any());
        verify(account).setIsActive(Mockito.<Boolean>any());
        verify(account).setMailingAddress(Mockito.<Address>any());
        verify(account).setTransactionPassword(Mockito.<String>any());
        verify(account).setUser(Mockito.<User>any());
        verify(transactionRepository).save(Mockito.<Transaction>any());
    }

    /**
     * Method under test: {@link TransactionService#getAllTransactions()}
     */
    @Test
    void testGetAllTransactions() {
        ArrayList<Transaction> transactionList = new ArrayList<>();
        when(transactionRepository.findAll()).thenReturn(transactionList);
        List<Transaction> actualAllTransactions = transactionService.getAllTransactions();
        assertSame(transactionList, actualAllTransactions);
        assertTrue(actualAllTransactions.isEmpty());
        verify(transactionRepository).findAll();
    }

    /**
     * Method under test: {@link TransactionService#getAllTransactions()}
     */
    @Test
    void testGetAllTransactions2() {
        when(transactionRepository.findAll()).thenThrow(new TransactionException("An error occurred"));
        assertThrows(TransactionException.class, () -> transactionService.getAllTransactions());
        verify(transactionRepository).findAll();
    }

    /**
     * Method under test: {@link TransactionService#getTransactionById(Long)}
     */
    @Test
    void testGetTransactionById() {
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

        Account toAccount = new Account();
        toAccount.setAccountNo(1234567890L);
        toAccount.setBalance(10.0d);
        toAccount.setIfscCode("Ifsc Code");
        toAccount.setIsActive(true);
        toAccount.setMailingAddress(mailingAddress2);
        toAccount.setTransactionPassword("iloveyou");
        toAccount.setUser(user2);

        Transaction transaction = new Transaction();
        transaction.setAmount(10.0d);
        transaction.setFromAccount(fromAccount);
        transaction.setId(1L);
        transaction.setRemarks("Remarks");
        transaction.setToAccount(toAccount);
        transaction.setTransactionDateTime(LocalDate.of(1970, 1, 1).atStartOfDay());
        transaction.setTransactionType("Transaction Type");
        Optional<Transaction> ofResult = Optional.of(transaction);
        when(transactionRepository.findById(Mockito.<Long>any())).thenReturn(ofResult);
        assertSame(transaction, transactionService.getTransactionById(1L));
        verify(transactionRepository).findById(Mockito.<Long>any());
    }

    /**
     * Method under test: {@link TransactionService#getTransactionsByAccount(Long)}
     */
    @Test
    void testGetTransactionsByAccount() {
        ArrayList<Transaction> transactionList = new ArrayList<>();
        when(transactionRepository.findTransactionsByAccountNo(Mockito.<Long>any())).thenReturn(transactionList);
        List<Transaction> actualTransactionsByAccount = transactionService.getTransactionsByAccount(1234567890L);
        assertSame(transactionList, actualTransactionsByAccount);
        assertTrue(actualTransactionsByAccount.isEmpty());
        verify(transactionRepository).findTransactionsByAccountNo(Mockito.<Long>any());
    }

    /**
     * Method under test: {@link TransactionService#getTransactionsByAccount(Long)}
     */
    @Test
    void testGetTransactionsByAccount2() {
        when(transactionRepository.findTransactionsByAccountNo(Mockito.<Long>any()))
                .thenThrow(new TransactionException("An error occurred"));
        assertThrows(TransactionException.class, () -> transactionService.getTransactionsByAccount(1234567890L));
        verify(transactionRepository).findTransactionsByAccountNo(Mockito.<Long>any());
    }

    /**
     * Method under test: {@link TransactionService#deleteTransaction(Long)}
     */
    @Test
    void testDeleteTransaction() {
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

        Account toAccount = new Account();
        toAccount.setAccountNo(1234567890L);
        toAccount.setBalance(10.0d);
        toAccount.setIfscCode("Ifsc Code");
        toAccount.setIsActive(true);
        toAccount.setMailingAddress(mailingAddress2);
        toAccount.setTransactionPassword("iloveyou");
        toAccount.setUser(user2);

        Transaction transaction = new Transaction();
        transaction.setAmount(10.0d);
        transaction.setFromAccount(fromAccount);
        transaction.setId(1L);
        transaction.setRemarks("Remarks");
        transaction.setToAccount(toAccount);
        transaction.setTransactionDateTime(LocalDate.of(1970, 1, 1).atStartOfDay());
        transaction.setTransactionType("Transaction Type");
        Optional<Transaction> ofResult = Optional.of(transaction);
        doNothing().when(transactionRepository).deleteById(Mockito.<Long>any());
        when(transactionRepository.findById(Mockito.<Long>any())).thenReturn(ofResult);
        transactionService.deleteTransaction(1L);
        verify(transactionRepository).findById(Mockito.<Long>any());
        verify(transactionRepository).deleteById(Mockito.<Long>any());
        assertTrue(transactionService.getAllTransactions().isEmpty());
    }

    /**
     * Method under test: {@link TransactionService#deleteTransaction(Long)}
     */
    @Test
    void testDeleteTransaction2() {
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

        Account toAccount = new Account();
        toAccount.setAccountNo(1234567890L);
        toAccount.setBalance(10.0d);
        toAccount.setIfscCode("Ifsc Code");
        toAccount.setIsActive(true);
        toAccount.setMailingAddress(mailingAddress2);
        toAccount.setTransactionPassword("iloveyou");
        toAccount.setUser(user2);

        Transaction transaction = new Transaction();
        transaction.setAmount(10.0d);
        transaction.setFromAccount(fromAccount);
        transaction.setId(1L);
        transaction.setRemarks("Remarks");
        transaction.setToAccount(toAccount);
        transaction.setTransactionDateTime(LocalDate.of(1970, 1, 1).atStartOfDay());
        transaction.setTransactionType("Transaction Type");
        Optional<Transaction> ofResult = Optional.of(transaction);
        doThrow(new TransactionException("An error occurred")).when(transactionRepository)
                .deleteById(Mockito.<Long>any());
        when(transactionRepository.findById(Mockito.<Long>any())).thenReturn(ofResult);
        assertThrows(TransactionException.class, () -> transactionService.deleteTransaction(1L));
        verify(transactionRepository).findById(Mockito.<Long>any());
        verify(transactionRepository).deleteById(Mockito.<Long>any());
    }
}

