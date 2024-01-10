package Question4;
import Question3.*;

public class BlueCrossBlueShield implements InsuranceBrand{
    @Override
    public double computeMonthlyPremium(HealthInsurancePlan insurancePlan, int age, boolean smoking) {
        double amount=0.0;
        if (insurancePlan != null) {
            double ageIncrease = 0;
            double smokingIncrease = 0;

            if (age > 55) {
                ageIncrease = (insurancePlan instanceof Platinum) ? 200 :
                        (insurancePlan instanceof Gold) ? 150 :
                                (insurancePlan instanceof Silver) ? 100 :
                                        (insurancePlan instanceof Bronze) ? 50 : 0;
            }
            if (smoking) {
                smokingIncrease = (insurancePlan instanceof Platinum) ? 100 :
                        (insurancePlan instanceof Gold) ? 90 :
                                (insurancePlan instanceof Silver) ? 80 :
                                        (insurancePlan instanceof Bronze) ? 70 : 0;
            }
            amount += ageIncrease + smokingIncrease;
        }
        return amount;
    }
}