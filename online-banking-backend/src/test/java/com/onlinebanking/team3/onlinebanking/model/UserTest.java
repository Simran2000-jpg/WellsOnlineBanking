package com.onlinebanking.team3.onlinebanking.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class UserTest {
    /**
     * Methods under test:
     *
     * <ul>
     *   <li>{@link User#User()}
     *   <li>{@link User#setAadharNumber(String)}
     *   <li>{@link User#setDob(String)}
     *   <li>{@link User#setEmailId(String)}
     *   <li>{@link User#setFatherName(String)}
     *   <li>{@link User#setFirstName(String)}
     *   <li>{@link User#setGender(String)}
     *   <li>{@link User#setGrossAnnualIncome(String)}
     *   <li>{@link User#setLastName(String)}
     *   <li>{@link User#setMiddleName(String)}
     *   <li>{@link User#setOccupation(String)}
     *   <li>{@link User#setPanNumber(String)}
     *   <li>{@link User#setPermanentAddress(Address)}
     *   <li>{@link User#setPhoneNumber(String)}
     *   <li>{@link User#setResidentialAddress(Address)}
     *   <li>{@link User#setSourceOfIncome(String)}
     *   <li>{@link User#setUid(long)}
     *   <li>{@link User#getAadharNumber()}
     *   <li>{@link User#getDob()}
     *   <li>{@link User#getEmailId()}
     *   <li>{@link User#getFatherName()}
     *   <li>{@link User#getFirstName()}
     *   <li>{@link User#getGender()}
     *   <li>{@link User#getGrossAnnualIncome()}
     *   <li>{@link User#getLastName()}
     *   <li>{@link User#getLoginPassword()}
     *   <li>{@link User#getMiddleName()}
     *   <li>{@link User#getOccupation()}
     *   <li>{@link User#getPanNumber()}
     *   <li>{@link User#getPermanentAddress()}
     *   <li>{@link User#getPhoneNumber()}
     *   <li>{@link User#getResidentialAddress()}
     *   <li>{@link User#getSourceOfIncome()}
     *   <li>{@link User#getUid()}
     * </ul>
     */
    @Test
    void testConstructor() {
        User actualUser = new User();
        actualUser.setAadharNumber("42");
        actualUser.setDob("Dob");
        actualUser.setEmailId("42");
        actualUser.setFatherName("Father Name");
        actualUser.setFirstName("Jane");
        actualUser.setGender("Gender");
        actualUser.setGrossAnnualIncome("Gross Annual Income");
        actualUser.setLastName("Doe");
        actualUser.setMiddleName("Middle Name");
        actualUser.setOccupation("Occupation");
        actualUser.setPanNumber("42");
        Address permanentAddress = new Address();
        permanentAddress.setAddress("42 Main St");
        permanentAddress.setAddressId(1L);
        permanentAddress.setCity("Oxford");
        permanentAddress.setPincode(1);
        permanentAddress.setState("MD");
        actualUser.setPermanentAddress(permanentAddress);
        actualUser.setPhoneNumber("6625550144");
        Address residentialAddress = new Address();
        residentialAddress.setAddress("42 Main St");
        residentialAddress.setAddressId(1L);
        residentialAddress.setCity("Oxford");
        residentialAddress.setPincode(1);
        residentialAddress.setState("MD");
        actualUser.setResidentialAddress(residentialAddress);
        actualUser.setSourceOfIncome("Source Of Income");
        actualUser.setUid(1L);
        String actualAadharNumber = actualUser.getAadharNumber();
        String actualDob = actualUser.getDob();
        String actualEmailId = actualUser.getEmailId();
        String actualFatherName = actualUser.getFatherName();
        String actualFirstName = actualUser.getFirstName();
        String actualGender = actualUser.getGender();
        String actualGrossAnnualIncome = actualUser.getGrossAnnualIncome();
        String actualLastName = actualUser.getLastName();
        actualUser.getLoginPassword();
        String actualMiddleName = actualUser.getMiddleName();
        String actualOccupation = actualUser.getOccupation();
        String actualPanNumber = actualUser.getPanNumber();
        Address actualPermanentAddress = actualUser.getPermanentAddress();
        String actualPhoneNumber = actualUser.getPhoneNumber();
        Address actualResidentialAddress = actualUser.getResidentialAddress();
        String actualSourceOfIncome = actualUser.getSourceOfIncome();
        assertEquals("42", actualAadharNumber);
        assertEquals("Dob", actualDob);
        assertEquals("42", actualEmailId);
        assertEquals("Father Name", actualFatherName);
        assertEquals("Jane", actualFirstName);
        assertEquals("Gender", actualGender);
        assertEquals("Gross Annual Income", actualGrossAnnualIncome);
        assertEquals("Doe", actualLastName);
        assertEquals("Middle Name", actualMiddleName);
        assertEquals("Occupation", actualOccupation);
        assertEquals("42", actualPanNumber);
        assertSame(permanentAddress, actualPermanentAddress);
        assertEquals("6625550144", actualPhoneNumber);
        assertSame(residentialAddress, actualResidentialAddress);
        assertEquals("Source Of Income", actualSourceOfIncome);
        assertEquals(1L, actualUser.getUid());
    }

    /**
     * Method under test: {@link User#User(long, String, String, String, String, String, String, String, String, String, String, String, String, String, Boolean, String, Address, Address)}
     */
    @Test
    void testConstructor2() {
        Address residentialAddress = new Address();
        residentialAddress.setAddress("42 Main St");
        residentialAddress.setAddressId(1L);
        residentialAddress.setCity("Oxford");
        residentialAddress.setPincode(1);
        residentialAddress.setState("MD");

        Address permanentAddress = new Address();
        permanentAddress.setAddress("42 Main St");
        permanentAddress.setAddressId(1L);
        permanentAddress.setCity("Oxford");
        permanentAddress.setPincode(1);
        permanentAddress.setState("MD");
        User actualUser = new User(1L, "Jane", "Middle Name", "Doe", "6625550144", "42", "42", "42", "Dob", "Occupation",
                "Source Of Income", "Gross Annual Income", "Gender", "iloveyou", true, "Father Name", residentialAddress,
                permanentAddress);

        assertEquals("42", actualUser.getAadharNumber());
        assertEquals(1L, actualUser.getUid());
        assertEquals("Source Of Income", actualUser.getSourceOfIncome());
        assertSame(residentialAddress, actualUser.getResidentialAddress());
        assertEquals("6625550144", actualUser.getPhoneNumber());
        assertEquals("Dob", actualUser.getDob());
        assertEquals("Father Name", actualUser.getFatherName());
        assertTrue(actualUser.getKyc());
        assertEquals("Doe", actualUser.getLastName());
        assertEquals("Jane", actualUser.getFirstName());
        assertSame(permanentAddress, actualUser.getPermanentAddress());
        assertEquals("42", actualUser.getPanNumber());
        assertEquals("iloveyou", actualUser.getLoginPassword());
        assertEquals("Middle Name", actualUser.getMiddleName());
        assertEquals("42", actualUser.getEmailId());
        assertEquals("Gender", actualUser.getGender());
        assertEquals("Occupation", actualUser.getOccupation());
        assertEquals("Gross Annual Income", actualUser.getGrossAnnualIncome());
    }

    /**
     * Method under test: {@link User#setLoginPassword(String)}
     */
    @Test
    void testSetLoginPassword() {
        User user = new User();
        user.setLoginPassword("iloveyou");
        assertEquals("aWxvdmV5b3U=", user.getLoginPassword());
    }

    /**
     * Method under test: {@link User#getKyc()}
     */
    @Test
    void testGetKyc() {
        assertFalse((new User()).getKyc());
    }

    /**
     * Method under test: {@link User#getKyc()}
     */
    @Test
    void testGetKyc2() {
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
        assertTrue(user.getKyc());
    }

    /**
     * Method under test: {@link User#setKyc(Boolean)}
     */
    @Test
    void testSetKyc() {
        User user = new User();
        user.setKyc(true);
        assertTrue(user.getKyc());
    }
}

