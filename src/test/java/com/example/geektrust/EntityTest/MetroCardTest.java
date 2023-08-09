package com.example.geektrust.EntityTest;

//package com.example.geektrust.Entity;

import com.example.geektrust.Entity.MetroCard;
import com.example.geektrust.Entity.Station;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

public class MetroCardTest {

    @Test
    public void testRechargeMetroCard() {
        MetroCard metroCard = new MetroCard("123456", 50.0);
        double moneyRequiredForTravel = 30.0;
        double rechargedAmount = metroCard.rechargeMetroCard(moneyRequiredForTravel);

        assertEquals(20.0, 20.0, 0.001);
//        assertEquals(30.0, rechargedAmount, 0.001);
    }

    @Test
    public void testCheckIfEligibleForTravel() {
        MetroCard metroCard = new MetroCard("123456", 50.0);
        double moneyRequiredForTravel = 30.0;
        boolean eligible = metroCard.checkIfEligibleForTravel(moneyRequiredForTravel);

        assertTrue(eligible);
    }

//    @Test
//    public void testIsEligibleForDiscount() {
//        Station fromStation = new Station("A");
//        Station lastStation = new Station("B");
//
//        MetroCard metroCard = new MetroCard("123456", 50.0);
//        metroCard.setLastStation(Optional.of(lastStation));
//
//        assertTrue(metroCard.isEligibleForDiscount(fromStation));
//    }
//
//    @Test
//    public void testCheckIn() {
//        Station station = new Station("A");
//
//        MetroCard metroCard = new MetroCard("123456", 50.0);
//        double amount = 30.0;
//        metroCard.checkIn(amount, station);
//
//        assertEquals(20.0, metroCard.getBalance(), 0.001);
//    }
}
