package com.example.geektrust.Services;

import com.example.geektrust.Entity.MetroCard;
import com.example.geektrust.Entity.PassengerType;
import com.example.geektrust.Entity.Station;
import org.apache.commons.lang3.tuple.Pair;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import static com.example.geektrust.Constants.constants.*;
public class MetroCardService implements IMetroCardService {
    private final Map<String, MetroCard> metroCardMap = new HashMap<>();

    private static final Map<PassengerType, Double> TRIP_CHARGES = new HashMap<>();

    private static final double DISCOUNT_PERCENTAGE = discount_percentage;

    static {
        TRIP_CHARGES.put(PassengerType.ADULT, (double) ADULT_CHARGE);
        TRIP_CHARGES.put(PassengerType.KID, (double) KID_CHARGE);
        TRIP_CHARGES.put(PassengerType.SENIOR_CITIZEN, (double) SENIOR_CITIZEN_CHARGE);
    }

    public Map<String, MetroCard> getMetroCardMap() {
        return metroCardMap;
    }

    @Override
    public void createMetroCard(String metroCardId, Double balance) {
        MetroCard metroCard = new MetroCard(metroCardId, balance);
        metroCardMap.putIfAbsent(metroCardId, metroCard);
        metroCardMap.get(metroCardId);
    }

    @Override
    public void checkIn(String metroCardId, PassengerType passengerType, Station station) {
        if (metroCardMap.containsKey(metroCardId)) {
            processCheckIn(metroCardMap.get(metroCardId), passengerType, station);
        }
    }

    private void processCheckIn(MetroCard metroCard, PassengerType passengerType, Station station) {
        Pair<Double, Double> travelChargeAndDiscount = getEffectiveChargeForTravel(metroCard, passengerType, station);
        double rechargedAmount = metroCard.checkIfEligibleForTravel(travelChargeAndDiscount.getKey())
                ? 0
                : metroCard.rechargeMetroCard(travelChargeAndDiscount.getKey());

        station.checkIn(metroCard, passengerType, travelChargeAndDiscount.getKey());
        station.collectExpenseAtTheStation(rechargedAmount, travelChargeAndDiscount.getKey(), travelChargeAndDiscount.getValue());
    }

    private Pair<Double, Double> getEffectiveChargeForTravel(MetroCard metroCard, PassengerType passengerType, Station station) {
        double charges = TRIP_CHARGES.get(passengerType);
        double discountApplied = 0;

        if (metroCard.isEligibleForDiscount(station)) {
            discountApplied = (DISCOUNT_PERCENTAGE * charges);
            charges -= discountApplied;
        }
        return Pair.of(charges, discountApplied);
    }

    @Override
    public void printSummary() {
        Arrays.stream(Station.values()).forEach(Station::printStationSummary);
    }
}
