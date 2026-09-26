class Room {
    String name;
    boolean available = true;

    Room(String name) {
        this.name = name;
    }

    double price(int days) {
        return days * 200;
    }
}

class Reservation {
    Room room;
    boolean cancelled = false;

    Reservation(Room room) {
        this.room = room;
    }

    void cancel() {
        cancelled = true;
        room.available = true;

        System.out.println("Reservation for " + room.name +
                " cancelled successfully.");
    }
}

class Hotel {
    void book(Room room, int days) {
        if (!room.available) {
            System.out.println("Booking failed: " +
                    room.name + " is not available.");
            return;
        }

        room.available = false;

        System.out.println(room.name + " booked.");
        System.out.println("Total price: $" + room.price(days));
    }
}

public class F3 {
    public static void main(String[] args) {
        Room r1 = new Room("Deluxe Room 101");
        Room r2 = new Room("Standard Room 205");

        Hotel h = new Hotel();

        h.book(r1, 4);
        h.book(r2, 4);

        h.book(r1, 4);

        Reservation res = new Reservation(r1);
        res.cancel();
    }
}