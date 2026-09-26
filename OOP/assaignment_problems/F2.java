abstract class Vehicle {
    String name;
    boolean available = true;

    Vehicle(String name) {
        this.name = name;
    }

    abstract double calculateCharge(int days);
}

class LuxuryCar extends Vehicle {
    LuxuryCar(String name) {
        super(name);
    }

    double calculateCharge(int days) {
        return days * 100;
    }
}

class StandardCar extends Vehicle {
    StandardCar(String name) {
        super(name);
    }

    double calculateCharge(int days) {
        return days * 50;
    }
}

class RentalService {
    void rent(Vehicle v, int days) {
        if (!v.available) {
            System.out.println(v.name + " is not available.");
            return;
        }

        v.available = false;

        System.out.println(v.name + " rented for " + days + " days.");
        System.out.println("Total charge: $" + v.calculateCharge(days));
    }

    void returnVehicle(Vehicle v) {
        v.available = true;
        System.out.println(v.name + " returned. Now available.");
    }
}

public class F2 {
    public static void main(String[] args) {
        Vehicle v1 = new LuxuryCar("Luxury Car A");
        Vehicle v2 = new StandardCar("Standard Car B");

        RentalService r = new RentalService();

        r.rent(v1, 3);
        r.rent(v2, 5);
        r.returnVehicle(v1);
    }
}