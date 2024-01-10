package question3;

public class Bronze extends HealthInsurancePlan {
    public Bronze() {
        setCoverage(0.6);
    }

    @Override
    public double computeMonthlyPremium(double salary) {
        return 0.05*salary;
    }

    @Override
    public double computeMonthlyPremium(double salary, int age, boolean smoking) {
        return 0.05*salary + getOfferedBy().computeMonthlyPremium(this, age, smoking);

    }
}
