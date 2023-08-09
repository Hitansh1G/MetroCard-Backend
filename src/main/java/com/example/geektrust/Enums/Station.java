package com.example.geektrust.Enums;

import com.example.geektrust.Entity.MetroCard;
import com.example.geektrust.Enums.PassengerType;

import java.util.*;
import static com.example.geektrust.Constants.constants.*;

public enum Station {
    CENTRAL, AIRPORT;

    private double totalMoneyCollected = ZERO;
    private double totalDiscountGiven = ZERO;
    private double taxCollected = ZERO;

    private final Map<PassengerType, Integer> passengerTypeIntegerMap = new HashMap<>();


    private static final double TAX_PERCENTAGE = TAX_PERCENT;

    public double getTotalMoneyCollected() {
        return totalMoneyCollected;
    }

    public double getTotalDiscountGiven() {
        return totalDiscountGiven;
    }

    public double getTaxCollected() {
        return taxCollected;
    }

    public void collectExpenseAtTheStation(double rechargedMoney, double travelExpenses, double discount) {
        this.totalDiscountGiven += discount;
        this.totalMoneyCollected += travelExpenses;
        if (rechargedMoney > ZERO) {
            this.taxCollected += (TAX_PERCENTAGE * rechargedMoney);
        }
    }

    public void checkIn(MetroCard metroCard, PassengerType passengerType, double amount) {
        metroCard.checkIn(amount, this);
        passengerTypeIntegerMap.putIfAbsent(passengerType,
                passengerTypeIntegerMap.getOrDefault(passengerType, 0));
        passengerTypeIntegerMap.computeIfPresent(passengerType, (passengerType1, integer) -> integer + 1);
    }

    public void printStationSummary() {
        double totalMoneyCollected = (this.getTotalMoneyCollected() + this.getTaxCollected());
        double discount = this.getTotalDiscountGiven();
        System.out.println(TOTAL_COLLECTION + this + BLANK_SPACE + (int) totalMoneyCollected + BLANK_SPACE + (int) discount);
        System.out.println(SUMMARY);
        HashMap<PassengerType, Integer> sortedMap = sortMap();
        for (PassengerType key : sortedMap.keySet()) {
            System.out.println(key.toString() + BLANK_SPACE + sortedMap.get(key));
        }
    }

    private HashMap<PassengerType, Integer> sortMap() {
        List<Map.Entry<PassengerType, Integer>> list =
                new LinkedList<>(passengerTypeIntegerMap.entrySet());

        list.sort((o1, o2) -> {
            int result = o2.getValue() - o1.getValue();
            if (result == 0) {
                return o1.getKey().compareTo(o2.getKey());
            }
            return result;
        });
        HashMap<PassengerType, Integer> temp = new LinkedHashMap<>();
        for (Map.Entry<PassengerType, Integer> aa : list) {
            temp.put(aa.getKey(), aa.getValue());
        }

        return temp;
    }

}
