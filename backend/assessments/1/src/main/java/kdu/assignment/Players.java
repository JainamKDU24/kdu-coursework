package kdu.assignment;

import java.util.List;
public class Players {
    private String Name;
    private String Team;
    private String Role;
    private int Matches;
    private int Runs;
    private double Average;
    private double SR;
    private int Wickets;

    public Players(String name, String team, String role, int matches, int runs, double average, double SR, int wickets) {
        this.Name = name;
        this.Team = team;
        this.Role = role;
        this.Matches = matches;
        this.Runs = runs;
        this.Average = average;
        this.SR = SR;
        this.Wickets = wickets;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getTeam() {
        return Team;
    }

    public void setTeam(String team) {
        Team = team;
    }

    public String getRole() {
        return Role;
    }

    public void setRole(String role) {
        Role = role;
    }

    public int getMatches() {
        return Matches;
    }

    public void setMatches(int matches) {
        Matches = matches;
    }

    public int getRuns() {
        return Runs;
    }

    public void setRuns(int runs) {
        Runs = runs;
    }

    public double getAverage() {
        return Average;
    }

    public void setAverage(double average) {
        Average = average;
    }

    public double getSR() {
        return SR;
    }

    public void setSR(double SR) {
        this.SR = SR;
    }

    public int getWickets() {
        return Wickets;
    }

    public void setWickets(int wickets) {
        Wickets = wickets;
    }

    public static List<Players> getTopNScorers(List<Players> players, int n) {
        return players.stream()
                .sorted((p1, p2) -> Double.compare(p2.getRuns(), p1.getRuns()))
                .limit(n)
                .toList();
    }
    public static List<Players> getTopNWicketTakers(List<Players> players, int n) {
        return players.stream()
                .sorted((p1, p2) -> Double.compare(p2.getWickets(), p1.getWickets()))
                .limit(n)
                .toList();
    }

    public static List<Players> getTopScorerforTeam(List<Players> players, int n,String Team) {
        return players.stream()
                .filter(players1 -> players1.getTeam().equalsIgnoreCase(Team))
                .sorted((p1, p2) -> Double.compare(p2.getRuns(), p1.getRuns()))
                .limit(n)
                .toList();
    }

    public static List<Players> getTopWicketTakersforTeam(List<Players> players, int n,String Team) {
        return players.stream()
                .filter(players1 -> players1.getTeam().equalsIgnoreCase(Team))
                .sorted((p1, p2) -> Double.compare(p2.getWickets(), p1.getWickets()))
                .limit(n)
                .toList();
    }
}
