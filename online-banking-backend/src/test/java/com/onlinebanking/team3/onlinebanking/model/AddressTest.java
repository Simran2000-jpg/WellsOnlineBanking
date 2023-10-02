package com.onlinebanking.team3.onlinebanking.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

class AddressTest {
    /**
     * Method under test: {@link Address#Address(String, String, String, int)}
     */
    @Test
    void testConstructor() {
        // Arrange and Act
        Address actualAddress = new Address("42 Main St", "Oxford", "MD", 1);

        // Assert
        assertEquals("42 Main St", actualAddress.getAddress());
        assertEquals("MD", actualAddress.getState());
        assertEquals(1, actualAddress.getPincode());
        assertEquals("Oxford", actualAddress.getCity());
    }

    /**
     * Method under test: {@link Address#Address(String, String, String, int)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testConstructor2() {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.NullPointerException: address is marked non-null but is null
        //       at com.onlinebanking.team3.onlinebanking.model.Address.<init>(Address.java:20)
        //   See https://diff.blue/R013 to resolve this issue.

        // Arrange and Act
        new Address(null, null, null, 1);

    }

    /**
     * Method under test: {@link Address#Address(String, String, String, int)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testConstructor3() {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.NullPointerException: city is marked non-null but is null
        //       at com.onlinebanking.team3.onlinebanking.model.Address.<init>(Address.java:20)
        //   See https://diff.blue/R013 to resolve this issue.

        // Arrange and Act
        new Address("foo", null, null, 1);

    }

    /**
     * Method under test: {@link Address#Address(String, String, String, int)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testConstructor4() {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.NullPointerException: state is marked non-null but is null
        //       at com.onlinebanking.team3.onlinebanking.model.Address.<init>(Address.java:20)
        //   See https://diff.blue/R013 to resolve this issue.

        // Arrange and Act
        new Address("foo", "foo", null, 1);

    }
}

