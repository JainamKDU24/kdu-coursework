package Q4;
import Q3.*;
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
        logger.info("Updated Premium amount : {}",toString(insurancePlan.computeMonthlyPremium(5000,56,true)));
    }

    private static double toString(double v) {
        return (v);
    }
}
