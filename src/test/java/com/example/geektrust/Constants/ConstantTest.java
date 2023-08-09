package com.example.geektrust.Constants;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ConstantsTest {

    @Test
    void testConstantValues() {
        assertEquals(0.5, constants.discount_percentage);
        assertEquals(200, constants.ADULT_CHARGE);
        assertEquals(50, constants.KID_CHARGE);
        assertEquals(100, constants.SENIOR_CITIZEN_CHARGE);
        assertEquals(0, constants.ZERO);
        assertEquals(0.2, constants.TAX_PERCENT);
        assertEquals("TOTAL_COLLECTION ", constants.TOTAL_COLLECTION);
        assertEquals(" ", constants.BLANK_SPACE);
        assertEquals("PASSENGER_TYPE_SUMMARY", constants.SUMMARY);
    }
}
