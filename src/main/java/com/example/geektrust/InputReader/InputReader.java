package com.example.geektrust.InputReader;

import com.example.geektrust.Command.Command;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import static com.example.geektrust.Constants.constants.BLANK_SPACE;

public class InputReader {
    public void processInput(String inputFile) {
        try {
            final BufferedReader bufferedReader = new BufferedReader(new FileReader(inputFile));
            String lineOfInput = bufferedReader.readLine();
            while (lineOfInput != null) {
                final String[] inputTokens = lineOfInput.split(BLANK_SPACE);
                final Command command = Command.valueOf(inputTokens[0]);
                command.process(inputTokens);
                lineOfInput = bufferedReader.readLine();
            }
        } catch (IOException e) {
            System.out.println("exception");
        }
    }
}
