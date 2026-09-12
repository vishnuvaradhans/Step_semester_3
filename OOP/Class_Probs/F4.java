class EventTicket {
    protected double basePrice;

    public EventTicket(double basePrice) {
        this.basePrice = basePrice;
    }

    String printTicket() {
        return "Standard | Balance: " + basePrice;
    }
}

class WorkshopTicket extends EventTicket {
    private String track;

    public WorkshopTicket(double basePrice, String track) {
        super(basePrice);
        this.track = track;
    }

    String getTrack() {
        return track;
    }

    @Override
    String printTicket() {
        return "Workshop | Track: " + track +
               " | Balance: " + basePrice;
    }
}

public class F4 {

    static String batchPrint(EventTicket[] tickets) {

        StringBuilder result =
            new StringBuilder();

        for (EventTicket ticket : tickets) {

            result.append(ticket.printTicket());

            if (ticket instanceof WorkshopTicket) {
                WorkshopTicket w =
                    (WorkshopTicket) ticket;

                result.append(
                    " [Track via downcast: "
                );

                result.append(w.getTrack());
                result.append("]");
            }

            result.append(" | ");
        }

        return result.toString();
    }

    public static void main(String[] args) {

        EventTicket[] tickets = {
            new EventTicket(500),
            new WorkshopTicket(1200, "AI/ML")
        };

        System.out.println(
            batchPrint(tickets)
        );
    }
}