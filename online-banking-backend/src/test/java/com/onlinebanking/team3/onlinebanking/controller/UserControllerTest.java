package com.onlinebanking.team3.onlinebanking.controller;

import static org.mockito.Mockito.when;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.onlinebanking.team3.onlinebanking.model.Account;
import com.onlinebanking.team3.onlinebanking.model.Address;
import com.onlinebanking.team3.onlinebanking.model.User;
import com.onlinebanking.team3.onlinebanking.service.AccountService;
import com.onlinebanking.team3.onlinebanking.service.AddressService;
import com.onlinebanking.team3.onlinebanking.service.BeneficiaryService;
import com.onlinebanking.team3.onlinebanking.service.UserService;

import java.util.Optional;

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

@ContextConfiguration(classes = {UserController.class})
@ExtendWith(SpringExtension.class)
class UserControllerTest {
    @MockBean
    private AccountService accountService;

    @MockBean
    private AddressService addressService;

    @MockBean
    private BeneficiaryService beneficiaryService;

    @Autowired
    private UserController userController;

    @MockBean
    private UserService userService;

    /**
     * Method under test: {@link UserController#createUser(User)}
     */
    @Test
    void testCreateUser() throws Exception {
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
        when(accountService.createAccount(Mockito.<Account>any())).thenReturn(account);

        Address address = new Address();
        address.setAddress("42 Main St");
        address.setAddressId(1L);
        address.setCity("Oxford");
        address.setPincode(1);
        address.setState("MD");
        when(addressService.saveAddress(Mockito.<Address>any())).thenReturn(address);

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
        when(userService.registerUser(Mockito.<User>any())).thenReturn(user2);

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
        String content = (new ObjectMapper()).writeValueAsString(user3);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/createUser")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);

        // Act and Assert
        MockMvcBuilders.standaloneSetup(userController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("text/plain;charset=ISO-8859-1"))
                .andExpect(MockMvcResultMatchers.content().string("Registration Successful"));
    }

