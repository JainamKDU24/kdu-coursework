import kdu.assignment.*;
import com.fasterxml.jackson.databind.JsonNode;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.Scanner;


public class Main {
    static Logging logger=new Logging();

    static ArrayList<Coins> coins = (ArrayList<Coins>) LoadCoinsTraders.loadCoinsFromCSV("src/main/resources/coins.csv");
    static ArrayList<Traders> traders = (ArrayList<Traders>) LoadCoinsTraders.loadTradersFromCSV("src/main/resources/traders.csv");
    static List<JsonNode> transactions = Transactions.loadTransactionsFromJSON("src/main/resources/large_transaction.json");

    public static void main(String[] args) throws Exception {
        logger.logInfo("Number of coins: " + coins.size());
        logger.logInfo("Number of traders: " + traders.size());
        // Creating a latch to synchronize thread execution
        CountDownLatch latch = new CountDownLatch(transactions.size());
        // Creating a thread to execute transactions concurrently
        Thread transactionsThread = new Thread(() -> executeTransactions(transactions, latch));
        transactionsThread.start();
        startMenu();
        // Waiting for transaction threads to finish
        transactionsThread.join();
    }

    public static void executeTransactions(List<JsonNode> jsonTransactions, CountDownLatch latch) {

        for (JsonNode transaction : jsonTransactions) {
            ExecuteTransactions runnable = new ExecuteTransactions(transaction,latch);

            Thread thread = new Thread(runnable);
            thread.start();
        }

        // Waiting for all threads to finish
        try {
            latch.await();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            logger.logInfo(String.valueOf(e));
        }

    }

    private static void startMenu() {
        Scanner scanner = new Scanner(System.in);
        int choice;

        do {
            logger.logInfo("Menu:");
            logger.logInfo("1. Get coin details");
            logger.logInfo("2. Display top N coins by price");
            logger.logInfo("3. Show trader's portfolio");
            logger.logInfo("4. Show trader's total profit/loss");
            logger.logInfo("5. Show top/bottom N traders by profit/loss");
            logger.logInfo("6. Exit");
            logger.logInfo("Enter your choice: ");
            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    handleGetCoinDetails(scanner);
                    break;
                case 2:
                    handleDisplayTopNCoins();
                    break;
                case 3:
                    handleShowTraderPortfolio(scanner);
                    break;
                case 4:
                    handleShowTraderProfitLoss(scanner);
                    break;
                case 5:
                    handleShowTopBottomTraders(scanner);
                    break;
                case 6:
                    logger.logInfo("Exiting...");
                    break;
                default:
                    logger.logInfo("Invalid choice");
            }
        } while (choice != 6);
    }

    private static void handleGetCoinDetails(Scanner scanner) {
        String symbol = scanner.next();
        for (Coins c : coins) {
            if (c.getSymbol().equalsIgnoreCase(symbol)) {
                logger.logInfo(c.getName() + "\n" + c.getSymbol() + "\n" + c.getPrice() + "\n" + c.getCirculatingSupply());
            }
        }
    }

    private static void handleDisplayTopNCoins() {
        List<Coins> coin = Coins.getTopNCoinsByPrice(coins, 3);
        for (Coins c : coin) {
            logger.logInfo(c.getName() + "\n" + c.getSymbol() + "\n" + c.getPrice() + "\n" + c.getCirculatingSupply());
        }
    }

    private static void handleShowTraderPortfolio(Scanner scanner) {
        logger.logInfo("Please enter wallet_address of trader:");
        String walletAddress = scanner.next(); //Test Wallet_address:"0x9c79042a5f769f6c2395b0d51be7677e"
        traders.stream()
                .filter(trader -> trader.getWalletAddress().equalsIgnoreCase(walletAddress))
                .findFirst()
                .ifPresent(trader -> logger.logInfo("Portfolio of "+trader.getFirstName()+" : "+trader.getPortfolio().toString()));
    }

    private static void handleShowTraderProfitLoss(Scanner scanner) {
        logger.logInfo("Please enter wallet_address of trader:");
        String walletAddress = scanner.next(); //Test Wallet_address:"0x9c79042a5f769f6c2395b0d51be7677e"
        traders.stream()
                .filter(trader -> trader.getWalletAddress().equalsIgnoreCase(walletAddress))
                .findFirst()
                .ifPresent(trader -> logger.logInfo(String.valueOf("Profit/Loss of "+trader.getFirstName()+" : "+trader.getTotalProfitLoss(coins))));
    }

    private static void handleShowTopBottomTraders(Scanner scanner) {
        logger.logInfo("Please enter N:");
        Integer N = scanner.nextInt();
        Traders.showTop5andBottom5Traders(coins,traders,N);
    }
}

