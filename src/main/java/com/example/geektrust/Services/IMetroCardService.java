package com.example.geektrust.Services;

import com.example.geektrust.Entity.PassengerType;
import com.example.geektrust.Entity.Station;

public interface IMetroCardService {
    void createMetroCard(String name, Double balance);

    void checkIn(String metroCardId, PassengerType passengerType, Station station);

    void printSummary();
}
