package com.onlinebanking.team3.onlinebanking.service;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.onlinebanking.team3.onlinebanking.model.Address;
import com.onlinebanking.team3.onlinebanking.model.Beneficiary;
import com.onlinebanking.team3.onlinebanking.model.User;
import com.onlinebanking.team3.onlinebanking.repository.BeneficiaryRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {BeneficiaryService.class})
@ExtendWith(SpringExtension.class)
class BeneficiaryServiceTest {
    @MockBean
    private BeneficiaryRepository beneficiaryRepository;

    @Autowired
    private BeneficiaryService beneficiaryService;

    /**
     * Method under test: {@link BeneficiaryService#createBeneficiary(Beneficiary)}
     */
    @Test
    void testCreateBeneficiary() {
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

        Beneficiary beneficiary = new Beneficiary();
        beneficiary.setAccountNo("3");
        beneficiary.setBid(1L);
        beneficiary.setIfscCode("Ifsc Code");
        beneficiary.setName("Name");
        beneficiary.setUser(user);
        when(beneficiaryRepository.save(Mockito.<Beneficiary>any())).thenReturn(beneficiary);

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

        Beneficiary beneficiary2 = new Beneficiary();
        beneficiary2.setAccountNo("3");
        beneficiary2.setBid(1L);
        beneficiary2.setIfscCode("Ifsc Code");
        beneficiary2.setName("Name");
        beneficiary2.setUser(user2);

        // Act and Assert
        assertSame(beneficiary, beneficiaryService.createBeneficiary(beneficiary2));
        verify(beneficiaryRepository).save(Mockito.<Beneficiary>any());
    }
}

