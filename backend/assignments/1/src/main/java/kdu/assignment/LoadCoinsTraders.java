package kdu.assignment;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LoadCoinsTraders {
    static Logging logger=new Logging();
    private static Map<String, Double> priceList = new HashMap<>();

    public static Map<String, Double> getPriceList() {
        return priceList;
    }

    private LoadCoinsTraders() {
    }

    public static void setPriceList(Map<String, Double> priceList, String symbol, Double price) {
        LoadCoinsTraders.priceList = priceList;
        priceList.put(symbol,price);
    }

    public static List<Coins> loadCoinsFromCSV(String fileName) {
        List<Coins> coins = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            br.readLine(); // Skip header line

            String line;
            while ((line = br.readLine()) != null) {
                String[] fields = line.split(",");
                String name = fields[2].trim();
                String symbol = fields[3].trim();
                double price = Double.parseDouble(fields[4].trim());
                long circulatingSupply = Long.parseLong(fields[5].trim());

                setPriceList(priceList,symbol,price);

                Coins coin = new Coins(name, symbol, price, circulatingSupply);
                coins.add(coin);
            }
        } catch (IOException e) {
            logger.logInfo(String.valueOf(e));
        }

        return coins;
    }

    public static List<Traders> loadTradersFromCSV(String fileName) {
        List<Traders> traders = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            br.readLine(); // Skip header line

            String line;
            while ((line = br.readLine()) != null) {
                String[] fields = line.split(",");
                String firstName = fields[1].trim();
                String lastName = fields[2].trim();
                String phone = fields[3].trim();
                String walletAddress = fields[4].trim();

                Traders trader = new Traders(firstName, lastName, phone, walletAddress);
                traders.add(trader);
            }
        } catch (IOException e) {
            logger.logInfo(String.valueOf(e));
        }

        return traders;
    }
}


