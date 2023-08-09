package com.example.geektrust.Services;

import com.example.geektrust.Entity.MetroCard;
import com.example.geektrust.Enums.PassengerType;
import com.example.geektrust.Enums.Station;
import org.apache.commons.lang3.tuple.Pair;
import org.jetbrains.annotations.NotNull;

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
    }

    @Override
    public void checkIn(String metroCardId, PassengerType passengerType, Station station) {
        if (metroCardMap.containsKey(metroCardId)) {
            checkInProcess(metroCardMap.get(metroCardId), passengerType, station);
        }
    }

    private void checkInProcess(MetroCard metroCard, PassengerType passengerType, Station station) {
        Pair<Double, Double> travelChargeAndDiscount = calculateChargesAndDiscount(metroCard, passengerType, station);
        double rechargedAmount = metroCard.checkIfEligibleForTravel(travelChargeAndDiscount.getKey())
                ? 0
                : metroCard.rechargeMetroCard(travelChargeAndDiscount.getKey());

        station.checkIn(metroCard, passengerType, travelChargeAndDiscount.getKey());
        station.collectExpenseAtTheStation(rechargedAmount, travelChargeAndDiscount.getKey(), travelChargeAndDiscount.getValue());
    }

    private @NotNull Pair<Double, Double> calculateChargesAndDiscount(MetroCard metroCard, PassengerType passengerType, Station station) {
        double charges = TRIP_CHARGES.get(passengerType);
        double discountApplied = 0;

        if (shouldApplyDiscount(metroCard, station)) {
            discountApplied = (DISCOUNT_PERCENTAGE * charges);
            charges -= discountApplied;
        }
        return Pair.of(charges, discountApplied);
    }

    private boolean shouldApplyDiscount(@org.jetbrains.annotations.NotNull MetroCard metroCard, Station station) {
        return metroCard.getLastStation()
                .map(lastStation -> !lastStation.equals(station))
                .orElse(false);
    }

    @Override
    public void printSummary() {
        for (Station station : Station.values()) {
            station.printStationSummary();
        }
    }
}
