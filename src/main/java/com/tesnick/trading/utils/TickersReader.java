package com.tesnick.trading.utils;

import com.tesnick.trading.dto.Ticker;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class TickersReader {

    public List<Ticker> getTickers() throws IOException {

        List<Ticker> results = new ArrayList<Ticker>();

        File[] files = new File("./src/main/resources").listFiles(new FilenameFilter() {
            @Override
            public boolean accept(File dir, String name) {
                return name.endsWith("tickers.txt");
            }
        });

        for (File file : files) {
            results.addAll(getTickers(file));
        }

        return results;
    }

    public List<Ticker> getTickers(File file) throws IOException {

        List<Ticker> results = new ArrayList<Ticker>();

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
