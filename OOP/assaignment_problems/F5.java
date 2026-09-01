class DeliveryAccount {
    String studentId;
    double orderValue;

    static String systemName;

    static {
        systemName = "Campus Delivery System";
    }

    public DeliveryAccount(String studentId, double orderValue) {
        this.studentId = studentId;
        this.orderValue = orderValue;
    }

    public DeliveryAccount(String studentId) {
        this(studentId, 0);
    }

    final double calculateSurgeFee(int delayMinutes) {
        if (delayMinutes < 0)
            throw new IllegalArgumentException("Invalid delay");

        if (delayMinutes == 0)
            return 0;

        double percent = 0;

        percent += Math.min(delayMinutes, 5) * 0.5;

        if (delayMinutes > 5)
            percent += Math.min(delayMinutes - 5, 10) * 1.0;

        if (delayMinutes > 15)
            percent += (delayMinutes - 15) * 2.0;

        return orderValue * percent / 100;
    }
}

class Premium extends DeliveryAccount {

    public Premium(String studentId, double orderValue) {
        super(studentId, orderValue);
    }
}

public class F5 {

    static void processAccount(DeliveryAccount account,
                               double amount,
                               int delayMinutes) {
        account.orderValue = amount;
        account.calculateSurgeFee(delayMinutes);
    }

    static void processBatch(DeliveryAccount[] accounts,
                             double[] amounts,
                             int[] delayMinutesArray) {

        if (accounts.length != amounts.length ||
            accounts.length != delayMinutesArray.length) {
            System.out.println("Invalid batch: array lengths do not match");
            return;
        }

        int processed = 0;
        int skipped = 0;
        int premium = 0;
        int regular = 0;

        double grandTotal = 0;

        for (int i = 0; i < accounts.length; i++) {

            if (accounts[i] == null) {
                skipped++;
                continue;
            }

            processAccount(
                accounts[i],
                amounts[i],
                delayMinutesArray[i]
            );

            double fee =
                accounts[i].calculateSurgeFee(delayMinutesArray[i]);

            grandTotal += fee;
            processed++;

            if (accounts[i] instanceof Premium)
                premium++;
            else
                regular++;
        }

        System.out.println(
            processed + " processed | " +
            skipped + " null skipped | " +
            premium + " premium | " +
            regular + " regular | grand total surge fees = Rs " +
            grandTotal
        );
    }

    public static void main(String[] args) {

        DeliveryAccount[] accounts = {
            new Premium("STU001", 500),
            null,
            new DeliveryAccount("STU002", 300)
        };

        double[] amounts = {500, 400, 300};
        int[] delays = {10, 5, 0};

        processBatch(accounts, amounts, delays);
    }
}