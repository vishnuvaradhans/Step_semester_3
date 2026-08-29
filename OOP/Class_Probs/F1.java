class SrmStudent {
    String name;
    String regNo;
    int attendance;

    SrmStudent(String name, String regNo, int attendance) {
        this.name = name;
        this.regNo = regNo;
        this.attendance = attendance;
    }

    void addAttendanceUpdate(int newAttendance) {
        attendance = newAttendance;
    }

    boolean isEligible() {
        return attendance >= 75;
    }

    // Static because it calculates average for all students.
    // isEligible() is non-static because it depends on one student.
    static double classAverage(SrmStudent[] students) {
        double total = 0;

        for (SrmStudent s : students)
            total += s.attendance;

        return total / students.length;
    }
}

public class F1 {
    public static void main(String[] args) {

        SrmStudent[] students = {
            new SrmStudent("Ravi", "RA01", 82),
            new SrmStudent("Anitha", "RA02", 68),
            new SrmStudent("Karthik", "RA03", 91),
            new SrmStudent("Meera", "RA04", 74),
            new SrmStudent("Suresh", "RA05", 60)
        };

        for (SrmStudent s : students) {
            System.out.println(
                s.name + " - " + s.attendance + "% - " +
                (s.isEligible() ? "Eligible" : "Detained")
            );
        }

        System.out.println(
            "Class average: " + SrmStudent.classAverage(students) + "%"
        );
    }
}