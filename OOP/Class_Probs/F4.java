abstract class Employee {
    String name;

    Employee(String name) {
        this.name = name;
    }

    abstract boolean canTakeLeave(int days);
}

class FullTime extends Employee {
    FullTime(String name) {
        super(name);
    }

    boolean canTakeLeave(int days) {
        return days <= 30;
    }
}

class PartTime extends Employee {
    PartTime(String name) {
        super(name);
    }

    boolean canTakeLeave(int days) {
        return days <= 15;
    }
}

class LeaveRequest {

    Employee employee;
    String start;
    String end;
    String status = "Pending";

    LeaveRequest(
            Employee employee,
            String start,
            String end) {

        this.employee = employee;
        this.start = start;
        this.end = end;
    }

    void approve() {
        if (status.equals("Pending"))
            status = "Approved";
    }

    void reject() {
        if (status.equals("Pending"))
            status = "Rejected";
    }

    void pending() {
        if (!status.equals("Pending"))
            System.out.println(
                "Cannot change status: " +
                status +
                " request cannot revert to Pending."
            );
    }
}

public class F4 {
    public static void main(String[] args) {

        Employee john =
            new FullTime("John Doe");

        LeaveRequest r1 =
            new LeaveRequest(
                john,
                "2024-10-10",
                "2024-10-12"
            );

        System.out.println(
            "Leave request submitted by " +
            john.name + ". Status: " + r1.status
        );

        r1.approve();

        System.out.println(
            "Leave request for " +
            john.name + " approved. Status: " +
            r1.status
        );

        Employee jane =
            new PartTime("Jane Smith");

        LeaveRequest r2 =
            new LeaveRequest(
                jane,
                "2024-11-01",
                "2024-11-05"
            );

        System.out.println(
            "Leave request submitted by " +
            jane.name + ". Status: " + r2.status
        );

        r1.pending();
    }
}