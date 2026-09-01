import java.util.Arrays;

class FareSplitter {
    private String tripId;
    private double totalFare;
    private int passengerCount;

    public FareSplitter(String tripId,
                        double totalFare,
                        int passengerCount) {

        if (totalFare < 0 || passengerCount <= 0)
            throw new IllegalArgumentException("Invalid fare split");

        this.tripId = tripId;
        this.totalFare = totalFare;
        this.passengerCount = passengerCount;
    }

    public FareSplitter(String tripId, double totalFare) {
        this(tripId, totalFare, 2);
    }

    public FareSplitter(String tripId) {
        this(tripId, 0, 2);
    }

    double[] fareBreakdown() {
        double[] shares = new double[passengerCount];

        long totalPaise = Math.round(totalFare * 100);
        long base = totalPaise / passengerCount;
        long remainder = totalPaise % passengerCount;

        for (int i = 0; i < passengerCount; i++)
            shares[i] = base / 100.0;

        for (int i = passengerCount - (int)remainder;
             i < passengerCount; i++) {
            if (i >= 0)
                shares[i] += 0.01;
        }

        return shares;
    }

    boolean isConfirmationOverdue(int confirmed, int expected) {
        return confirmed < expected;
    }
}

public class F2 {
    public static void main(String[] args) {

        FareSplitter f1 =
            new FareSplitter("TRIP001", 100000, 3);

        FareSplitter f2 =
            new FareSplitter("TRIP003");

        System.out.println(Arrays.toString(f1.fareBreakdown()));
        System.out.println(Arrays.toString(f2.fareBreakdown()));
    }
}