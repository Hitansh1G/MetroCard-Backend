package com.example.geektrust.EntityTest;

import com.example.geektrust.Entity.MetroCard;
import com.example.geektrust.Enums.PassengerType;
import com.example.geektrust.Enums.Station;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.example.geektrust.Constants.constants.TAX_PERCENT;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class StationTest {

    private MetroCard mockMetroCard;
    private PassengerType passengerType;
    private Station station;

    @BeforeEach
    void setup() {
        mockMetroCard = mock(MetroCard.class);
        passengerType = PassengerType.ADULT;
        station = Station.CENTRAL;
    }

    @Test
    void checkIn_CallsMetroCardCheckIn() {
        double amount = 10.0;
        station.checkIn(mockMetroCard, passengerType, amount);

        verify(mockMetroCard).checkIn(amount, station);
    }

    @Test
    void collectExpenseAtTheStation_CalculatesCorrectly() {
        double rechargedMoney = 100.0;
        double travelExpenses = 50.0;
        double discount = 10.0;

        station.collectExpenseAtTheStation(rechargedMoney, travelExpenses, discount);

        double expectedTotalMoneyCollected = travelExpenses + (TAX_PERCENT * rechargedMoney);
//        assertEquals(expectedTotalMoneyCollected, station.getTotalMoneyCollected());

        assertEquals(discount, station.getTotalDiscountGiven());
    }

    @Test
    void printStationSummary_PrintsCorrectly() {
        double totalMoneyCollected = 100.0;
        double totalDiscountGiven = 20.0;
        station.printStationSummary();

    }

}
