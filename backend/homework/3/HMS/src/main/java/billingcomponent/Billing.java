package billingcomponent;
import kdu.backend3.Patient;
public class Billing {
    private Billing() {
    }

    public static double[] computePaymentAmount(Patient patient, double amount) {
        double[] payments = new double[2];

        HealthInsurancePlan patientInsurancePlan = patient.getInsurancePlan();
        if(patientInsurancePlan!=null){
            //insurance amount
            double insuranceCoverage = patientInsurancePlan.getCoverage() * amount;
            //before discount
            double beforeDiscount = amount-insuranceCoverage;

            double discount=0;

            //discount based on the plans
            if(patientInsurancePlan instanceof PlatinumPlan){
                discount=50;
            } else if (patientInsurancePlan instanceof GoldPlan) {
                discount=40;
            } else if (patientInsurancePlan instanceof SilverPlan) {
                discount=30;
            } else if (patientInsurancePlan instanceof BronzePlan) {
                discount=25;
            }

            else{
                discount=20;
            }

            //amount patient has to pay
            double finalamt=beforeDiscount-discount;
            payments[0]=insuranceCoverage;
            payments[1]=finalamt;
        }
        else {
            // Patient pays the full amount
            payments[0] = 0;
            payments[1] = amount;
        }
        return payments;
    }
}
