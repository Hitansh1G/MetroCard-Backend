package com.example.geektrust.Command;

import com.example.geektrust.Services.IMetroCardService;



@FunctionalInterface
public interface CommandProcessor {
    void process(IMetroCardService metroCardService, String[] tokens);
}
