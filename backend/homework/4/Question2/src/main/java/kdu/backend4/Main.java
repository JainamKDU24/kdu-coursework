package kdu.backend4;
import java.util.*;

public class Main {
    public static final String BUSINESS_CLASS = "business";
    public static final String FEMALE_GENDER = "female";
    public static final String MALE_GENDER = "male";

    public static final String TICKET_BOOKED_MESSAGE = "Ticket booked: ";
    public static final String ECONOMY_CLASS = "economy";
    public static final String FIRST_CLASS = "first class";

    public static void main(String[] args) {
        Logging logger = new Logging();
        TicketReservation mmt = new TicketReservation();

        boolean ticket1 = mmt.bookFlight("a","b",21,MALE_GENDER,BUSINESS_CLASS,"A1");
        logger.logInfo(TICKET_BOOKED_MESSAGE+ticket1);
        boolean ticket2 = mmt.bookFlight("b","b",20,FEMALE_GENDER,ECONOMY_CLASS,"A2");
        logger.logInfo(TICKET_BOOKED_MESSAGE+ticket2);
        boolean ticket3 = mmt.bookFlight("c","b",28,MALE_GENDER,FIRST_CLASS,"A3");
        logger.logInfo(TICKET_BOOKED_MESSAGE+ticket3);
        boolean ticket4 = mmt.bookFlight("d","b",25,FEMALE_GENDER,ECONOMY_CLASS,"A4");
        logger.logInfo(TICKET_BOOKED_MESSAGE+ticket4);
        boolean ticket5 = mmt.bookFlight("e","b",23,MALE_GENDER,FIRST_CLASS,"A5");
        logger.logInfo(TICKET_BOOKED_MESSAGE+ticket5);
        boolean ticket6 = mmt.bookFlight("f","b",24,FEMALE_GENDER,BUSINESS_CLASS,"A6");
        logger.logInfo(TICKET_BOOKED_MESSAGE+ticket6);
        boolean ticket7 = mmt.bookFlight("g","b",25,MALE_GENDER,FIRST_CLASS,"A7");
        logger.logInfo(TICKET_BOOKED_MESSAGE+ticket7);
        boolean ticket8 = mmt.bookFlight("h","b",27,MALE_GENDER,BUSINESS_CLASS,"A8");
        logger.logInfo(TICKET_BOOKED_MESSAGE+ticket8);
        boolean ticket9 = mmt.bookFlight("i","b",21,MALE_GENDER,ECONOMY_CLASS,"A9");
        logger.logInfo(TICKET_BOOKED_MESSAGE+ticket9);
        boolean ticket10 = mmt.bookFlight("j","b",31,MALE_GENDER,FIRST_CLASS,"A10");
        logger.logInfo(TICKET_BOOKED_MESSAGE+ticket10);
        boolean ticket11 = mmt.bookFlight("k","b",41,MALE_GENDER,ECONOMY_CLASS,"A11");
        logger.logInfo(TICKET_BOOKED_MESSAGE+ticket11);
        boolean ticket12 = mmt.bookFlight("l","b",31,MALE_GENDER,BUSINESS_CLASS,"A12");
        logger.logInfo(TICKET_BOOKED_MESSAGE+ticket12);
        boolean ticket13 = mmt.bookFlight("m","b",51,MALE_GENDER,FIRST_CLASS,"A13");
        logger.logInfo(TICKET_BOOKED_MESSAGE+ticket13);
        boolean ticket14 = mmt.bookFlight("n","b",21,MALE_GENDER,ECONOMY_CLASS,"A14");
        logger.logInfo(TICKET_BOOKED_MESSAGE+ticket14);

        mmt.cancel("A7");

        ticket14 = mmt.bookFlight("n","b",21,"male",BUSINESS_CLASS,"A14");
        logger.logInfo(TICKET_BOOKED_MESSAGE+ticket14);
        List<Passenger> cnf = mmt.getConfirmedList();
        for(Passenger p: cnf){
            logger.logInfo(p.getConfirmationNumber());
        }
    }
}