    /**
     * Method under test: {@link UserController#getUserById(Long)}
     */
    @Test
    void testGetUserById() throws Exception {
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
        when(userService.getUserById(Mockito.<Long>any())).thenReturn(user);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/users/{uid}", 1L);

        // Act and Assert
        MockMvcBuilders.standaloneSetup(userController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"uid\":1,\"firstName\":\"Jane\",\"middleName\":\"Middle Name\",\"lastName\":\"Doe\",\"gender\":\"Gender\",\"fatherName"
                                        + "\":\"Father Name\",\"phoneNumber\":\"6625550144\",\"emailId\":\"42\",\"panNumber\":\"42\",\"aadharNumber\":\"42\",\"dob\""
                                        + ":\"Dob\",\"occupation\":\"Occupation\",\"sourceOfIncome\":\"Source Of Income\",\"grossAnnualIncome\":\"Gross Annual"
                                        + " Income\",\"loginPassword\":\"aWxvdmV5b3U=\",\"kyc\":true,\"residentialAddress\":{\"addressId\":1,\"address\":\"42"
                                        + " Main St\",\"city\":\"Oxford\",\"state\":\"MD\",\"pincode\":1},\"permanentAddress\":{\"addressId\":1,\"address\":\"42"
                                        + " Main St\",\"city\":\"Oxford\",\"state\":\"MD\",\"pincode\":1}}"));
    }

    /**
     * Method under test: {@link UserController#updateUser(Long, User)}
     */
    @Test
    void testUpdateUser() throws Exception {
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
        when(userService.updateUser(Mockito.<Long>any(), Mockito.<User>any())).thenReturn(user);

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
        String content = (new ObjectMapper()).writeValueAsString(user2);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.put("/findUser/{userId}", 1L)
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);

        // Act and Assert
        MockMvcBuilders.standaloneSetup(userController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"uid\":1,\"firstName\":\"Jane\",\"middleName\":\"Middle Name\",\"lastName\":\"Doe\",\"gender\":\"Gender\",\"fatherName"
                                        + "\":\"Father Name\",\"phoneNumber\":\"6625550144\",\"emailId\":\"42\",\"panNumber\":\"42\",\"aadharNumber\":\"42\",\"dob\""
                                        + ":\"Dob\",\"occupation\":\"Occupation\",\"sourceOfIncome\":\"Source Of Income\",\"grossAnnualIncome\":\"Gross Annual"
                                        + " Income\",\"loginPassword\":\"aWxvdmV5b3U=\",\"kyc\":true,\"residentialAddress\":{\"addressId\":1,\"address\":\"42"
                                        + " Main St\",\"city\":\"Oxford\",\"state\":\"MD\",\"pincode\":1},\"permanentAddress\":{\"addressId\":1,\"address\":\"42"
                                        + " Main St\",\"city\":\"Oxford\",\"state\":\"MD\",\"pincode\":1}}"));
    }

    /**
     * Method under test: {@link UserController#demo()}
     */
    @Test
    void testDemo() throws Exception {
        // Arrange
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/welcome");

        // Act and Assert
        MockMvcBuilders.standaloneSetup(userController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("text/plain;charset=ISO-8859-1"))
                .andExpect(MockMvcResultMatchers.content().string("Welcome User"));
    }

    /**
     * Method under test: {@link UserController#demo()}
     */
    @Test
    void testDemo2() throws Exception {
        // Arrange
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/welcome");
        requestBuilder.contentType("https://example.org/example");

        // Act and Assert
        MockMvcBuilders.standaloneSetup(userController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("text/plain;charset=ISO-8859-1"))
                .andExpect(MockMvcResultMatchers.content().string("Welcome User"));
    }

    /**
     * Method under test: {@link UserController#getAllUsers(String)}
     */
    @Test
    void testGetAllUsers() throws Exception {
        // Arrange
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/users")
                .header("Authorization", "Basic QWxhZGRpbjpvcGVuIHNlc2FtZQ==");

        // Act
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(userController)
                .build()
                .perform(requestBuilder);

        // Assert
        actualPerformResult.andExpect(MockMvcResultMatchers.status().is(401));
    }

    /**
     * Method under test: {@link UserController#getAllUsers(String)}
     */
    @Test
    void testGetAllUsers2() throws Exception {
        // Arrange
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/users")
                .header("Authorization", "https://example.org/example");

        // Act
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(userController)
                .build()
                .perform(requestBuilder);

        // Assert
        actualPerformResult.andExpect(MockMvcResultMatchers.status().is(401));
    }

    /**
     * Method under test: {@link UserController#getAllUsers(String)}
     */
    @Test
    void testGetAllUsers3() throws Exception {
        // Arrange
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/users")
                .header("Authorization", "Values");

        // Act
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(userController)
                .build()
                .perform(requestBuilder);

        // Assert
        actualPerformResult.andExpect(MockMvcResultMatchers.status().is(401));
    }

    /**
     * Method under test: {@link UserController#getAllUsers(String)}
     */
    @Test
    void testGetAllUsers4() throws Exception {
        // Arrange
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/users")
                .header("Authorization", 42, "Values");

        // Act
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(userController)
                .build()
                .perform(requestBuilder);

        // Assert
        actualPerformResult.andExpect(MockMvcResultMatchers.status().is(401));
    }

    /**
     * Method under test: {@link UserController#getUserByPhoneNumber(String)}
     */
    @Test
    void testGetUserByPhoneNumber() throws Exception {
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
        when(userService.findUserByPhoneNumber(Mockito.<String>any())).thenReturn(ofResult);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/users/phone/{ph}", "Ph");

        // Act and Assert
        MockMvcBuilders.standaloneSetup(userController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"uid\":1,\"firstName\":\"Jane\",\"middleName\":\"Middle Name\",\"lastName\":\"Doe\",\"gender\":\"Gender\",\"fatherName"
                                        + "\":\"Father Name\",\"phoneNumber\":\"6625550144\",\"emailId\":\"42\",\"panNumber\":\"42\",\"aadharNumber\":\"42\",\"dob\""
                                        + ":\"Dob\",\"occupation\":\"Occupation\",\"sourceOfIncome\":\"Source Of Income\",\"grossAnnualIncome\":\"Gross Annual"
                                        + " Income\",\"loginPassword\":\"aWxvdmV5b3U=\",\"kyc\":true,\"residentialAddress\":{\"addressId\":1,\"address\":\"42"
                                        + " Main St\",\"city\":\"Oxford\",\"state\":\"MD\",\"pincode\":1},\"permanentAddress\":{\"addressId\":1,\"address\":\"42"
                                        + " Main St\",\"city\":\"Oxford\",\"state\":\"MD\",\"pincode\":1}}"));
    }

    /**
     * Method under test: {@link UserController#kycVerifyUser(String, Long)}
     */
    @Test
    void testKycVerifyUser() throws Exception {
        // Arrange
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.put("/users/{userId}/verify", 1L)
                .header("Authorization", "Basic QWxhZGRpbjpvcGVuIHNlc2FtZQ==");

        // Act
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(userController)
                .build()
                .perform(requestBuilder);

        // Assert
        actualPerformResult.andExpect(MockMvcResultMatchers.status().is(401));
    }

    /**
     * Method under test: {@link UserController#kycVerifyUser(String, Long)}
     */
    @Test
    void testKycVerifyUser2() throws Exception {
        // Arrange
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.put("/users/{userId}/verify", 1L)
                .header("Authorization", "https://example.org/example");

        // Act
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(userController)
                .build()
                .perform(requestBuilder);

        // Assert
        actualPerformResult.andExpect(MockMvcResultMatchers.status().is(401));
    }

    /**
     * Method under test: {@link UserController#kycVerifyUser(String, Long)}
     */
    @Test
    void testKycVerifyUser3() throws Exception {
        // Arrange
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.put("/users/{userId}/verify", 1L)
                .header("Authorization", "Values");

        // Act
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(userController)
                .build()
                .perform(requestBuilder);

        // Assert
        actualPerformResult.andExpect(MockMvcResultMatchers.status().is(401));
    }

    /**
     * Method under test: {@link UserController#kycVerifyUser(String, Long)}
     */
    @Test
    void testKycVerifyUser4() throws Exception {
        // Arrange
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.put("/users/{userId}/verify", 1L)
                .header("Authorization", 42, "Values");

        // Act
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(userController)
                .build()
                .perform(requestBuilder);

        // Assert
        actualPerformResult.andExpect(MockMvcResultMatchers.status().is(401));
    }

    /**
     * Method under test: {@link UserController#loginUser(String, String)}
     */
    @Test
    void testLoginUser() throws Exception {
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
        when(userService.findUserByPhoneNumber(Mockito.<String>any())).thenReturn(ofResult);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/loginUser")
                .param("password", "foo")
                .param("phoneNumber", "foo");

        // Act
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(userController)
                .build()
                .perform(requestBuilder);

        // Assert
        actualPerformResult.andExpect(MockMvcResultMatchers.status().is(401));
    }

    /**
     * Method under test: {@link UserController#loginUser(String, String)}
     */
    @Test
    void testLoginUser2() throws Exception {
        // Arrange
        Optional<User> emptyResult = Optional.empty();
        when(userService.findUserByPhoneNumber(Mockito.<String>any())).thenReturn(emptyResult);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/loginUser")
                .param("password", "foo")
                .param("phoneNumber", "foo");

        // Act
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(userController)
                .build()
                .perform(requestBuilder);

        // Assert
        actualPerformResult.andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    /**
     * Method under test: {@link UserController#registerInternetBanking(String, Long, String)}
     */
    @Test
    void testRegisterInternetBanking() throws Exception {
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
        when(accountService.getAccountById(Mockito.<Long>any())).thenReturn(account);

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
        when(userService.getUserById(Mockito.<Long>any())).thenReturn(user2);
        MockHttpServletRequestBuilder putResult = MockMvcRequestBuilders.put("/register");
        MockHttpServletRequestBuilder requestBuilder = putResult.param("accountNumber", String.valueOf(1L))
                .param("emailId", "foo")
                .param("transactionPassword", "foo");

        // Act
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(userController)
                .build()
                .perform(requestBuilder);

        // Assert
        actualPerformResult.andExpect(MockMvcResultMatchers.status().is(400))
                .andExpect(MockMvcResultMatchers.content().contentType("text/plain;charset=ISO-8859-1"))
                .andExpect(MockMvcResultMatchers.content().string("Account Number and Email Id mismatch"));
    }

    /**
     * Method under test: {@link UserController#registerInternetBanking(String, Long, String)}
     */
    @Test
    void testRegisterInternetBanking2() throws Exception {
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
        user.setEmailId("foo");
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
        account2.setAccountType("3");
        account2.setBalance(10.0d);
        account2.setIfscCode("Ifsc Code");
        account2.setIsActive(true);
        account2.setMailingAddress(mailingAddress2);
        account2.setTransactionPassword("iloveyou");
        account2.setUser(user2);
        when(accountService.createAccount(Mockito.<Account>any())).thenReturn(account2);
        when(accountService.getAccountById(Mockito.<Long>any())).thenReturn(account);

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
        when(userService.getUserById(Mockito.<Long>any())).thenReturn(user3);
        MockHttpServletRequestBuilder putResult = MockMvcRequestBuilders.put("/register");
        MockHttpServletRequestBuilder requestBuilder = putResult.param("accountNumber", String.valueOf(1L))
                .param("emailId", "foo")
                .param("transactionPassword", "foo");

        // Act and Assert
        MockMvcBuilders.standaloneSetup(userController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("text/plain;charset=ISO-8859-1"))
                .andExpect(MockMvcResultMatchers.content().string("Registration Successful"));
    }
}

