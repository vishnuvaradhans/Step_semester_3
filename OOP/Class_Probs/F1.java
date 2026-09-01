import java.util.HashSet;

class BusTicket {
    private String passengerName;
    private String destination;
    private boolean checkedIn;

    public BusTicket(String passengerName, String destination) {
        if (passengerName == null || passengerName.trim().isEmpty())
            throw new IllegalArgumentException();

        if (destination == null || destination.trim().isEmpty())
            throw new IllegalArgumentException();

        for (int i = 0; i < passengerName.length(); i++) {
            char ch = passengerName.charAt(i);

            if (!Character.isLetter(ch) && ch != ' ')
                throw new IllegalArgumentException();
        }

        this.passengerName = passengerName.trim();
        this.destination = destination.trim();
        this.checkedIn = false;
    }

    void markCheckedIn() {
        if (checkedIn)
            System.out.println("Ticket already checked in");
        else {
            checkedIn = true;
            System.out.println("Check-in successful");
        }
    }

    static void processBatch(String[][] rawBookings) {
        int valid = 0, rejected = 0, duplicates = 0;

        HashSet<String> accepted = new HashSet<>();

        for (String[] booking : rawBookings) {
            try {
                BusTicket ticket =
                    new BusTicket(booking[0], booking[1]);

                String key =
                    ticket.passengerName.toLowerCase() + "|" +
                    ticket.destination.toLowerCase();

                if (accepted.contains(key))
                    duplicates++;
                else {
                    accepted.add(key);
                    valid++;
                }

            } catch (Exception e) {
                rejected++;
            }
        }

        System.out.println(
            "Valid: " + valid +
            " | Rejected: " + rejected +
            " | Duplicates skipped: " + duplicates
        );
    }
}

public class F1 {
    public static void main(String[] args) {

        String[][] bookings = {
            {"Divya", "Chennai"},
            {"", "Bangalore"},
            {"Ravi123", "Pune"},
            {"Divya", "Chennai"},
            {" ", " "}
        };

        BusTicket.processBatch(bookings);
    }
}