package hw.question4;
import homework.question3.HealthInsurancePlan;

public interface InsuranceBrand {
    public double computeMonthlyPremium(HealthInsurancePlan insurancePlan, int age, boolean smoking);
}
