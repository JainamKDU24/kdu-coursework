package kdu.backend3;
import billingcomponent.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Main {

    public static void main(String[] args) {
        Logger logger=LoggerFactory.getLogger(Main.class);
        HealthInsurancePlan insurancePlan = new PlatinumPlan();

        Patient patient = new Patient();
        patient.setId(1);
        patient.setFirstName("Jainam");
        patient.setLastName("Gandhi");
        patient.setGender("Male");
        patient.setEmail("jrgandhi169@gmail.com");
        patient.setPatientId(101);
        patient.setInsured(true);

        patient.setInsurancePlan(insurancePlan);

        double[] payments = Billing.computePaymentAmount(patient, 1000.0);

        logger.info("Insurance coverage: {}" , (payments[0]));
        logger.info("Patient has to pay: {}" , (payments[1]));
    }
}
