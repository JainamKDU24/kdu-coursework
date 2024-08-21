package homework.question3;

import hw.question4.InsuranceBrand;


public abstract class HealthInsurancePlan {
    private double coverage;
    InsuranceBrand OfferedBy;

    public InsuranceBrand getOfferedBy() {
        return OfferedBy;
    }

    public void setOfferedBy(InsuranceBrand offeredBy) {
        OfferedBy = offeredBy;
    }

    public double getCoverage() {
        return coverage;
    }

    public void setCoverage(double coverage) {
        this.coverage = coverage;
    }

    public abstract double computeMonthlyPremium(double salary);
    public abstract double computeMonthlyPremium(double salary, int age, boolean smoking);


}
