import java.io.*;
import java.util.*;

abstract class Ride {
    protected double distance;
    Ride(double d) { distance = d; }
    abstract double calculateFare();
}
class Auto extends Ride {
    Auto(double d) { super(d); }
    double calculateFare() { return 30 + 15 * distance; }
}
class Cab extends Ride {
    private double surge;
    Cab(double d, double s) { super(d); surge = s; }
    double calculateFare() { return (50 + 20 * distance) * surge; }
}
class Bike extends Ride {
    private double waiting;
    Bike(double d, double w) { super(d); waiting = w; }
    double calculateFare() { return 20 + 10 * distance + 2 * waiting; }
}

public class RideFare {
    static String f(double d) { return d == Math.rint(d) ? String.valueOf((long) d) : String.valueOf(d); }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        List<Ride> list = new ArrayList<>();
        List<String> types = new ArrayList<>();
        String line;
        while ((line = br.readLine()) != null) {
            line = line.trim();
            if (line.isEmpty()) continue;
            String[] t = line.split("\\s+");      // Auto 4 km | Cab 6 km surge 1.5 | Bike 3 km waiting 5 min
            double d = Double.parseDouble(t[1]);
            switch (t[0].toLowerCase()) {
                case "auto": list.add(new Auto(d)); types.add("Auto"); break;
                case "cab":  list.add(new Cab(d, t.length > 4 ? Double.parseDouble(t[4]) : 1.0)); types.add("Cab"); break;
                case "bike": list.add(new Bike(d, t.length > 4 ? Double.parseDouble(t[4]) : 0)); types.add("Bike"); break;
            }
        }
        Ride[] rides = list.toArray(new Ride[0]);
        double total = 0;
        for (int i = 0; i < rides.length; i++) {      // polymorphic call, O(n) overall
            double fare = rides[i].calculateFare();
            System.out.println(types.get(i) + ": " + f(fare));
            total += fare;
        }
        System.out.println("Total: " + f(total));
    }
}