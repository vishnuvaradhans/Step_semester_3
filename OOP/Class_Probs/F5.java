class EventTicket {

    private static int ticketCounter = 1000;

    public final String ticketId;

    protected double basePrice;
    protected double amountPaid;

    public EventTicket(double basePrice) {
        ticketCounter++;

        ticketId =
            "TCK-" + ticketCounter;

        this.basePrice = basePrice;
        this.amountPaid = 0;
    }

    void pay(double amount) {
        if (amount > 0)
            amountPaid += amount;
    }

    void pay(double amount, String mode) {
        System.out.println(
            "Payment Mode: " + mode
        );

        pay(amount);
    }

    double getBalanceDue() {
        return basePrice - amountPaid;
    }

    static boolean isValidPromoCode(String code) {

        if (code == null || code.length() != 5)
            return false;

        if (code.charAt(0) != 'F')
            return false;

        for (int i = 1; i <= 3; i++) {
            if (!Character.isDigit(code.charAt(i)))
                return false;
        }

        return Character.isUpperCase(
            code.charAt(4)
        );
    }

    static int getTicketsIssued() {
        return ticketCounter - 1000;
    }
}

class GroupTicket extends EventTicket {

    private int groupSize;

    public GroupTicket(double basePrice,
                       int groupSize) {
        super(basePrice);
        this.groupSize = groupSize;
    }
}

public class F5 {

    static String processNightlySettlement(
            EventTicket[] tickets) {

        int processed = 0;
        int skipped = 0;
        int group = 0;
        int individual = 0;

        for (EventTicket ticket : tickets) {

            if (ticket == null) {
                skipped++;
                continue;
            }

            processed++;

            if (ticket instanceof GroupTicket)
                group++;
            else
                individual++;
        }

        return processed + " processed | " +
               skipped + " null skipped | " +
               group + " group | " +
               individual + " individual";
    }

    public static void main(String[] args) {

        EventTicket t1 =
            new EventTicket(500);

        System.out.println(t1.ticketId);

        System.out.println(
            EventTicket.getTicketsIssued()
        );

        System.out.println(
            EventTicket.isValidPromoCode("F123A")
        );

        System.out.println(
            EventTicket.isValidPromoCode("F12A")
        );

        System.out.println(
            EventTicket.isValidPromoCode("X123A")
        );

        t1.pay(200);
        t1.pay(200, "UPI");

        System.out.println(
            t1.getBalanceDue()
        );

        EventTicket[] tickets = {
            new GroupTicket(2000, 5),
            null,
            new EventTicket(500)
        };

        System.out.println(
            processNightlySettlement(tickets)
        );
    }
}