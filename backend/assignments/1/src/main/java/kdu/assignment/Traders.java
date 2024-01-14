package kdu.assignment;

import java.io.FileNotFoundException;

import java.io.IOException;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.*;

import static kdu.assignment.Coins.getCoinByNameOrCode;


public class Traders {
    String fileName = "transactions1.txt";
    String fileName2 = "transactions2.txt";

    static Logging logger=new Logging();

    static Scanner scanner=new Scanner(System.in);
    private final String firstName;
    private final String lastName;
    private final String phone;
    private final String walletAddress;
    private  Map<String, Integer> portfolio;


    public Traders(String firstName, String lastName, String phone, String walletAddress) throws FileNotFoundException {
        this.firstName = firstName;
        this.lastName = lastName;
        this.phone = phone;
        this.walletAddress = walletAddress;
        this.portfolio = new HashMap<>();
    }
    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getPhone() {
        return phone;
    }

    public String getWalletAddress() {
        return walletAddress;
    }

    public Map<String, Integer> getPortfolio() {
        return portfolio;
    }
    Path path = Path.of(fileName);
    public void executeBuyTransaction(Traders trader,String coinSymbol, int quantity,String transactionHash) {
        // Update the trader's portfolio with the purchased coins
        int currentQuantity = portfolio.getOrDefault(coinSymbol, 0);
        portfolio.put(coinSymbol, currentQuantity + quantity);
        String content = trader.firstName+" "+"COIN BOUGHT: " + coinSymbol + " Transaction ID: "+ transactionHash;
        byte[] contentBytes = content.getBytes();
        try {
            Files.write(path, contentBytes, StandardOpenOption.CREATE, StandardOpenOption.WRITE);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    Path path1 = Path.of(fileName2);
    public void executeSellTransaction(Traders trader,String coinSymbol, int quantity,String transactionHash) {
        // Update the trader's portfolio after selling the coins
        int currentQuantity = portfolio.get(coinSymbol);
        if (currentQuantity < quantity) {
            throw new IllegalArgumentException("Trader doesn't own enough coins to sell");
        }
        portfolio.put(coinSymbol, currentQuantity - quantity);
        String content = trader.firstName+" "+"COIN SOLD: " + coinSymbol + " Transaction ID: "+ transactionHash;
        byte[] contentBytes = content.getBytes();
        try {
            Files.write(path1, contentBytes, StandardOpenOption.CREATE, StandardOpenOption.WRITE);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public double getTotalProfitLoss(ArrayList<Coins> coins) {

        return portfolio.entrySet().stream()
                .mapToDouble(entry -> {
                    String coinSymbol = entry.getKey();
                    int quantity = entry.getValue();

                    Coins coin = getCoinByNameOrCode(coins, coinSymbol);
                    if (coin != null) {
                        double initialPrice= LoadCoinsTraders.getPriceList().getOrDefault(coinSymbol, 0.0);
                        // Sufficient coins bought to cover the sell
                        return ((currentPrice-initialPrice)  * quantity);
                    }
                    return 0;
                })
                .sum();
    }

    public static void showTop5andBottom5Traders(ArrayList<Coins> coins,ArrayList<Traders> traders,Integer N) {
        List<Traders> topTraders = traders.stream().sorted((trader, t1) -> (int) (t1.getTotalProfitLoss(coins) - trader.getTotalProfitLoss(coins))).limit(N).toList();
        logger.logInfo("Top "+ N +" traders:- ");
        for(Traders t: topTraders){
            logger.logInfo(t.getFirstName());
        }
        List<Traders> bottomTraders = traders.stream().sorted((trader, t1) -> (int) (trader.getTotalProfitLoss(coins) - t1.getTotalProfitLoss(coins))).limit(N).toList();
        logger.logInfo("Bottom "+ N +" traders:- ");
        for(Traders t: bottomTraders){
            logger.logInfo(t.getFirstName());
        }
    }
}
