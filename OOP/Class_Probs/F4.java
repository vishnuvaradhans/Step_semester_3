class BrokenSrmStudent {
    static String name;
    static String regNo;
    static int attendance;

    BrokenSrmStudent(String n, String r, int a) {
        name = n;
        regNo = r;
        attendance = a;
    }

    // These should not be static because name, regNo and
    // attendance are different for every student.
}

class SrmStudent {
    String name;
    String regNo;
    int attendance;

    static String university = "SRMIST";
    static int admissionCount = 0;

    SrmStudent(String name, int attendance) {
        this.name = name;
        this.attendance = attendance;

        admissionCount++;
        this.regNo =
            "RA2311003010" + (10 + admissionCount);
    }

    void printIdCard() {
        System.out.println(name + " | " + regNo);
    }

    static void printTotalAdmissions() {
        System.out.println(
            "Students admitted so far: " + admissionCount
        );
    }
}

public class F4 {
    public static void main(String[] args) {

        System.out.println("Broken version:");

        BrokenSrmStudent s1 =
            new BrokenSrmStudent("Ravi", "RA01", 82);

        BrokenSrmStudent s2 =
            new BrokenSrmStudent("Meera", "RA02", 74);

        System.out.println(s1.name);
        System.out.println(s2.name);

        System.out.println("\nFixed version:");

        SrmStudent student1 =
            new SrmStudent("Ravi", 82);

        SrmStudent student2 =
            new SrmStudent("Meera", 74);

        student1.printIdCard();
        student2.printIdCard();

        SrmStudent.printTotalAdmissions();
    }
}