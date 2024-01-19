package kdu.assignment;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class LoadfromCSV {
    private LoadfromCSV() {
    }

    static Logging logger=new Logging();
    public static ArrayList<Players> loadplayers(String fileName) {
        ArrayList<Players> players = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            line= br.readLine();

            while ((line = br.readLine()) != null) {
                String[] fields = line.split(",");
                String Name=fields[0].trim();
                String Team=fields[1].trim();
                String Role=fields[2].trim();
                int Matches=Integer.parseInt(fields[3].trim());
                int Runs=Integer.parseInt(fields[4].trim());
                double Average=Double.parseDouble(fields[5].trim());
                double SR=Double.parseDouble(fields[6].trim());
                int Wickets=Integer.parseInt(fields[7].trim());

                Players player = new Players(Name,Team,Role,Matches,Runs,Average,SR,Wickets);
                players.add(player);
            }
        } catch (IOException e) {
            logger.logInfo(String.valueOf(e));
        }

        return players;
    }
}
