package Question4;
import Question3.HealthInsurancePlan;

public interface InsuranceBrand {
    public double computeMonthlyPremium(HealthInsurancePlan insurancePlan, int age, boolean smoking);
}
