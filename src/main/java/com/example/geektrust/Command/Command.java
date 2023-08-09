package com.example.geektrust.Command;

import com.example.geektrust.Entity.PassengerType;
import com.example.geektrust.Entity.Station;
import com.example.geektrust.Services.IMetroCardService;
import com.example.geektrust.Services.MetroCardService;

public enum Command {
    BALANCE((svc, tokens) -> svc.createMetroCard(tokens[1], Double.valueOf(tokens[2]))),
    CHECK_IN((svc, inputTokens) -> svc.checkIn(inputTokens[1], PassengerType.valueOf(inputTokens[2]), Station.valueOf(inputTokens[3]))),
    PRINT_SUMMARY((svc, inputTokens) -> svc.printSummary());

    private static final IMetroCardService metroService = new MetroCardService();

    private final CommandProcessor commandProcessor;

    Command(CommandProcessor processor) {
        this.commandProcessor = processor;
    }

    public void process(String[] inputTokens) {
        this.commandProcessor.process(metroService, inputTokens);
    }
}
