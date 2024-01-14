import kdu.assignment.*;
import com.fasterxml.jackson.databind.JsonNode;
import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.CountDownLatch;

public class ExecuteTransactions implements Runnable {
    Logging logger=new Logging();

    private final JsonNode transaction;
    private final CountDownLatch latch;
    private static ArrayList<Coins> coins=Main.coins;
    private static ArrayList<Traders> traders=Main.traders;

    public ExecuteTransactions(JsonNode transaction,CountDownLatch latch) {
        this.transaction = transaction;

        this.latch = latch;
    }

    @Override
    public void run() {
        try {
            String type = transaction.get("type").asText();
            JsonNode data = transaction.get("data");
            String transactionHash = getBlockHash();

            switch (type) {
                case "BUY":
                    executeBuyTransaction(data,transactionHash);
                    break;
                case "SELL":
                    executeSellTransaction(data,transactionHash);
                    break;
                case "UPDATE_PRICE":
                    executeUpdatePriceTransaction(data);
                    break;
                case "ADD_VOLUME":
                    executeAddVolumeTransaction(data);
                    break;
                default:
                    break;
            }

        } finally {
            latch.countDown();
        }
    }

    private void executeBuyTransaction(JsonNode data, String transactionHash) {
        String coinSymbol = data.get("coin").asText();
        Coins coin = Coins.getCoinByNameOrCode(coins, coinSymbol);
        if (coin == null) {
            logger.logWarn("Error: Invalid coin symbol - " + coinSymbol);
            throw new IllegalArgumentException("Invalid coin symbol");
        }
        int quantity = data.get("quantity").intValue();
        String walletAddress = data.get("wallet_address").asText();
        Traders trader = getTraderByWalletAddress(walletAddress);

        if (trader == null) {
            throw new IllegalArgumentException("Invalid trader wallet address");
        }

        long circulatingSupply = coin.getCirculatingSupply();

        // Checking if there are enough coins available for trade
        synchronized (coin) {
            while (quantity > circulatingSupply) {
                try {
                    // Waiting for another trader to SELL or for volume to increase
                    coin.wait();
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
                circulatingSupply = coin.getCirculatingSupply();
            }

            // Performing the buy transaction
            coin.setCirculatingSupply(circulatingSupply - quantity);
            trader.executeBuyTransaction(trader,coinSymbol, quantity,transactionHash);

            // Notifying other threads that the volume has changed
            coin.notifyAll();
        }
    }

    private void executeSellTransaction(JsonNode data, String transactionHash) {
        String coinSymbol = data.get("coin").asText();
        Coins coin = Coins.getCoinByNameOrCode(coins, coinSymbol);
        if (coin == null) {
            throw new IllegalArgumentException("Invalid coin symbol");
        }
        int quantity = data.get("quantity").intValue();
        String walletAddress = data.get("wallet_address").asText();
        Traders trader = getTraderByWalletAddress(walletAddress);

        if (trader == null) {
            throw new IllegalArgumentException("Invalid trader wallet address");
        }

        // Checking if it is valid
        int traderQuantity;
        synchronized (trader) {
            traderQuantity = trader.getPortfolio().getOrDefault(coinSymbol, 0);
        }

        synchronized (coin) {
            while (quantity > traderQuantity) {
                try {
                    // Waiting for the trader to have enough coins to sell
                    trader.wait();
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
                traderQuantity = trader.getPortfolio().getOrDefault(coinSymbol, 0);
            }

            // Performing the sell transaction
            trader.executeSellTransaction(trader,coinSymbol, quantity,transactionHash);
            coin.setCirculatingSupply(coin.getCirculatingSupply() + quantity);

            // Notifying other threads that the trader's portfolio has changed
            trader.notifyAll();
        }
    }

    private void executeUpdatePriceTransaction(JsonNode data) {
        String coinSymbol = data.get("coin").asText();
        Coins coin = Coins.getCoinByNameOrCode(coins, coinSymbol);
        if (coin == null) {
            throw new IllegalArgumentException("Invalid coin symbol");
        }
        double newPrice = data.get("price").doubleValue();

        // Update the coin price
        synchronized (coin) {
            coin.setPrice(newPrice);
        }
    }

    private void executeAddVolumeTransaction(JsonNode data) {
        String coinSymbol = data.get("coin").asText();
        Coins coin = Coins.getCoinByNameOrCode(coins, coinSymbol);
        if (coin == null) {
            throw new IllegalArgumentException("Invalid coin symbol");
        }
        long newVolume = data.get("volume").longValue();

        // Updating the coin circulating supply
        synchronized (coin) {
            coin.setCirculatingSupply(coin.getCirculatingSupply() + newVolume);
            coin.notifyAll();
        }
    }

    private Traders getTraderByWalletAddress(String walletAddress) {
        return traders.stream()
                .filter(trader -> trader.getWalletAddress().equals(walletAddress))
                .findFirst()
                .orElse(null);
    }

    private String getBlockHash() {
        String SALTCHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
        StringBuilder transactionHash = new StringBuilder();
        Random rnd = new Random();
        /**
         * Introducing delay mimicking complex
         * calculation being performed.
         */
        for (double i = 0; i < 199999999; i++) {
            i = i;
        }
        while (transactionHash.length() < 128) {
            int index = (int) (rnd.nextFloat() * SALTCHARS.length());
            transactionHash.append(SALTCHARS.charAt(index));
        }
        String hashCode = transactionHash.toString();
        return "0x" + hashCode.toLowerCase();
    }

}
