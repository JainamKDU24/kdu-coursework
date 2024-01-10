package hw.question4;
import homework.question3.HealthInsurancePlan;
import homework.question3.Platinum;
import homework.question3.User;
import homework.question3.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Main {
    public static void main(String[] args){
        final Logger logger = LoggerFactory.getLogger(Main.class);
        User staff = new User();
        InsuranceBrand insuranceBrand = new BlueCrossBlueShield();
        HealthInsurancePlan insurancePlan = new Platinum();

        insurancePlan.setOfferedBy(insuranceBrand);
        staff.setInsurancePlan(insurancePlan);
        logger.info("Updated Premium amount : {}",(insurancePlan.computeMonthlyPremium(5000,54,true)));
    }
}
