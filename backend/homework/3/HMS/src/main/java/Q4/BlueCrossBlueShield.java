package Q4;
import Q3.*;

public class BlueCrossBlueShield implements InsuranceBrand{
    @Override
    public double computeMonthlyPremium(HealthInsurancePlan insurancePlan, int age, boolean smoking) {
        double amount=0.0;
        if(insurancePlan instanceof Platinum){
            if(age>55) amount+=200;
            if(smoking) amount+=100;
        } else if (insurancePlan instanceof Gold) {
            if(age>55) amount+=150;
            if(smoking) amount+=90;
        } else if (insurancePlan instanceof Silver) {
            if(age>55) amount+=100;
            if(smoking) amount+=80;
        } else if (insurancePlan instanceof Bronze) {
            if(age>55) amount+=50;
            if(smoking) amount+=70;
        }
        return amount;
    }
}
