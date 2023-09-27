package com.onlinebanking.team3.onlinebanking.controller;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.onlinebanking.team3.onlinebanking.model.Address;
import com.onlinebanking.team3.onlinebanking.model.Beneficiary;
import com.onlinebanking.team3.onlinebanking.model.User;
import com.onlinebanking.team3.onlinebanking.service.BeneficiaryService;
import com.onlinebanking.team3.onlinebanking.service.UserService;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@ContextConfiguration(classes = {BeneficiaryController.class})
@ExtendWith(SpringExtension.class)
class BeneficiaryControllerTest {
    @Autowired
    private BeneficiaryController beneficiaryController;

    @MockBean
    private BeneficiaryService beneficiaryService;

    @MockBean
    private UserService userService;

    /**
     * Method under test: {@link BeneficiaryController#createBeneficiary(long, Beneficiary)}
     */
    @Test
    void testCreateBeneficiary() throws Exception {
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

        Beneficiary beneficiary = new Beneficiary();
        beneficiary.setAccountNo("3");
        beneficiary.setBid(1L);
        beneficiary.setIfscCode("Ifsc Code");
        beneficiary.setName("Name");
        beneficiary.setUser(user);
        when(beneficiaryService.createBeneficiary(Mockito.<Beneficiary>any())).thenReturn(beneficiary);

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

        Beneficiary beneficiary2 = new Beneficiary();
        beneficiary2.setAccountNo("3");
        beneficiary2.setBid(1L);
        beneficiary2.setIfscCode("Ifsc Code");
        beneficiary2.setName("Name");
        beneficiary2.setUser(user3);
        String content = (new ObjectMapper()).writeValueAsString(beneficiary2);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/beneficiaries/{userId}", 1L)
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        MockMvcBuilders.standaloneSetup(beneficiaryController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"bid\":1,\"ifscCode\":\"Ifsc Code\",\"accountNo\":\"3\",\"name\":\"Name\",\"user\":{\"uid\":1,\"firstName\":\"Jane\","
                                        + "\"middleName\":\"Middle Name\",\"lastName\":\"Doe\",\"gender\":\"Gender\",\"fatherName\":\"Father Name\",\"phoneNumber"
                                        + "\":\"6625550144\",\"emailId\":\"42\",\"panNumber\":\"42\",\"aadharNumber\":\"42\",\"dob\":\"Dob\",\"occupation\":\"Occupation"
                                        + "\",\"sourceOfIncome\":\"Source Of Income\",\"grossAnnualIncome\":\"Gross Annual Income\",\"loginPassword\":"
                                        + "\"aWxvdmV5b3U=\",\"kyc\":true,\"residentialAddress\":{\"addressId\":1,\"address\":\"42 Main St\",\"city\":\"Oxford\""
                                        + ",\"state\":\"MD\",\"pincode\":1},\"permanentAddress\":{\"addressId\":1,\"address\":\"42 Main St\",\"city\":\"Oxford\","
                                        + "\"state\":\"MD\",\"pincode\":1}}}"));
    }

    /**
     * Method under test: {@link BeneficiaryController#deleteBeneficiaryById(Long)}
     */
    @Test
    void testDeleteBeneficiaryById() throws Exception {
        doNothing().when(beneficiaryService).deleteBeneficiaryById(Mockito.<Long>any());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/beneficiaries/{bid}", 1L);
        MockMvcBuilders.standaloneSetup(beneficiaryController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("text/plain;charset=ISO-8859-1"))
                .andExpect(MockMvcResultMatchers.content().string("Deleted Successfully"));
    }

    /**
     * Method under test: {@link BeneficiaryController#deleteBeneficiaryById(Long)}
     */
    @Test
    void testDeleteBeneficiaryById2() throws Exception {
        doNothing().when(beneficiaryService).deleteBeneficiaryById(Mockito.<Long>any());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/beneficiaries/{bid}", 1L);
        requestBuilder.contentType("https://example.org/example");
        MockMvcBuilders.standaloneSetup(beneficiaryController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("text/plain;charset=ISO-8859-1"))
                .andExpect(MockMvcResultMatchers.content().string("Deleted Successfully"));
    }

    /**
     * Method under test: {@link BeneficiaryController#getAllBeneficiaries()}
     */
    @Test
    void testGetAllBeneficiaries() throws Exception {
        when(beneficiaryService.listAll()).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/beneficiaries");
        MockMvcBuilders.standaloneSetup(beneficiaryController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    /**
     * Method under test: {@link BeneficiaryController#getAllBeneficiaryByUser(Long)}
     */
    @Test
    void testGetAllBeneficiaryByUser() throws Exception {
        when(beneficiaryService.getBeneficiaryByUser(Mockito.<Long>any())).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/beneficiaries/{uid}", 1L);
        MockMvcBuilders.standaloneSetup(beneficiaryController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    /**
     * Method under test: {@link BeneficiaryController#getAllBeneficiaryByUser(Long)}
     */
    @Test
    void testGetAllBeneficiaryByUser2() throws Exception {
        when(beneficiaryService.listAll()).thenReturn(new ArrayList<>());
        when(beneficiaryService.getBeneficiaryByUser(Mockito.<Long>any())).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/beneficiaries/{uid}", "",
                "Uri Variables");
        MockMvcBuilders.standaloneSetup(beneficiaryController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }
}

