package Q3;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Main {
    public static void main(String args[]){
        final Logger logger = LoggerFactory.getLogger(Q4.Main.class);
        HealthInsurancePlan insurancePlan=new Platinum();

        Doctor doctor = new Doctor();

        doctor.setSalary(100000);
        doctor.setInsurancePlan(insurancePlan);

        logger.info("Monthly Premium: {}",toString(doctor.getInsurancePlan().computeMonthlyPremium(doctor.getSalary())));
    }

    private static double toString(double v) {
        return (v);
    }
}
