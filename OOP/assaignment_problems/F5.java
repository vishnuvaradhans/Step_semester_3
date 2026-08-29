class Employee {
    private String empId;
    private String empName;
    private double salary;

    Employee(String empId, String empName, double salary) {
        this.empId = empId;
        this.empName = empName;
        this.salary = salary;
    }

    double getSalary() {
        return salary;
    }
}

class ManagerEmployee extends Employee {
    private double teamBonus;

    ManagerEmployee(String empId, String empName,
                    double salary, double teamBonus) {
        super(empId, empName, salary);
        this.teamBonus = teamBonus;
    }

    double effectiveSalary() {
        return getSalary() + teamBonus;
    }
}

class ParkingSlot {
    String slotNo;
    int capacity;
    int occupiedCount;

    ParkingSlot(String slotNo, int capacity, int occupiedCount) {
        this.slotNo = slotNo;
        this.capacity = capacity;
        this.occupiedCount = occupiedCount;
    }

    void allot(String vehicleNo) {
        if (occupiedCount < capacity)
            occupiedCount++;
    }

    static ParkingSlot findAvailableSlot(ParkingSlot[] slots) {
        for (ParkingSlot s : slots) {
            if (s.occupiedCount < s.capacity)
                return s;
        }

        return null;
    }

    static ParkingSlot safeAllot(ParkingSlot[] slots, String vehicleNo) {

        ParkingSlot slot = findAvailableSlot(slots);

        if (slot != null)
            slot.allot(vehicleNo);

        return slot;
    }
}

class CompanyEmployeeRecord {
    String name;
    String empId;
    Employee employee;
    ParkingSlot slot;

    static int totalRecords = 0;

    CompanyEmployeeRecord(String name, String empId,
                          Employee employee, ParkingSlot slot) {

        this.name = name;
        this.empId = empId;
        this.employee = employee;
        this.slot = slot;

        totalRecords++;
    }

    String fullProfile() {

        double pay;

        if (employee instanceof ManagerEmployee)
            pay = ((ManagerEmployee)employee).effectiveSalary();
        else
            pay = employee.getSalary();

        String slotInfo =
            (slot == null) ? "no parking assigned" : slot.slotNo;

        return name + " | Pay: Rs " + pay +
               " | Slot: " + slotInfo;
    }
}

public class F5 {
    public static void main(String[] args) {

        ParkingSlot[] slots = {
            new ParkingSlot("A1", 1, 0),
            new ParkingSlot("A2", 1, 0)
        };

        Employee e1 =
            new ManagerEmployee("E101", "Divya", 70000, 8000);

        Employee e2 =
            new Employee("E102", "Karan", 40000);

        Employee e3 =
            new Employee("E103", "Meera", 10000);

        ParkingSlot s1 = ParkingSlot.safeAllot(slots, "EMP1");
        ParkingSlot s2 = ParkingSlot.safeAllot(slots, "EMP2");

        CompanyEmployeeRecord r1 =
            new CompanyEmployeeRecord("Divya", "E101", e1, s1);

        CompanyEmployeeRecord r2 =
            new CompanyEmployeeRecord("Karan", "E102", e2, s2);

        CompanyEmployeeRecord r3 =
            new CompanyEmployeeRecord("Meera", "E103", e3, null);

        System.out.println(r1.fullProfile());
        System.out.println(r2.fullProfile());
        System.out.println(r3.fullProfile());

        System.out.println(
            "Total records: " + CompanyEmployeeRecord.totalRecords
        );
    }
}