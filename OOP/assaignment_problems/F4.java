final class SurgeFeeCalculator {
    private final double minimumSurgePercent;

    public SurgeFeeCalculator(double minimumSurgePercent) {
        this.minimumSurgePercent = minimumSurgePercent;
    }

    final double calculateSurgeFee(double orderValue, int delayMinutes) {
        if (orderValue < 0 || delayMinutes < 0)
            throw new IllegalArgumentException("Invalid input");

        if (delayMinutes == 0)
            return 0;

        double surgePercent = 0;

        int first = Math.min(delayMinutes, 5);
        surgePercent += first * 0.5;

        if (delayMinutes > 5) {
            int second = Math.min(delayMinutes - 5, 10);
            surgePercent += second * 1.0;
        }

        if (delayMinutes > 15) {
            int third = delayMinutes - 15;
            surgePercent += third * 2.0;
        }

        double tieredFee = orderValue * surgePercent / 100;
        double minimumFee = orderValue * minimumSurgePercent / 100;

        return Math.max(tieredFee, minimumFee);
    }
}

public class F4 {
    public static void main(String[] args) {
        SurgeFeeCalculator calculator =
            new SurgeFeeCalculator(1);

        System.out.println("Rs " +
            calculator.calculateSurgeFee(500, 0));

        System.out.println("Rs " +
            calculator.calculateSurgeFee(500, 1));

        System.out.println("Rs " +
            calculator.calculateSurgeFee(500, 16));
    }
}