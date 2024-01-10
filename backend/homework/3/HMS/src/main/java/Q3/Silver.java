package Q3;

public class Silver extends HealthInsurancePlan{
    public Silver() {
        setCoverage(0.7);
    }

    @Override
    public double computeMonthlyPremium(double salary) {
        return 0.06*salary;
    }

    @Override
    public double computeMonthlyPremium(double salary, int age, boolean smoking) {
        return 0.06*salary + getOfferedBy().computeMonthlyPremium(this, age, smoking);
    }

}
