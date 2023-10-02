package com.onlinebanking.team3.onlinebanking.controller;

import static org.mockito.Mockito.anyDouble;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.onlinebanking.team3.onlinebanking.exception.TransactionNotFoundException;
import com.onlinebanking.team3.onlinebanking.model.Account;
import com.onlinebanking.team3.onlinebanking.model.Address;
import com.onlinebanking.team3.onlinebanking.model.Transaction;
import com.onlinebanking.team3.onlinebanking.model.User;
import com.onlinebanking.team3.onlinebanking.service.AccountService;
import com.onlinebanking.team3.onlinebanking.service.BeneficiaryService;
import com.onlinebanking.team3.onlinebanking.service.TransactionService;

import java.time.LocalDate;
import java.util.ArrayList;

import org.junit.jupiter.api.Disabled;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@ContextConfiguration(classes = {TransactionController.class})
@ExtendWith(SpringExtension.class)
class TransactionControllerTest {
    @MockBean
    private AccountService accountService;

    @MockBean
    private BeneficiaryService beneficiaryService;

    @Autowired
    private TransactionController transactionController;

    @MockBean
    private TransactionService transactionService;

    /**
     * Method under test: {@link TransactionController#getTransactionById(Long)}
     */
    @Test
    void testGetTransactionById() throws Exception {
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

        Account fromAccount = new Account();
        fromAccount.setAccountNo(1234567890L);
        fromAccount.setAccountType("3");
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
        toAccount.setAccountType("3");
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
        when(transactionService.getTransactionById(Mockito.<Long>any())).thenReturn(transaction);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/transactions/{transactionId}", 1L);

        // Act and Assert
        MockMvcBuilders.standaloneSetup(transactionController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"id\":1,\"transactionDateTime\":\"1970-01-01 00:00:00\",\"amount\":10.0,\"transactionType\":\"Transaction"
                                        + " Type\",\"remarks\":\"Remarks\",\"toAccount\":{\"accountNo\":1234567890,\"accountType\":\"3\",\"ifscCode\":\"Ifsc"
                                        + " Code\",\"isActive\":true,\"mailingAddress\":{\"addressId\":1,\"address\":\"42 Main St\",\"city\":\"Oxford\",\"state"
                                        + "\":\"MD\",\"pincode\":1},\"balance\":10.0,\"transactionPassword\":\"aWxvdmV5b3U=\",\"user\":{\"uid\":1,\"firstName\":"
                                        + "\"Jane\",\"middleName\":\"Middle Name\",\"lastName\":\"Doe\",\"gender\":\"Gender\",\"fatherName\":\"Father Name\","
                                        + "\"phoneNumber\":\"6625550144\",\"emailId\":\"42\",\"panNumber\":\"42\",\"aadharNumber\":\"42\",\"dob\":\"Dob\",\"occupation"
                                        + "\":\"Occupation\",\"sourceOfIncome\":\"Source Of Income\",\"grossAnnualIncome\":\"Gross Annual Income\",\"loginPassword"
                                        + "\":\"aWxvdmV5b3U=\",\"kyc\":true,\"residentialAddress\":{\"addressId\":1,\"address\":\"42 Main St\",\"city\":\"Oxford"
                                        + "\",\"state\":\"MD\",\"pincode\":1},\"permanentAddress\":{\"addressId\":1,\"address\":\"42 Main St\",\"city\":\"Oxford\""
                                        + ",\"state\":\"MD\",\"pincode\":1}}},\"fromAccount\":{\"accountNo\":1234567890,\"accountType\":\"3\",\"ifscCode\":\"Ifsc"
                                        + " Code\",\"isActive\":true,\"mailingAddress\":{\"addressId\":1,\"address\":\"42 Main St\",\"city\":\"Oxford\",\"state"
                                        + "\":\"MD\",\"pincode\":1},\"balance\":10.0,\"transactionPassword\":\"aWxvdmV5b3U=\",\"user\":{\"uid\":1,\"firstName\":"
                                        + "\"Jane\",\"middleName\":\"Middle Name\",\"lastName\":\"Doe\",\"gender\":\"Gender\",\"fatherName\":\"Father Name\","
                                        + "\"phoneNumber\":\"6625550144\",\"emailId\":\"42\",\"panNumber\":\"42\",\"aadharNumber\":\"42\",\"dob\":\"Dob\",\"occupation"
                                        + "\":\"Occupation\",\"sourceOfIncome\":\"Source Of Income\",\"grossAnnualIncome\":\"Gross Annual Income\",\"loginPassword"
                                        + "\":\"aWxvdmV5b3U=\",\"kyc\":true,\"residentialAddress\":{\"addressId\":1,\"address\":\"42 Main St\",\"city\":\"Oxford"
                                        + "\",\"state\":\"MD\",\"pincode\":1},\"permanentAddress\":{\"addressId\":1,\"address\":\"42 Main St\",\"city\":\"Oxford\""
                                        + ",\"state\":\"MD\",\"pincode\":1}}}}"));
    }

    /**
     * Method under test: {@link TransactionController#getTransactionsByAccount(Long)}
     */
    @Test
    void testGetTransactionsByAccount() throws Exception {
        // Arrange
        when(transactionService.getTransactionsByAccount(Mockito.<Long>any())).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/transactions/accounts/{accountNo}",
                1234567890L);

        // Act
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(transactionController)
                .build()
                .perform(requestBuilder);

        // Assert
        actualPerformResult.andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    /**
     * Method under test: {@link TransactionController#getTransactionsByAccount(Long)}
     */
    @Test
    void testGetTransactionsByAccount2() throws Exception {
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

        Account fromAccount = new Account();
        fromAccount.setAccountNo(1234567890L);
        fromAccount.setAccountType("3");
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
        toAccount.setAccountType("3");
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

        ArrayList<Transaction> transactionList = new ArrayList<>();
        transactionList.add(transaction);
        when(transactionService.getTransactionsByAccount(Mockito.<Long>any())).thenReturn(transactionList);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/transactions/accounts/{accountNo}",
                1234567890L);

        // Act and Assert
        MockMvcBuilders.standaloneSetup(transactionController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "[{\"id\":1,\"transactionDateTime\":\"1970-01-01 00:00:00\",\"amount\":10.0,\"transactionType\":\"Transaction"
                                        + " Type\",\"remarks\":\"Remarks\",\"toAccount\":{\"accountNo\":1234567890,\"accountType\":\"3\",\"ifscCode\":\"Ifsc"
                                        + " Code\",\"isActive\":true,\"mailingAddress\":{\"addressId\":1,\"address\":\"42 Main St\",\"city\":\"Oxford\",\"state"
                                        + "\":\"MD\",\"pincode\":1},\"balance\":10.0,\"transactionPassword\":\"aWxvdmV5b3U=\",\"user\":{\"uid\":1,\"firstName\":"
                                        + "\"Jane\",\"middleName\":\"Middle Name\",\"lastName\":\"Doe\",\"gender\":\"Gender\",\"fatherName\":\"Father Name\","
                                        + "\"phoneNumber\":\"6625550144\",\"emailId\":\"42\",\"panNumber\":\"42\",\"aadharNumber\":\"42\",\"dob\":\"Dob\",\"occupation"
                                        + "\":\"Occupation\",\"sourceOfIncome\":\"Source Of Income\",\"grossAnnualIncome\":\"Gross Annual Income\",\"loginPassword"
                                        + "\":\"aWxvdmV5b3U=\",\"kyc\":true,\"residentialAddress\":{\"addressId\":1,\"address\":\"42 Main St\",\"city\":\"Oxford"
                                        + "\",\"state\":\"MD\",\"pincode\":1},\"permanentAddress\":{\"addressId\":1,\"address\":\"42 Main St\",\"city\":\"Oxford\""
                                        + ",\"state\":\"MD\",\"pincode\":1}}},\"fromAccount\":{\"accountNo\":1234567890,\"accountType\":\"3\",\"ifscCode\":\"Ifsc"
                                        + " Code\",\"isActive\":true,\"mailingAddress\":{\"addressId\":1,\"address\":\"42 Main St\",\"city\":\"Oxford\",\"state"
                                        + "\":\"MD\",\"pincode\":1},\"balance\":10.0,\"transactionPassword\":\"aWxvdmV5b3U=\",\"user\":{\"uid\":1,\"firstName\":"
                                        + "\"Jane\",\"middleName\":\"Middle Name\",\"lastName\":\"Doe\",\"gender\":\"Gender\",\"fatherName\":\"Father Name\","
                                        + "\"phoneNumber\":\"6625550144\",\"emailId\":\"42\",\"panNumber\":\"42\",\"aadharNumber\":\"42\",\"dob\":\"Dob\",\"occupation"
                                        + "\":\"Occupation\",\"sourceOfIncome\":\"Source Of Income\",\"grossAnnualIncome\":\"Gross Annual Income\",\"loginPassword"
                                        + "\":\"aWxvdmV5b3U=\",\"kyc\":true,\"residentialAddress\":{\"addressId\":1,\"address\":\"42 Main St\",\"city\":\"Oxford"
                                        + "\",\"state\":\"MD\",\"pincode\":1},\"permanentAddress\":{\"addressId\":1,\"address\":\"42 Main St\",\"city\":\"Oxford\""
                                        + ",\"state\":\"MD\",\"pincode\":1}}}}]"));
    }

    /**
     * Method under test: {@link TransactionController#getAllTransactions()}
     */
    @Test
    void testGetAllTransactions() throws Exception {
        // Arrange
        when(transactionService.getAllTransactions()).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/transactions/all");

        // Act
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(transactionController)
                .build()
                .perform(requestBuilder);

        // Assert
        actualPerformResult.andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    /**
     * Method under test: {@link TransactionController#getAllTransactions()}
     */
    @Test
    void testGetAllTransactions2() throws Exception {
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

        Account fromAccount = new Account();
        fromAccount.setAccountNo(1234567890L);
        fromAccount.setAccountType("3");
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
        toAccount.setAccountType("3");
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

        ArrayList<Transaction> transactionList = new ArrayList<>();
        transactionList.add(transaction);
        when(transactionService.getAllTransactions()).thenReturn(transactionList);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/transactions/all");

        // Act and Assert
        MockMvcBuilders.standaloneSetup(transactionController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "[{\"id\":1,\"transactionDateTime\":\"1970-01-01 00:00:00\",\"amount\":10.0,\"transactionType\":\"Transaction"
                                        + " Type\",\"remarks\":\"Remarks\",\"toAccount\":{\"accountNo\":1234567890,\"accountType\":\"3\",\"ifscCode\":\"Ifsc"
                                        + " Code\",\"isActive\":true,\"mailingAddress\":{\"addressId\":1,\"address\":\"42 Main St\",\"city\":\"Oxford\",\"state"
                                        + "\":\"MD\",\"pincode\":1},\"balance\":10.0,\"transactionPassword\":\"aWxvdmV5b3U=\",\"user\":{\"uid\":1,\"firstName\":"
                                        + "\"Jane\",\"middleName\":\"Middle Name\",\"lastName\":\"Doe\",\"gender\":\"Gender\",\"fatherName\":\"Father Name\","
                                        + "\"phoneNumber\":\"6625550144\",\"emailId\":\"42\",\"panNumber\":\"42\",\"aadharNumber\":\"42\",\"dob\":\"Dob\",\"occupation"
                                        + "\":\"Occupation\",\"sourceOfIncome\":\"Source Of Income\",\"grossAnnualIncome\":\"Gross Annual Income\",\"loginPassword"
                                        + "\":\"aWxvdmV5b3U=\",\"kyc\":true,\"residentialAddress\":{\"addressId\":1,\"address\":\"42 Main St\",\"city\":\"Oxford"
                                        + "\",\"state\":\"MD\",\"pincode\":1},\"permanentAddress\":{\"addressId\":1,\"address\":\"42 Main St\",\"city\":\"Oxford\""
                                        + ",\"state\":\"MD\",\"pincode\":1}}},\"fromAccount\":{\"accountNo\":1234567890,\"accountType\":\"3\",\"ifscCode\":\"Ifsc"
                                        + " Code\",\"isActive\":true,\"mailingAddress\":{\"addressId\":1,\"address\":\"42 Main St\",\"city\":\"Oxford\",\"state"
                                        + "\":\"MD\",\"pincode\":1},\"balance\":10.0,\"transactionPassword\":\"aWxvdmV5b3U=\",\"user\":{\"uid\":1,\"firstName\":"
                                        + "\"Jane\",\"middleName\":\"Middle Name\",\"lastName\":\"Doe\",\"gender\":\"Gender\",\"fatherName\":\"Father Name\","
                                        + "\"phoneNumber\":\"6625550144\",\"emailId\":\"42\",\"panNumber\":\"42\",\"aadharNumber\":\"42\",\"dob\":\"Dob\",\"occupation"
                                        + "\":\"Occupation\",\"sourceOfIncome\":\"Source Of Income\",\"grossAnnualIncome\":\"Gross Annual Income\",\"loginPassword"
                                        + "\":\"aWxvdmV5b3U=\",\"kyc\":true,\"residentialAddress\":{\"addressId\":1,\"address\":\"42 Main St\",\"city\":\"Oxford"
                                        + "\",\"state\":\"MD\",\"pincode\":1},\"permanentAddress\":{\"addressId\":1,\"address\":\"42 Main St\",\"city\":\"Oxford\""
                                        + ",\"state\":\"MD\",\"pincode\":1}}}}]"));
    }

    /**
     * Method under test: {@link TransactionController#createTransaction(Long, Long, String, double, String, String)}
     */
    @Test
    void testCreateTransaction() throws Exception {
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

        Account fromAccount = new Account();
        fromAccount.setAccountNo(1234567890L);
        fromAccount.setAccountType("3");
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
        toAccount.setAccountType("3");
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
        when(transactionService.transferFunds(Mockito.<Long>any(), Mockito.<Long>any(), Mockito.<String>any(), anyDouble(),
                Mockito.<String>any(), Mockito.<String>any())).thenReturn(transaction);
        MockHttpServletRequestBuilder postResult = MockMvcRequestBuilders.post("/transactions/{fromId}/{toId}", 1L, 1L);
        MockHttpServletRequestBuilder requestBuilder = postResult.param("amount", String.valueOf(10.0d))
                .param("remarks", "foo")
                .param("transactionPassword", "foo")
                .param("transactionType", "foo");

        // Act and Assert
        MockMvcBuilders.standaloneSetup(transactionController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"id\":1,\"transactionDateTime\":\"1970-01-01 00:00:00\",\"amount\":10.0,\"transactionType\":\"Transaction"
                                        + " Type\",\"remarks\":\"Remarks\",\"toAccount\":{\"accountNo\":1234567890,\"accountType\":\"3\",\"ifscCode\":\"Ifsc"
                                        + " Code\",\"isActive\":true,\"mailingAddress\":{\"addressId\":1,\"address\":\"42 Main St\",\"city\":\"Oxford\",\"state"
                                        + "\":\"MD\",\"pincode\":1},\"balance\":10.0,\"transactionPassword\":\"aWxvdmV5b3U=\",\"user\":{\"uid\":1,\"firstName\":"
                                        + "\"Jane\",\"middleName\":\"Middle Name\",\"lastName\":\"Doe\",\"gender\":\"Gender\",\"fatherName\":\"Father Name\","
                                        + "\"phoneNumber\":\"6625550144\",\"emailId\":\"42\",\"panNumber\":\"42\",\"aadharNumber\":\"42\",\"dob\":\"Dob\",\"occupation"
                                        + "\":\"Occupation\",\"sourceOfIncome\":\"Source Of Income\",\"grossAnnualIncome\":\"Gross Annual Income\",\"loginPassword"
                                        + "\":\"aWxvdmV5b3U=\",\"kyc\":true,\"residentialAddress\":{\"addressId\":1,\"address\":\"42 Main St\",\"city\":\"Oxford"
                                        + "\",\"state\":\"MD\",\"pincode\":1},\"permanentAddress\":{\"addressId\":1,\"address\":\"42 Main St\",\"city\":\"Oxford\""
                                        + ",\"state\":\"MD\",\"pincode\":1}}},\"fromAccount\":{\"accountNo\":1234567890,\"accountType\":\"3\",\"ifscCode\":\"Ifsc"
                                        + " Code\",\"isActive\":true,\"mailingAddress\":{\"addressId\":1,\"address\":\"42 Main St\",\"city\":\"Oxford\",\"state"
                                        + "\":\"MD\",\"pincode\":1},\"balance\":10.0,\"transactionPassword\":\"aWxvdmV5b3U=\",\"user\":{\"uid\":1,\"firstName\":"
                                        + "\"Jane\",\"middleName\":\"Middle Name\",\"lastName\":\"Doe\",\"gender\":\"Gender\",\"fatherName\":\"Father Name\","
                                        + "\"phoneNumber\":\"6625550144\",\"emailId\":\"42\",\"panNumber\":\"42\",\"aadharNumber\":\"42\",\"dob\":\"Dob\",\"occupation"
                                        + "\":\"Occupation\",\"sourceOfIncome\":\"Source Of Income\",\"grossAnnualIncome\":\"Gross Annual Income\",\"loginPassword"
                                        + "\":\"aWxvdmV5b3U=\",\"kyc\":true,\"residentialAddress\":{\"addressId\":1,\"address\":\"42 Main St\",\"city\":\"Oxford"
                                        + "\",\"state\":\"MD\",\"pincode\":1},\"permanentAddress\":{\"addressId\":1,\"address\":\"42 Main St\",\"city\":\"Oxford\""
                                        + ",\"state\":\"MD\",\"pincode\":1}}}}"));
    }

    /**
     * Method under test: {@link TransactionController#deleteTransaction(Long)}
     */
    @Test
    void testDeleteTransaction() throws Exception {
        // Arrange
        doNothing().when(transactionService).deleteTransaction(Mockito.<Long>any());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/{transactionId}", 1L);

        // Act
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(transactionController)
                .build()
                .perform(requestBuilder);

        // Assert
        actualPerformResult.andExpect(MockMvcResultMatchers.status().isNoContent());
    }

    /**
     * Method under test: {@link TransactionController#deleteTransaction(Long)}
     */
    @Test
    void testDeleteTransaction2() throws Exception {
        // Arrange
        doThrow(new TransactionNotFoundException("Transaction Not Found")).when(transactionService)
                .deleteTransaction(Mockito.<Long>any());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/{transactionId}", 1L);

        // Act
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(transactionController)
                .build()
                .perform(requestBuilder);

        // Assert
        actualPerformResult.andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    /**
     * Method under test: {@link TransactionController#updateTransaction(Long, Transaction)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testUpdateTransaction() throws Exception {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   com.fasterxml.jackson.databind.exc.InvalidDefinitionException: Java 8 date/time type `java.time.LocalDateTime` not supported by default: add Module "com.fasterxml.jackson.datatype:jackson-datatype-jsr310" to enable handling (through reference chain: com.onlinebanking.team3.onlinebanking.model.Transaction["transactionDateTime"])
        //       at com.fasterxml.jackson.databind.exc.InvalidDefinitionException.from(InvalidDefinitionException.java:77)
        //       at com.fasterxml.jackson.databind.SerializerProvider.reportBadDefinition(SerializerProvider.java:1308)
        //       at com.fasterxml.jackson.databind.ser.impl.UnsupportedTypeSerializer.serialize(UnsupportedTypeSerializer.java:35)
        //       at com.fasterxml.jackson.databind.ser.BeanPropertyWriter.serializeAsField(BeanPropertyWriter.java:732)
        //       at com.fasterxml.jackson.databind.ser.std.BeanSerializerBase.serializeFields(BeanSerializerBase.java:772)
        //       at com.fasterxml.jackson.databind.ser.BeanSerializer.serialize(BeanSerializer.java:178)
        //       at com.fasterxml.jackson.databind.ser.DefaultSerializerProvider._serialize(DefaultSerializerProvider.java:479)
        //       at com.fasterxml.jackson.databind.ser.DefaultSerializerProvider.serializeValue(DefaultSerializerProvider.java:318)
        //       at com.fasterxml.jackson.databind.ObjectMapper._writeValueAndClose(ObjectMapper.java:4719)
        //       at com.fasterxml.jackson.databind.ObjectMapper.writeValueAsString(ObjectMapper.java:3964)
        //   See https://diff.blue/R013 to resolve this issue.

        // Arrange
        MockHttpServletRequestBuilder contentTypeResult = MockMvcRequestBuilders.put("/{transactionId}", 1L)
                .contentType(MediaType.APPLICATION_JSON);

        Transaction transaction = new Transaction();
        transaction.setAmount(10.0d);

        Account fromAccount = new Account();
        fromAccount.setAccountNo(1234567890L);
        fromAccount.setAccountType("3");
        fromAccount.setBalance(10.0d);
        fromAccount.setIfscCode("Ifsc Code");
        fromAccount.setIsActive(true);

        Address mailingAddress = new Address();
        mailingAddress.setAddress("42 Main St");
        mailingAddress.setAddressId(1L);
        mailingAddress.setCity("Oxford");
        mailingAddress.setPincode(1);
        mailingAddress.setState("MD");
        fromAccount.setMailingAddress(mailingAddress);
        fromAccount.setTransactionPassword("iloveyou");

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

        Address permanentAddress = new Address();
        permanentAddress.setAddress("42 Main St");
        permanentAddress.setAddressId(1L);
        permanentAddress.setCity("Oxford");
        permanentAddress.setPincode(1);
        permanentAddress.setState("MD");
        user.setPermanentAddress(permanentAddress);
        user.setPhoneNumber("6625550144");

        Address residentialAddress = new Address();
        residentialAddress.setAddress("42 Main St");
        residentialAddress.setAddressId(1L);
        residentialAddress.setCity("Oxford");
        residentialAddress.setPincode(1);
        residentialAddress.setState("MD");
        user.setResidentialAddress(residentialAddress);
        user.setSourceOfIncome("Source Of Income");
        user.setUid(1L);
        fromAccount.setUser(user);
        transaction.setFromAccount(fromAccount);
        transaction.setId(1L);
        transaction.setRemarks("Remarks");

        Account toAccount = new Account();
        toAccount.setAccountNo(1234567890L);
        toAccount.setAccountType("3");
        toAccount.setBalance(10.0d);
        toAccount.setIfscCode("Ifsc Code");
        toAccount.setIsActive(true);

        Address mailingAddress2 = new Address();
        mailingAddress2.setAddress("42 Main St");
        mailingAddress2.setAddressId(1L);
        mailingAddress2.setCity("Oxford");
        mailingAddress2.setPincode(1);
        mailingAddress2.setState("MD");
        toAccount.setMailingAddress(mailingAddress2);
        toAccount.setTransactionPassword("iloveyou");

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

        Address permanentAddress2 = new Address();
        permanentAddress2.setAddress("42 Main St");
        permanentAddress2.setAddressId(1L);
        permanentAddress2.setCity("Oxford");
        permanentAddress2.setPincode(1);
        permanentAddress2.setState("MD");
        user2.setPermanentAddress(permanentAddress2);
        user2.setPhoneNumber("6625550144");

        Address residentialAddress2 = new Address();
        residentialAddress2.setAddress("42 Main St");
        residentialAddress2.setAddressId(1L);
        residentialAddress2.setCity("Oxford");
        residentialAddress2.setPincode(1);
        residentialAddress2.setState("MD");
        user2.setResidentialAddress(residentialAddress2);
        user2.setSourceOfIncome("Source Of Income");
        user2.setUid(1L);
        toAccount.setUser(user2);
        transaction.setToAccount(toAccount);
        transaction.setTransactionDateTime(LocalDate.of(1970, 1, 1).atStartOfDay());
        transaction.setTransactionType("Transaction Type");
        MockHttpServletRequestBuilder requestBuilder = contentTypeResult
                .content((new ObjectMapper()).writeValueAsString(transaction));

        // Act
        MockMvcBuilders.standaloneSetup(transactionController).build().perform(requestBuilder);
    }
}

