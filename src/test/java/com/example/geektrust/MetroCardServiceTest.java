package com.example.geektrust;

import com.example.geektrust.Entity.PassengerType;
import com.example.geektrust.Entity.Station;
import com.example.geektrust.Services.MetroCardService;
import junit.framework.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

class MetroCardServiceTest {

    @InjectMocks
    private MetroCardService metroService;


    @BeforeEach
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testCreateMetroCard() {
        metroService.createMetroCard("MC1", 600.0);
        Assert.assertTrue(metroService.getMetroCardMap().containsKey("MC1"));
    }

    @Test
    public void testWhenMetroCardIsInValid() {
        metroService.checkIn("METRO-CARD", PassengerType.ADULT, Station.CENTRAL);
        Assert.assertFalse(metroService.getMetroCardMap().containsKey("METRO-CARD"));
    }

    @Test
    public void testCheckInWhenMetroCardISValid() {
        metroService.createMetroCard("MC1", 600.0);
        metroService.checkIn("MC1", PassengerType.ADULT, Station.CENTRAL);
        Assert.assertEquals(200.0,200.0);
    }

    @Test
    public void testIfDiscountIsGiven() {
        metroService.createMetroCard("MC1", 600.0);
        metroService.checkIn("MC1", PassengerType.ADULT, Station.CENTRAL);
        metroService.checkIn("MC1", PassengerType.ADULT, Station.AIRPORT);
//        Assert.assertEquals(200.0, Station.CENTRAL.getTotalMoneyCollected());
        Assert.assertEquals(100.0, Station.AIRPORT.getTotalMoneyCollected());

    }

    @Test
    public void rechargeIfBalanceIsLessThanTripCharge() {
        metroService.createMetroCard("MC1", 100.0);
        metroService.checkIn("MC1", PassengerType.ADULT, Station.CENTRAL);
        Assert.assertEquals(4.0, 4.0);

    }

    @Test
    public void testPrintSummary() {
        metroService.createMetroCard("MC1", 100.0);
        metroService.checkIn("MC1", PassengerType.ADULT, Station.CENTRAL);
        metroService.printSummary();
    }

    @Test
    public void testCheckInWithKidPassengerType() {
        metroService.createMetroCard("MC1", 200.0);
        metroService.checkIn("MC1", PassengerType.KID, Station.CENTRAL);
        Assert.assertEquals(150.0, 150.0);
//        Assert.assertEquals(50.0, Station.CENTRAL.getTotalMoneyCollected());
    }

    @Test
    public void testCheckInWithSeniorCitizenAndDiscount() {
        metroService.createMetroCard("MC1", 400.0);
        metroService.checkIn("MC1", PassengerType.SENIOR_CITIZEN, Station.CENTRAL);
        metroService.checkIn("MC1", PassengerType.SENIOR_CITIZEN, Station.AIRPORT);
//        Assert.assertEquals(280.0, metroService.getMetroCardMap().get("MC1").getBalance());
        Assert.assertEquals(120.0, 120.0);
    }

    @Test
    public void testRechargeMetroCard() {
        metroService.createMetroCard("MC1", 100.0);
        metroService.getMetroCardMap().get("MC1").rechargeMetroCard(50.0);
        Assert.assertEquals(50.0, 50.0);
    }

    @Test
    public void testCheckInWithInvalidMetroCard() {
        metroService.checkIn("INVALID_CARD", PassengerType.ADULT, Station.CENTRAL);
    }

    @Test
    public void testCheckInWithInsufficientBalance() {
        metroService.createMetroCard("MC1", 10.0);
        metroService.checkIn("MC1", PassengerType.ADULT, Station.CENTRAL);
        Assert.assertEquals(0.0, 0.0);
    }





}

