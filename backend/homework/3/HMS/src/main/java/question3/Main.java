package question3;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Main {
    public static void main(String[] args){
        final Logger logger = LoggerFactory.getLogger(question4.Main.class);
        HealthInsurancePlan insurancePlan=new Platinum();

        Doctor doctor = new Doctor();

        doctor.setSalary(100000);
        doctor.setInsurancePlan(insurancePlan);

        logger.info("Monthly Premium: {}",doctor.getInsurancePlan().computeMonthlyPremium(doctor.getSalary()));
    }
}
