class RaceEntry {

    protected String bibNumber;
    protected double entryFee;
    protected double balanceDue;

    private double[] lateFeeHistory = new double[10];
    private int lateFeeCount = 0;

    public RaceEntry(String bibNumber, double entryFee) {

        if (bibNumber == null ||
            bibNumber.trim().length() < 4) {
            throw new IllegalArgumentException("Invalid bib number");
        }

        this.bibNumber = bibNumber;
        this.entryFee = entryFee;
        this.balanceDue = entryFee;
    }

    public void pay(double amount) {
        balanceDue -= amount;
    }

    protected void applyLateFee(double amount) {

        balanceDue += amount;

        lateFeeHistory[lateFeeCount] = amount;
        lateFeeCount++;
    }

    double[] getLateFeeHistory() {

        double[] copy = new double[lateFeeCount];

        for (int i = 0; i < lateFeeCount; i++) {
            copy[i] = lateFeeHistory[i];
        }

        return copy;
    }

    public double getBalanceDue() {
        return balanceDue;
    }
}

class RunnerEntry extends RaceEntry {

    private String category;

    public RunnerEntry(
            String bibNumber,
            double entryFee,
            String category) {

        super(bibNumber, entryFee);
        this.category = category;
    }

    @Override
    protected void applyLateFee(double amount) {
        super.applyLateFee(amount * 2);
    }
}

public class F3 {

    public static void main(String[] args) {

        RunnerEntry r =
                new RunnerEntry(
                        "BIB2001",
                        80,
                        "Open 10K");

        r.pay(30);

        r.applyLateFee(20);

        System.out.println(r.getBalanceDue());

        double[] history = r.getLateFeeHistory();

        history[0] = 999;

        double[] actual = r.getLateFeeHistory();

        System.out.println(actual[0]);
    }
}