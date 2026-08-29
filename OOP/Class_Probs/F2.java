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
            System.out.println("Invalid payment");
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

class ScholarshipFeeAccount extends FeeAccount {
    private double scholarshipPercent;

    ScholarshipFeeAccount(String regNo, double totalFee,
                          double scholarshipPercent) {
        super(regNo, totalFee);
        this.scholarshipPercent = scholarshipPercent;
    }

    double effectiveDue() {
        return getDue() * (1 - scholarshipPercent / 100);
    }
}

public class F2 {
    public static void main(String[] args) {

        FeeAccount plain =
            new FeeAccount("RA01", 150000);

        HostelFeeAccount hostel =
            new HostelFeeAccount("RA02", 200000);

        ScholarshipFeeAccount scholarship =
            new ScholarshipFeeAccount("RA03", 180000, 20);

        plain.pay(150000);
        hostel.pay(60000);

        FeeAccount[] accounts = {plain, hostel, scholarship};

        for (FeeAccount a : accounts) {

            if (a instanceof ScholarshipFeeAccount) {
                ScholarshipFeeAccount s =
                    (ScholarshipFeeAccount) a;

                System.out.println(
                    "Scholarship account effective due: Rs "
                    + s.effectiveDue()
                );
            }

            else if (a instanceof HostelFeeAccount) {
                System.out.println(
                    "Hostel account due: Rs " + a.getDue()
                );
            }

            else {
                System.out.println(
                    "Plain account due: Rs " + a.getDue()
                );
            }
        }
    }
}