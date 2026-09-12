import java.util.Arrays;

class EventTicket {
    protected double basePrice;
    protected double amountPaid;

    private double[] lateFeeHistory = new double[10];
    private int lateFeeCount = 0;

    public EventTicket(double basePrice) {
        this.basePrice = basePrice;
        this.amountPaid = 0;
    }

    void pay(double amount) {
        amountPaid += amount;
    }

    double getBalanceDue() {
        return basePrice - amountPaid;
    }

    protected void applyLateFee(double amount) {
        basePrice += amount;

        if (lateFeeCount < lateFeeHistory.length) {
            lateFeeHistory[lateFeeCount] = amount;
            lateFeeCount++;
        }
    }

    double[] getLateFeeHistory() {
        return Arrays.copyOf(
            lateFeeHistory,
            lateFeeCount
        );
    }
}

class WorkshopTicket extends EventTicket {

    public WorkshopTicket(double basePrice) {
        super(basePrice);
    }

    @Override
    protected void applyLateFee(double amount) {
        super.applyLateFee(amount * 2);
    }
}

public class F3 {
    public static void main(String[] args) {

        WorkshopTicket w =
            new WorkshopTicket(1200);

        w.pay(1200);
        w.applyLateFee(100);

        System.out.println(w.getBalanceDue());

        double[] history =
            w.getLateFeeHistory();

        System.out.println(
            Arrays.toString(history)
        );

        history[0] = 999;

        System.out.println(
            Arrays.toString(
                w.getLateFeeHistory()
            )
        );
    }
}