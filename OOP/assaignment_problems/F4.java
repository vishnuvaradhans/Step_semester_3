abstract class Employee {
    String name;

    Employee(String name) {
        this.name = name;
    }

    abstract boolean canTakeLeave();
}

class FullTime extends Employee {
    FullTime(String name) {
        super(name);
    }

    boolean canTakeLeave() {
        return true;
    }
}

class PartTime extends Employee {
    PartTime(String name) {
        super(name);
    }

    boolean canTakeLeave() {
        return true;
    }
}

class LeaveRequest {
    Employee employee;
    String status = "Pending";

    LeaveRequest(Employee employee) {
        this.employee = employee;
    }

    void approve() {
        if (status.equals("Pending")) {
            status = "Approved";
            System.out.println("Leave request for " +
                    employee.name + " approved.");
        }
    }

    void reject() {
        if (status.equals("Pending")) {
            status = "Rejected";
        }
    }

    void makePending() {
        if (!status.equals("Pending")) {
            System.out.println("Cannot change status: " +
                    status + " request cannot revert to Pending.");
        }
    }
}

public class F4 {
    public static void main(String[] args) {
        Employee e = new FullTime("John Doe");

        LeaveRequest l = new LeaveRequest(e);

        System.out.println("Leave request submitted by " + e.name);
        System.out.println("Status: " + l.status);

        l.approve();
        System.out.println("Status: " + l.status);

        l.makePending();
    }
}