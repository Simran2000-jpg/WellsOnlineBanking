package com.onlinebanking.team3.onlinebanking.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.junit.jupiter.api.Disabled;

import org.junit.jupiter.api.Test;

class TransactionTest {
    /**
     * Method under test: {@link Transaction#Transaction(LocalDateTime, double, String, String)}
     */
    @Test
    void testConstructor() {
        Transaction actualTransaction = new Transaction(LocalDate.of(1970, 1, 1).atStartOfDay(), 10.0d, "Transaction Type",
                "Remarks");

        assertEquals(10.0d, actualTransaction.getAmount());
        assertEquals("Transaction Type", actualTransaction.getTransactionType());
        assertEquals("00:00", actualTransaction.getTransactionDateTime().toLocalTime().toString());
        assertEquals("Remarks", actualTransaction.getRemarks());
    }

    /**
     * Method under test: {@link Transaction#Transaction(LocalDateTime, double, String, String)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testConstructor2() {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.NullPointerException: transactionDateTime is marked non-null but is null
        //       at com.onlinebanking.team3.onlinebanking.model.Transaction.<init>(Transaction.java:33)
        //   See https://diff.blue/R013 to resolve this issue.

        new Transaction(null, 10.0d, "foo", "foo");

    }

    /**
     * Methods under test:
     *
     * <ul>
     *   <li>{@link Transaction#setAmount(double)}
     *   <li>{@link Transaction#setFromAccount(Account)}
     *   <li>{@link Transaction#setId(Long)}
     *   <li>{@link Transaction#setRemarks(String)}
     *   <li>{@link Transaction#setToAccount(Account)}
     *   <li>{@link Transaction#setTransactionDateTime(LocalDateTime)}
     *   <li>{@link Transaction#setTransactionType(String)}
     *   <li>{@link Transaction#getAmount()}
     *   <li>{@link Transaction#getFromAccount()}
     *   <li>{@link Transaction#getId()}
     *   <li>{@link Transaction#getRemarks()}
     *   <li>{@link Transaction#getToAccount()}
     *   <li>{@link Transaction#getTransactionDateTime()}
     *   <li>{@link Transaction#getTransactionType()}
     * </ul>
     */
    @Test
    void testSetAmount() {
        Transaction transaction = new Transaction();
        transaction.setAmount(10.0d);
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
        transaction.setFromAccount(fromAccount);
        transaction.setId(1L);
        transaction.setRemarks("Remarks");
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
        transaction.setToAccount(toAccount);
        LocalDateTime transactionDateTime = LocalDate.of(1970, 1, 1).atStartOfDay();
        transaction.setTransactionDateTime(transactionDateTime);
        transaction.setTransactionType("Transaction Type");
        double actualAmount = transaction.getAmount();
        Account actualFromAccount = transaction.getFromAccount();
        Long actualId = transaction.getId();
        String actualRemarks = transaction.getRemarks();
        Account actualToAccount = transaction.getToAccount();
        LocalDateTime actualTransactionDateTime = transaction.getTransactionDateTime();
        String actualTransactionType = transaction.getTransactionType();
        assertEquals(10.0d, actualAmount);
        assertSame(fromAccount, actualFromAccount);
        assertEquals(1L, actualId.longValue());
        assertEquals("Remarks", actualRemarks);
        assertSame(toAccount, actualToAccount);
        assertSame(transactionDateTime, actualTransactionDateTime);
        assertEquals("Transaction Type", actualTransactionType);
    }
}

