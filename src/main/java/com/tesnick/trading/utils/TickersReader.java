package com.tesnick.trading.utils;

import com.tesnick.trading.dto.Ticker;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TickersReader {

    public List<Ticker> getTickers() throws IOException {

        List<Ticker> results = new ArrayList<>();

        File[] files = new File("./src/main/resources").listFiles((dir, name) -> {
            return name.endsWith("tickers.txt");
        });

        for (File file : files) {
            results.addAll(getTickers(file));
        }

        return results;
    }

    public List<Ticker> getTickers(File file) throws IOException {

        List<Ticker> results = new ArrayList<>();

        try (FileReader fr = new FileReader(file); BufferedReader br = new BufferedReader(fr)) {

            String line;
            while ((line = br.readLine()) != null) {
                if (!line.startsWith("---")) {
                    results.add(new Ticker(line));
                }

            }
        }

        return results;
    }
}
