import java.util.Arrays;

class PatientVitals {

    private double[] readings;
    private int count;

    PatientVitals(double[] initialReadings) {

        readings = new double[500];
        count = 0;

        if (initialReadings != null) {
            for (double reading : initialReadings)
                recordReading(reading);
        }
    }

    void recordReading(double reading) {

        if (reading <= 0 || reading > 45)
            return;

        if (count < readings.length) {
            readings[count] = reading;
            count++;
        }
    }

    double getAverage() {

        if (count == 0)
            return 0;

        double total = 0;

        for (int i = 0; i < count; i++)
            total += readings[i];

        return total / count;
    }

    double[] getAllReadings() {

        double[] copy = new double[count];

        for (int i = 0; i < count; i++)
            copy[i] = readings[i];

        return copy;
    }
}

public class F3 {
    public static void main(String[] args) {

        PatientVitals v =
            new PatientVitals(
                new double[]{36.5, -2, 37.1}
            );

        System.out.println(
            Arrays.toString(v.getAllReadings())
        );

        double[] copy = v.getAllReadings();

        copy[0] = 999;

        System.out.println(
            v.getAllReadings()[0]
        );

        System.out.println(
            "Average: " + v.getAverage()
        );
    }
}