abstract class Vehicle {
    String name;
    boolean available = true;

    Vehicle(String name) {
        this.name = name;
    }

    abstract double calculateCharge(int days);
}

class StandardCar extends Vehicle {
    StandardCar(String name) {
        super(name);
    }

    double calculateCharge(int days) {
        return days * 50;
    }
}

class LuxuryCar extends Vehicle {
    LuxuryCar(String name) {
        super(name);
    }

    double calculateCharge(int days) {
        return days * 100;
    }
}

class RentalService {

    void rent(Vehicle v, int days) {

        if (!v.available) {
            System.out.println(v.name + " is not available.");
            return;
        }

        v.available = false;

        System.out.printf(
            "%s rented for %d days. Total charge: $%.2f%n",
            v.name, days, v.calculateCharge(days)
        );
    }

    void returnVehicle(Vehicle v) {
        v.available = true;
        System.out.println(
            v.name + " returned. Now available."
        );
    }
}

public class F2 {
    public static void main(String[] args) {

        Vehicle luxury =
            new LuxuryCar("Luxury Car A");

        Vehicle standard =
            new StandardCar("Standard Car B");

        RentalService service =
            new RentalService();

        service.rent(luxury, 3);
        service.rent(standard, 5);

        service.returnVehicle(luxury);
    }
}