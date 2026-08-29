class FeeAccount {
    private String regNo;
    private double totalFee;
    private double amountPaid;

    FeeAccount(String regNo, double totalFee) {
        this.regNo = regNo;
        this.totalFee = totalFee;
        this.amountPaid = 0;
    }

    void pay(double amount) {
        if (amount > 0)
            amountPaid += amount;
        else
            System.out.println("Payment rejected");
    }

    double getDue() {
        return totalFee - amountPaid;
    }
}

class HostelFeeAccount extends FeeAccount {

    HostelFeeAccount(String regNo, double totalFee) {
        super(regNo, totalFee);
    }

    void payInTwoInstallments(double amount) {
        pay(amount / 2);
        pay(amount / 2);
    }
}

class HostelRoom {
    String roomNo;
    int beds;
    int occupied;

    HostelRoom(String roomNo, int beds, int occupied) {
        this.roomNo = roomNo;
        this.beds = beds;
        this.occupied = occupied;
    }

    void allot(String name) {
        if (occupied < beds)
            occupied++;
    }

    static HostelRoom findAvailableRoom(HostelRoom[] rooms) {

        for (HostelRoom r : rooms) {
            if (r.occupied < r.beds)
                return r;
        }

        return null;
    }

    static HostelRoom safeAllot(
            HostelRoom[] rooms, String studentName) {

        HostelRoom room = findAvailableRoom(rooms);

        if (room != null)
            room.allot(studentName);

        return room;
    }
}

class SrmStudent {
    String name;
    String regNo;
    HostelFeeAccount feeAccount;
    HostelRoom room;

    static int totalStudents = 0;

    SrmStudent(String name, String regNo,
               HostelFeeAccount feeAccount,
               HostelRoom room) {

        this.name = name;
        this.regNo = regNo;
        this.feeAccount = feeAccount;
        this.room = room;

        totalStudents++;
    }

    String fullStatus() {

        String roomStatus =
            (room == null) ? "unallotted" : room.roomNo;

        return name +
               " | Due: Rs " + feeAccount.getDue() +
               " | Room: " + roomStatus;
    }
}

public class F5 {
    public static void main(String[] args) {

        HostelRoom[] rooms = {
            new HostelRoom("C-214", 1, 0),
            new HostelRoom("C-507", 1, 0)
        };

        HostelFeeAccount f1 =
            new HostelFeeAccount("RA01", 200000);

        HostelFeeAccount f2 =
            new HostelFeeAccount("RA02", 200000);

        HostelFeeAccount f3 =
            new HostelFeeAccount("RA03", 200000);

        f1.pay(60000);
        f2.pay(20000);

        // Invalid payment - rejected
        f3.pay(-5000);

        HostelRoom r1 =
            HostelRoom.safeAllot(rooms, "Ravi");

        HostelRoom r2 =
            HostelRoom.safeAllot(rooms, "Anitha");

        SrmStudent s1 =
            new SrmStudent("Ravi", "RA01", f1, r1);

        SrmStudent s2 =
            new SrmStudent("Anitha", "RA02", f2, r2);

        SrmStudent s3 =
            new SrmStudent("Karthik", "RA03", f3, null);

        System.out.println(s1.fullStatus());
        System.out.println(s2.fullStatus());
        System.out.println(s3.fullStatus());

        System.out.println(
            "Total students: " + SrmStudent.totalStudents
        );
    }
}