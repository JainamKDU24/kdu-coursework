package kdu.assignment;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    static Logging logger=new Logging();
    static ArrayList<Players> players=LoadfromCSV.loadplayers("src/main/resources/IPL_2021-data.csv");
    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        int choice;

        do {
            logger.logInfo("Menu:");
            logger.logInfo("1. Bowlers wicket>=40 :");
            logger.logInfo("2. Highest wicket-taker and highest run-scorer for a Team :");
            logger.logInfo("3. Show top/bottom N Run Scorers & Wicket Takers :");
            logger.logInfo("4. Generate Fixtures :");
            logger.logInfo("5. Exit");
            logger.logInfo("Enter your choice: ");
            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    Bowlerswithmin40();
                    break;
                case 2:
                    HighestWicketRuns(scanner);
                    break;
                case 3:
                    TopNScorersWicketTakers();
                    break;
                case 4:
                    Fixtures.GenerateFixtures();
                    break;
                case 5:
                    logger.logInfo("Exiting...");
                    break;
                default:
                    logger.logInfo("Invalid choice");
                    break;
            }
        } while (choice != 4);
    }
    private static void Bowlerswithmin40() {
        for (Players p : players) {
            if (p.getWickets()>=40) {
                logger.logInfo(p.getName() + "\n" + p.getTeam() + "\n" + p.getWickets());
            }
        }
    }

    private static void HighestWicketRuns(Scanner scanner) {
        logger.logInfo("Please enter name of Team:");
        String team = scanner.next();
        logger.logInfo("Top Run Scorer:");
        List<Players> player = Players.getTopScorerforTeam(players, 1,team);
        for (Players p : player) {
            logger.logInfo(p.getName() + "\n" + p.getRuns() + "\n" + p.getTeam());
        }

        logger.logInfo("Top Wicket Taker:");
        List<Players> player1 = Players.getTopWicketTakersforTeam(players, 1,team);
        for (Players p : player1) {
            logger.logInfo(p.getName() + "\n" + p.getWickets() + "\n" + p.getTeam());
        }
    }

    private static void TopNScorersWicketTakers() {
        logger.logInfo("Top 3 Run Scorers:");
        List<Players> player = Players.getTopNScorers(players, 3);
        for (Players p : player) {
            logger.logInfo(p.getName() + "\n" + p.getRuns() + "\n" + p.getTeam());
        }

        logger.logInfo("Top 3 Run Scorers:");
        List<Players> player1 = Players.getTopNWicketTakers(players, 3);
        for (Players p : player1) {
            logger.logInfo(p.getName() + "\n" + p.getWickets() + "\n" + p.getTeam());
        }
    }
}
