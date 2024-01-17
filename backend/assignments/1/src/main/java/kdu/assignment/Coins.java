package kdu.assignment;
import java.util.*;


public class Coins {
    private int rank;
    private  String name;
    private  String symbol;
    private double price;
    private long circulatingSupply;

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    public Coins(int rank, String name, String symbol, double price, long circulatingSupply) {
        this.name = name;
        this.symbol = symbol;
        this.price = price;
        this.circulatingSupply = circulatingSupply;
        this.rank=rank;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public long getCirculatingSupply() {
        return circulatingSupply;
    }

    public void setCirculatingSupply(long circulatingSupply) {
        this.circulatingSupply = circulatingSupply;
    }

    // Helper functions

    public static Coins getCoinByNameOrCode(List<Coins> coins, String nameOrCode) {
        return coins.stream()
                .filter(coin -> nameOrCode.equalsIgnoreCase(coin.getName()) || nameOrCode.equalsIgnoreCase(coin.getSymbol()))
                .findFirst()
                .orElse(null);
    }

    public static List<Coins> getTopNCoinsByPrice(List<Coins> coins, int n) {
        return coins.stream()
                .sorted((c1, c2) -> Double.compare(c2.getPrice(), c1.getPrice()))
                .limit(n)
                .toList();
    }
}
