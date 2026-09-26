class Room {
    String name;
    double price;
    boolean booked = false;

    Room(String name, double price) {
        this.name = name;
        this.price = price;
    }
}

class Reservation {
    Room room;
    String start;
    String end;
    boolean active = true;

    Reservation(Room room, String start, String end) {
        this.room = room;
        this.start = start;
        this.end = end;
    }
}

class Hotel {

    boolean available(Room room) {
        return !room.booked;
    }

    Reservation book(
            Room room,
            String start,
            String end,
            int days) {

        if (!available(room)) {
            System.out.println(
                "Booking failed: " + room.name +
                " is not available."
            );
            return null;
        }

        room.booked = true;

        double price = room.price * days;

        System.out.printf(
            "%s booked from %s to %s. Total price: $%.2f%n",
            room.name, start, end, price
        );

        return new Reservation(room, start, end);
    }

    void cancel(Reservation r) {

        if (r != null && r.active) {
            r.active = false;
            r.room.booked = false;

            System.out.println(
                "Reservation for " + r.room.name +
                " cancelled successfully."
            );
        }
    }
}

public class F3 {
    public static void main(String[] args) {

        Room deluxe =
            new Room("Deluxe Room 101", 200);

        Room standard =
            new Room("Standard Room 205", 150);

        Hotel hotel = new Hotel();

        Reservation r1 =
            hotel.book(
                deluxe,
                "2024-12-01",
                "2024-12-05",
                4
            );

        hotel.book(
            standard,
            "2024-12-03",
            "2024-12-07",
            4
        );

        hotel.book(
            deluxe,
            "2024-12-03",
            "2024-12-07",
            4
        );

        hotel.cancel(r1);
    }
}