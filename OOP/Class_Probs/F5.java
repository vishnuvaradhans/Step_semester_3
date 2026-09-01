class BusTicketAccount {
    String bookingId;
    double ticketFare;

    static String depotName;

    static {
        depotName = "Central Bus Depot";
    }

    public BusTicketAccount(String bookingId, double ticketFare) {
        this.bookingId = bookingId;
        this.ticketFare = ticketFare;
    }

    public BusTicketAccount(String bookingId) {
        this(bookingId, 0);
    }

    final double calculatePenalty(int minutesLate) {

        if (minutesLate < 0)
            throw new IllegalArgumentException("Invalid delay");

        if (minutesLate == 0)
            return 0;

        double percent = 0;

        percent += Math.min(minutesLate, 5) * 0.5;

        if (minutesLate > 5)
            percent += Math.min(minutesLate - 5, 10);

        if (minutesLate > 15)
            percent += (minutesLate - 15) * 2.0;

        return ticketFare * percent / 100;
    }
}

class Sleeper extends BusTicketAccount {

    public Sleeper(String bookingId, double ticketFare) {
        super(bookingId, ticketFare);
    }
}

public class F5 {

    static double processAccount(BusTicketAccount account,
                                 double amount,
                                 int minutesLate) {

        account.ticketFare = amount;

        double penalty = account.calculatePenalty(minutesLate);

        if (account instanceof Sleeper)
            penalty *= 0.9;

        return penalty;
    }

    static void processBatch(BusTicketAccount[] accounts,
                             double[] amounts,
                             int[] minutesLateArray) {

        if (accounts.length != amounts.length ||
            accounts.length != minutesLateArray.length) {
            System.out.println("Invalid batch: array lengths do not match");
            return;
        }

        int processed = 0;
        int nullSkipped = 0;
        int sleeper = 0;
        int regular = 0;

        double grandTotal = 0;

        for (int i = 0; i < accounts.length; i++) {

            if (accounts[i] == null) {
                nullSkipped++;
                continue;
            }

            grandTotal += processAccount(
                accounts[i],
                amounts[i],
                minutesLateArray[i]
            );

            processed++;

            if (accounts[i] instanceof Sleeper)
                sleeper++;
            else
                regular++;
        }

        System.out.println(
            processed + " processed | " +
            nullSkipped + " null skipped | " +
            sleeper + " sleeper | " +
            regular + " regular | " +
            "grand total penalties = Rs " + grandTotal
        );
    }

    public static void main(String[] args) {

        BusTicketAccount[] accounts = {
            new Sleeper("BK001", 2000),
            null,
            new BusTicketAccount("BK002", 1200)
        };

        double[] amounts = {1200, 900, 700};
        int[] minutesLate = {10, 5, 0};

        processBatch(accounts, amounts, minutesLate);
    }
}