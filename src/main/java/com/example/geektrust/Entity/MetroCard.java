package com.example.geektrust.Entity;

import com.example.geektrust.Enums.Station;

import java.util.Optional;

public class MetroCard {
    private final String metroCardNumber;
    private Optional<Station> lastStation = Optional.empty();
    private double balance;

    public MetroCard(String metroCardNumber, Optional<Station> lastStation, double balance) {
        this.metroCardNumber = metroCardNumber;
        this.lastStation = lastStation;
        this.balance = balance;
    }

    public MetroCard(String metroCardNumber, double balance) {
        this.metroCardNumber = metroCardNumber;
        this.balance = balance;
    }

    public String getMetroCardNumber() {
        return metroCardNumber;
    }

    public Optional<Station> getLastStation() {
        return lastStation;
    }

    public void setLastStation(Optional<Station> lastStation) {
        this.lastStation = lastStation;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public double rechargeMetroCard(double moneyRequiredForTravel) {
        double moneyToBeRecharged = (moneyRequiredForTravel - this.balance);
        recharge(moneyToBeRecharged);
        return moneyToBeRecharged;
    }

    public boolean checkIfEligibleForTravel(Double money) {
        return (this.balance >= money);
    }

    private void recharge(Double money) {
        this.balance += money;
    }

    private void addJourneyHistory(Station station) {
        if (!this.lastStation.isPresent()) {
            this.lastStation = Optional.ofNullable(station);
            return;
        }
        Station lastStation = this.lastStation.get();
        if (!lastStation.equals(station)) {
            this.lastStation = Optional.empty();
        }
    }

    public boolean isEligibleForDiscount(Station fromStation) {
        return this.lastStation.filter(station -> (!station.equals(fromStation))).orElse(null) != null;
    }

    public void checkIn(double amount, Station station) {
        addJourneyHistory(station);
        balance -= amount;
    }
}
