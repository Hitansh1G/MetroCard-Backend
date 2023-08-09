package com.example.geektrust.EntityTest;

//package com.example.geektrust.Entity;

import com.example.geektrust.Enums.PassengerType;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PassengerTypeTest {

    @Test
    void testPassengerTypeValues() {
        assertEquals(3, PassengerType.values().length);
        assertEquals(PassengerType.ADULT, PassengerType.valueOf("ADULT"));
        assertEquals(PassengerType.KID, PassengerType.valueOf("KID"));
        assertEquals(PassengerType.SENIOR_CITIZEN, PassengerType.valueOf("SENIOR_CITIZEN"));
    }
}
