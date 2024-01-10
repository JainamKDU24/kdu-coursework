package question3;

public class Gold extends HealthInsurancePlan {
    public Gold() {
        setCoverage(0.8);
    }

    @Override
    public double computeMonthlyPremium(double salary) {
        return 0.07 * salary;
    }

    @Override
    public double computeMonthlyPremium(double salary, int age, boolean smoking) {
        return 0.07 * salary + getOfferedBy().computeMonthlyPremium(this, age, smoking);
    }
}
