package kdu.backend4;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        Logging logger = new Logging();
        TicketReservation mmt = new TicketReservation();

        boolean ticket1 = mmt.bookFlight("a","b",21,"male","business","A1");
        logger.logInfo("Ticket booked: "+ticket1);
        boolean ticket2 = mmt.bookFlight("b","b",20,"female","economy","A2");
        logger.logInfo("Ticket booked: "+ticket2);
        boolean ticket3 = mmt.bookFlight("c","b",28,"male","first class","A3");
        logger.logInfo("Ticket booked: "+ticket3);
        boolean ticket4 = mmt.bookFlight("d","b",25,"female","economy","A4");
        logger.logInfo("Ticket booked: "+ticket4);
        boolean ticket5 = mmt.bookFlight("e","b",23,"male","first class","A5");
        logger.logInfo("Ticket booked: "+ticket5);
        boolean ticket6 = mmt.bookFlight("f","b",24,"female","business","A6");
        logger.logInfo("Ticket booked: "+ticket6);
        boolean ticket7 = mmt.bookFlight("g","b",25,"male","first class","A7");
        logger.logInfo("Ticket booked: "+ticket7);
        boolean ticket8 = mmt.bookFlight("h","b",27,"male","business","A8");
        logger.logInfo("Ticket booked: "+ticket8);
        boolean ticket9 = mmt.bookFlight("i","b",21,"male","economy","A9");
        logger.logInfo("Ticket booked: "+ticket9);
        boolean ticket10 = mmt.bookFlight("j","b",31,"male","first class","A10");
        logger.logInfo("Ticket booked: "+ticket10);
        boolean ticket11 = mmt.bookFlight("k","b",41,"male","economy","A11");
        logger.logInfo("Ticket booked: "+ticket11);
        boolean ticket12 = mmt.bookFlight("l","b",31,"male","business","A12");
        logger.logInfo("Ticket booked: "+ticket12);
        boolean ticket13 = mmt.bookFlight("m","b",51,"male","first class","A13");
        logger.logInfo("Ticket booked: "+ticket13);
        boolean ticket14 = mmt.bookFlight("n","b",21,"male","economy","A14");
        logger.logInfo("Ticket booked: "+ticket14);

        mmt.cancel("A7");

        ticket14 = mmt.bookFlight("n","b",21,"male","business","A14");
        logger.logInfo("Ticket booked: "+ticket14);
        List<Passenger> cnf = mmt.getConfirmedList();
        for(Passenger p: cnf){
            logger.logInfo(p.getConfirmationNumber());
        }
    }
}
