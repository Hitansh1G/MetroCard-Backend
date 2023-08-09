package com.example.geektrust.CommandTest;
//package com.example.geektrust.Command;

import com.example.geektrust.Command.CommandProcessor;
import com.example.geektrust.Services.IMetroCardService;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;

class CommandProcessorTest {

    @Test
    void process_ValidCommand_CallsService() {
        IMetroCardService mockMetroCardService = mock(IMetroCardService.class);
        CommandProcessor commandProcessor = (metroCardService, tokens) -> {
            // Simulate the processing of a command
            metroCardService.createMetroCard(tokens[1], Double.valueOf(tokens[2]));
        };

        String[] tokens = {"BALANCE", "12345", "100.0"};
        commandProcessor.process(mockMetroCardService, tokens);

        // Verify that the service method was called with the correct arguments
        verify(mockMetroCardService).createMetroCard("12345", 100.0);
    }

}
