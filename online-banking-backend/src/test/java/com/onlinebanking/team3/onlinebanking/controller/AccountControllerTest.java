package com.onlinebanking.team3.onlinebanking.controller;

import static org.mockito.Mockito.when;

import com.onlinebanking.team3.onlinebanking.model.Account;
import com.onlinebanking.team3.onlinebanking.model.Address;
import com.onlinebanking.team3.onlinebanking.model.Transaction;
import com.onlinebanking.team3.onlinebanking.model.User;
import com.onlinebanking.team3.onlinebanking.service.AccountService;
import com.onlinebanking.team3.onlinebanking.service.TransactionService;
import com.onlinebanking.team3.onlinebanking.service.UserService;

import java.time.LocalDate;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@ContextConfiguration(classes = {AccountController.class})
@ExtendWith(SpringExtension.class)
class AccountControllerTest {
    @Autowired
    private AccountController accountController;

    @MockBean
    private AccountService accountService;

    @MockBean
    private TransactionService transactionService;

    @MockBean
    private UserService userService;

    /**
     * Method under test: {@link AccountController#addNewAccount(Long)}
     */
    @Test
    void testAddNewAccount() throws Exception {
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
        when(accountService.addNewAccount(Mockito.<Long>any())).thenReturn(account);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.put("/addNewAccount/{userId}", 1L);
        MockMvcBuilders.standaloneSetup(accountController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"accountNo\":1234567890,\"ifscCode\":\"Ifsc Code\",\"isActive\":true,\"mailingAddress\":{\"addressId\":1,\"address\":\"42"
                                        + " Main St\",\"city\":\"Oxford\",\"state\":\"MD\",\"pincode\":1},\"balance\":10.0,\"transactionPassword\":\"aWxvdmV5b3U"
                                        + "=\",\"user\":{\"uid\":1,\"firstName\":\"Jane\",\"middleName\":\"Middle Name\",\"lastName\":\"Doe\",\"gender\":\"Gender\","
                                        + "\"fatherName\":\"Father Name\",\"phoneNumber\":\"6625550144\",\"emailId\":\"42\",\"panNumber\":\"42\",\"aadharNumber\""
                                        + ":\"42\",\"dob\":\"Dob\",\"occupation\":\"Occupation\",\"sourceOfIncome\":\"Source Of Income\",\"grossAnnualIncome\":\"Gross"
                                        + " Annual Income\",\"loginPassword\":\"aWxvdmV5b3U=\",\"kyc\":true,\"residentialAddress\":{\"addressId\":1,\"address\":\"42"
                                        + " Main St\",\"city\":\"Oxford\",\"state\":\"MD\",\"pincode\":1},\"permanentAddress\":{\"addressId\":1,\"address\":\"42"
                                        + " Main St\",\"city\":\"Oxford\",\"state\":\"MD\",\"pincode\":1}}}"));
    }

    /**
     * Method under test: {@link AccountController#getActiveAccountsForUser(Long)}
     */
    @Test
    void testGetActiveAccountsForUser() throws Exception {
        when(accountService.getActiveAccountsForUser(Mockito.<Long>any())).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/active/{userId}", 1L);
        MockMvcBuilders.standaloneSetup(accountController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    /**
     * Method under test: {@link AccountController#getActiveAccountsForUser(Long)}
     */
    @Test
    void testGetActiveAccountsForUser2() throws Exception {
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

        ArrayList<Account> accountList = new ArrayList<>();
        accountList.add(account);
        when(accountService.getActiveAccountsForUser(Mockito.<Long>any())).thenReturn(accountList);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/active/{userId}", 1L);
        MockMvcBuilders.standaloneSetup(accountController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "[{\"accountNo\":1234567890,\"ifscCode\":\"Ifsc Code\",\"isActive\":true,\"mailingAddress\":{\"addressId\":1,\"address\":\"42"
                                        + " Main St\",\"city\":\"Oxford\",\"state\":\"MD\",\"pincode\":1},\"balance\":10.0,\"transactionPassword\":\"aWxvdmV5b3U"
                                        + "=\",\"user\":{\"uid\":1,\"firstName\":\"Jane\",\"middleName\":\"Middle Name\",\"lastName\":\"Doe\",\"gender\":\"Gender\","
                                        + "\"fatherName\":\"Father Name\",\"phoneNumber\":\"6625550144\",\"emailId\":\"42\",\"panNumber\":\"42\",\"aadharNumber\""
                                        + ":\"42\",\"dob\":\"Dob\",\"occupation\":\"Occupation\",\"sourceOfIncome\":\"Source Of Income\",\"grossAnnualIncome\":\"Gross"
                                        + " Annual Income\",\"loginPassword\":\"aWxvdmV5b3U=\",\"kyc\":true,\"residentialAddress\":{\"addressId\":1,\"address\":\"42"
                                        + " Main St\",\"city\":\"Oxford\",\"state\":\"MD\",\"pincode\":1},\"permanentAddress\":{\"addressId\":1,\"address\":\"42"
                                        + " Main St\",\"city\":\"Oxford\",\"state\":\"MD\",\"pincode\":1}}}]"));
    }

    /**
     * Method under test: {@link AccountController#getAccountsForUserId(Long)}
     */
    @Test
    void testGetAccountsForUserId() throws Exception {
        Optional<List<Account>> ofResult = Optional.of(new ArrayList<>());
        when(accountService.getAccountsForUser(Mockito.<Long>any())).thenReturn(ofResult);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/user/{uid}/accounts", 1L);
        MockMvcBuilders.standaloneSetup(accountController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    /**
     * Method under test: {@link AccountController#getAccountsForUserId(Long)}
     */
    @Test
    void testGetAccountsForUserId2() throws Exception {
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

        ArrayList<Account> accountList = new ArrayList<>();
        accountList.add(account);
        Optional<List<Account>> ofResult = Optional.of(accountList);
        when(accountService.getAccountsForUser(Mockito.<Long>any())).thenReturn(ofResult);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/user/{uid}/accounts", 1L);
        MockMvcBuilders.standaloneSetup(accountController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "[{\"accountNo\":1234567890,\"ifscCode\":\"Ifsc Code\",\"isActive\":true,\"mailingAddress\":{\"addressId\":1,\"address\":\"42"
                                        + " Main St\",\"city\":\"Oxford\",\"state\":\"MD\",\"pincode\":1},\"balance\":10.0,\"transactionPassword\":\"aWxvdmV5b3U"
                                        + "=\",\"user\":{\"uid\":1,\"firstName\":\"Jane\",\"middleName\":\"Middle Name\",\"lastName\":\"Doe\",\"gender\":\"Gender\","
                                        + "\"fatherName\":\"Father Name\",\"phoneNumber\":\"6625550144\",\"emailId\":\"42\",\"panNumber\":\"42\",\"aadharNumber\""
                                        + ":\"42\",\"dob\":\"Dob\",\"occupation\":\"Occupation\",\"sourceOfIncome\":\"Source Of Income\",\"grossAnnualIncome\":\"Gross"
                                        + " Annual Income\",\"loginPassword\":\"aWxvdmV5b3U=\",\"kyc\":true,\"residentialAddress\":{\"addressId\":1,\"address\":\"42"
                                        + " Main St\",\"city\":\"Oxford\",\"state\":\"MD\",\"pincode\":1},\"permanentAddress\":{\"addressId\":1,\"address\":\"42"
                                        + " Main St\",\"city\":\"Oxford\",\"state\":\"MD\",\"pincode\":1}}}]"));
    }

    /**
     * Method under test: {@link AccountController#getAccountsForUserId(Long)}
     */
    @Test
    void testGetAccountsForUserId3() throws Exception {
        Optional<List<Account>> emptyResult = Optional.empty();
        when(accountService.getAccountsForUser(Mockito.<Long>any())).thenReturn(emptyResult);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/user/{uid}/accounts", 1L);
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(accountController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    /**
     * Method under test: {@link AccountController#getAllAccounts()}
     */
    @Test
    void testGetAllAccounts() throws Exception {
        when(accountService.listAll()).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/accounts");
        MockMvcBuilders.standaloneSetup(accountController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    /**
     * Method under test: {@link AccountController#getAllAccounts()}
     */
    @Test
    void testGetAllAccounts2() throws Exception {
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

        ArrayList<Account> accountList = new ArrayList<>();
        accountList.add(account);
        when(accountService.listAll()).thenReturn(accountList);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/accounts");
        MockMvcBuilders.standaloneSetup(accountController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "[{\"accountNo\":1234567890,\"ifscCode\":\"Ifsc Code\",\"isActive\":true,\"mailingAddress\":{\"addressId\":1,\"address\":\"42"
                                        + " Main St\",\"city\":\"Oxford\",\"state\":\"MD\",\"pincode\":1},\"balance\":10.0,\"transactionPassword\":\"aWxvdmV5b3U"
                                        + "=\",\"user\":{\"uid\":1,\"firstName\":\"Jane\",\"middleName\":\"Middle Name\",\"lastName\":\"Doe\",\"gender\":\"Gender\","
                                        + "\"fatherName\":\"Father Name\",\"phoneNumber\":\"6625550144\",\"emailId\":\"42\",\"panNumber\":\"42\",\"aadharNumber\""
                                        + ":\"42\",\"dob\":\"Dob\",\"occupation\":\"Occupation\",\"sourceOfIncome\":\"Source Of Income\",\"grossAnnualIncome\":\"Gross"
                                        + " Annual Income\",\"loginPassword\":\"aWxvdmV5b3U=\",\"kyc\":true,\"residentialAddress\":{\"addressId\":1,\"address\":\"42"
                                        + " Main St\",\"city\":\"Oxford\",\"state\":\"MD\",\"pincode\":1},\"permanentAddress\":{\"addressId\":1,\"address\":\"42"
                                        + " Main St\",\"city\":\"Oxford\",\"state\":\"MD\",\"pincode\":1}}}]"));
    }

    /**
     * Method under test: {@link AccountController#getParticularAccount(Long)}
     */
    @Test
    void testGetParticularAccount() throws Exception {
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
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/accounts/{accountNo}", 1234567890L);
        MockMvcBuilders.standaloneSetup(accountController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"accountNo\":1234567890,\"ifscCode\":\"Ifsc Code\",\"isActive\":true,\"mailingAddress\":{\"addressId\":1,\"address\":\"42"
                                        + " Main St\",\"city\":\"Oxford\",\"state\":\"MD\",\"pincode\":1},\"balance\":10.0,\"transactionPassword\":\"aWxvdmV5b3U"
                                        + "=\",\"user\":{\"uid\":1,\"firstName\":\"Jane\",\"middleName\":\"Middle Name\",\"lastName\":\"Doe\",\"gender\":\"Gender\","
                                        + "\"fatherName\":\"Father Name\",\"phoneNumber\":\"6625550144\",\"emailId\":\"42\",\"panNumber\":\"42\",\"aadharNumber\""
                                        + ":\"42\",\"dob\":\"Dob\",\"occupation\":\"Occupation\",\"sourceOfIncome\":\"Source Of Income\",\"grossAnnualIncome\":\"Gross"
                                        + " Annual Income\",\"loginPassword\":\"aWxvdmV5b3U=\",\"kyc\":true,\"residentialAddress\":{\"addressId\":1,\"address\":\"42"
                                        + " Main St\",\"city\":\"Oxford\",\"state\":\"MD\",\"pincode\":1},\"permanentAddress\":{\"addressId\":1,\"address\":\"42"
                                        + " Main St\",\"city\":\"Oxford\",\"state\":\"MD\",\"pincode\":1}}}"));
    }

    /**
     * Method under test: {@link AccountController#getUserAccounts(Long)}
     */
    @Test
    void testGetUserAccounts() throws Exception {
        when(accountService.getAccountsByUser(Mockito.<Long>any())).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/accounts/user/{uid}", 1L);
        MockMvcBuilders.standaloneSetup(accountController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    /**
     * Method under test: {@link AccountController#getUserAccounts(Long)}
     */
    @Test
    void testGetUserAccounts2() throws Exception {
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

        ArrayList<Account> accountList = new ArrayList<>();
        accountList.add(account);
        when(accountService.getAccountsByUser(Mockito.<Long>any())).thenReturn(accountList);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/accounts/user/{uid}", 1L);
        MockMvcBuilders.standaloneSetup(accountController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "[{\"accountNo\":1234567890,\"ifscCode\":\"Ifsc Code\",\"isActive\":true,\"mailingAddress\":{\"addressId\":1,\"address\":\"42"
                                        + " Main St\",\"city\":\"Oxford\",\"state\":\"MD\",\"pincode\":1},\"balance\":10.0,\"transactionPassword\":\"aWxvdmV5b3U"
                                        + "=\",\"user\":{\"uid\":1,\"firstName\":\"Jane\",\"middleName\":\"Middle Name\",\"lastName\":\"Doe\",\"gender\":\"Gender\","
                                        + "\"fatherName\":\"Father Name\",\"phoneNumber\":\"6625550144\",\"emailId\":\"42\",\"panNumber\":\"42\",\"aadharNumber\""
                                        + ":\"42\",\"dob\":\"Dob\",\"occupation\":\"Occupation\",\"sourceOfIncome\":\"Source Of Income\",\"grossAnnualIncome\":\"Gross"
                                        + " Annual Income\",\"loginPassword\":\"aWxvdmV5b3U=\",\"kyc\":true,\"residentialAddress\":{\"addressId\":1,\"address\":\"42"
                                        + " Main St\",\"city\":\"Oxford\",\"state\":\"MD\",\"pincode\":1},\"permanentAddress\":{\"addressId\":1,\"address\":\"42"
                                        + " Main St\",\"city\":\"Oxford\",\"state\":\"MD\",\"pincode\":1}}}]"));
    }

    /**
     * Method under test: {@link AccountController#getUsersFirstAccountTransactions(Long)}
     */
    @Test
    void testGetUsersFirstAccountTransactions() throws Exception {
        when(accountService.getAccountsByUser(Mockito.<Long>any())).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/transactions/accounts/user/{uid}",
                1L);
        MockMvcBuilders.standaloneSetup(accountController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    /**
     * Method under test: {@link AccountController#getUsersFirstAccountTransactions(Long)}
     */
    @Test
    void testGetUsersFirstAccountTransactions2() throws Exception {
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

        ArrayList<Account> accountList = new ArrayList<>();
        accountList.add(account);
        when(accountService.getAccountsByUser(Mockito.<Long>any())).thenReturn(accountList);
        when(transactionService.getTransactionsByAccount(Mockito.<Long>any())).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/transactions/accounts/user/{uid}",
                1L);
        MockMvcBuilders.standaloneSetup(accountController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    /**
     * Method under test: {@link AccountController#getUsersFirstAccountTransactions(Long)}
     */
    @Test
    void testGetUsersFirstAccountTransactions3() throws Exception {
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

        ArrayList<Account> accountList = new ArrayList<>();
        accountList.add(account);
        when(accountService.getAccountsByUser(Mockito.<Long>any())).thenReturn(accountList);

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

        ArrayList<Transaction> transactionList = new ArrayList<>();
        transactionList.add(transaction);
        when(transactionService.getTransactionsByAccount(Mockito.<Long>any())).thenReturn(transactionList);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/transactions/accounts/user/{uid}",
                1L);
        MockMvcBuilders.standaloneSetup(accountController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "[{\"id\":1,\"transactionDateTime\":\"1970-01-01 00:00:00\",\"amount\":10.0,\"transactionType\":\"Transaction"
                                        + " Type\",\"remarks\":\"Remarks\",\"toAccount\":{\"accountNo\":1234567890,\"ifscCode\":\"Ifsc Code\",\"isActive\":true"
                                        + ",\"mailingAddress\":{\"addressId\":1,\"address\":\"42 Main St\",\"city\":\"Oxford\",\"state\":\"MD\",\"pincode\":1},"
                                        + "\"balance\":10.0,\"transactionPassword\":\"aWxvdmV5b3U=\",\"user\":{\"uid\":1,\"firstName\":\"Jane\",\"middleName\":\"Middle"
                                        + " Name\",\"lastName\":\"Doe\",\"gender\":\"Gender\",\"fatherName\":\"Father Name\",\"phoneNumber\":\"6625550144\","
                                        + "\"emailId\":\"42\",\"panNumber\":\"42\",\"aadharNumber\":\"42\",\"dob\":\"Dob\",\"occupation\":\"Occupation\",\"sourceOfIncome"
                                        + "\":\"Source Of Income\",\"grossAnnualIncome\":\"Gross Annual Income\",\"loginPassword\":\"aWxvdmV5b3U=\",\"kyc\""
                                        + ":true,\"residentialAddress\":{\"addressId\":1,\"address\":\"42 Main St\",\"city\":\"Oxford\",\"state\":\"MD\",\"pincode"
                                        + "\":1},\"permanentAddress\":{\"addressId\":1,\"address\":\"42 Main St\",\"city\":\"Oxford\",\"state\":\"MD\",\"pincode"
                                        + "\":1}}},\"fromAccount\":{\"accountNo\":1234567890,\"ifscCode\":\"Ifsc Code\",\"isActive\":true,\"mailingAddress"
                                        + "\":{\"addressId\":1,\"address\":\"42 Main St\",\"city\":\"Oxford\",\"state\":\"MD\",\"pincode\":1},\"balance\":10.0,"
                                        + "\"transactionPassword\":\"aWxvdmV5b3U=\",\"user\":{\"uid\":1,\"firstName\":\"Jane\",\"middleName\":\"Middle"
                                        + " Name\",\"lastName\":\"Doe\",\"gender\":\"Gender\",\"fatherName\":\"Father Name\",\"phoneNumber\":\"6625550144\","
                                        + "\"emailId\":\"42\",\"panNumber\":\"42\",\"aadharNumber\":\"42\",\"dob\":\"Dob\",\"occupation\":\"Occupation\",\"sourceOfIncome"
                                        + "\":\"Source Of Income\",\"grossAnnualIncome\":\"Gross Annual Income\",\"loginPassword\":\"aWxvdmV5b3U=\",\"kyc\""
                                        + ":true,\"residentialAddress\":{\"addressId\":1,\"address\":\"42 Main St\",\"city\":\"Oxford\",\"state\":\"MD\",\"pincode"
                                        + "\":1},\"permanentAddress\":{\"addressId\":1,\"address\":\"42 Main St\",\"city\":\"Oxford\",\"state\":\"MD\",\"pincode"
                                        + "\":1}}}}]"));
    }

    /**
     * Method under test: {@link AccountController#updateActiveState(String, Long)}
     */
    @Test
    void testUpdateActiveState() throws Exception {
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.put("/account/{accountId}/active", 1L)
                .header("Authorization", "Basic QWxhZGRpbjpvcGVuIHNlc2FtZQ==");
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(accountController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().is(401));
    }

    /**
     * Method under test: {@link AccountController#updateActiveState(String, Long)}
     */
    @Test
    void testUpdateActiveState2() throws Exception {
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.put("/account/{accountId}/active", 1L)
                .header("Authorization", "https://example.org/example");
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(accountController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().is(401));
    }

    /**
     * Method under test: {@link AccountController#updateActiveState(String, Long)}
     */
    @Test
    void testUpdateActiveState3() throws Exception {
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.put("/account/{accountId}/active", 1L)
                .header("Authorization", "Values");
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(accountController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().is(401));
    }

    /**
     * Method under test: {@link AccountController#updateActiveState(String, Long)}
     */
    @Test
    void testUpdateActiveState4() throws Exception {
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.put("/account/{accountId}/active", 1L)
                .header("Authorization", 42, "Values");
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(accountController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().is(401));
    }
}

