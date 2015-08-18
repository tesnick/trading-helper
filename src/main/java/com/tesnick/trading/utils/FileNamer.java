package com.tesnick.trading.utils;

import java.io.File;
import java.io.FileFilter;

public class FileNamer {

    public static File fromTickerAndDate(String name, String startDate,
                                         String endDate) {

        File workingDirectory = new File(".");
        if (workingDirectory.getAbsolutePath().contains("analysis")) {
            return new File("./data/stock_" + name + "_" + startDate + "_"
                    + endDate + ".json");
        } else {
            return new File("./data-analysis/data/stock_" + name + "_" + startDate + "_"
                    + endDate + ".json");
        }
    }

    public static File fromTickerAndDateToYahooData(String name, String startDate,
                                                    String endDate) {
        return new File("./data-analysis/yahooData/stock_" + name + "_" + startDate + "_"
                + endDate + ".json");
    }

    public static File getFileFromYahooDataFolder(final String ticker) {

        return new File("./yahooData").listFiles(new FileFilter() {
            @Override
            public boolean accept(File pathname) {
                return pathname.getName().contains(ticker);
            }
        })[0];
    }
}