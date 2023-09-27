package com.onlinebanking.team3.onlinebanking.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

class BeneficiaryTest {
    /**
     * Method under test: {@link Beneficiary#Beneficiary(String, String, String)}
     */
    @Test
    void testConstructor() {
        Beneficiary actualBeneficiary = new Beneficiary("Ifsc Code", "3", "Name");

        assertEquals("3", actualBeneficiary.getAccountNo());
        assertEquals("Name", actualBeneficiary.getName());
        assertEquals("Ifsc Code", actualBeneficiary.getIfscCode());
    }

    /**
     * Method under test: {@link Beneficiary#Beneficiary(String, String, String)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testConstructor2() {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.NullPointerException: accountNo is marked non-null but is null
        //       at com.onlinebanking.team3.onlinebanking.model.Beneficiary.<init>(Beneficiary.java:34)
        //   See https://diff.blue/R013 to resolve this issue.

        new Beneficiary("foo", null, null);

    }

    /**
     * Method under test: {@link Beneficiary#Beneficiary(String, String, String)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testConstructor3() {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.NullPointerException: name is marked non-null but is null
        //       at com.onlinebanking.team3.onlinebanking.model.Beneficiary.<init>(Beneficiary.java:34)
        //   See https://diff.blue/R013 to resolve this issue.

        new Beneficiary("foo", "foo", null);

    }
}

