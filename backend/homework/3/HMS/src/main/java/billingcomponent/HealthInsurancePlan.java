package billingcomponent;

public class HealthInsurancePlan {
    private double coverage;

    public HealthInsurancePlan(double coverage) {
        this.coverage=coverage;
    }

    public double getCoverage() {
        return coverage;
    }

    public void setCoverage(double coverage) {
        this.coverage = coverage;
    }

}
