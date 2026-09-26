interface PaymentMethod {
    boolean pay(double amount);
}

class CreditCard implements PaymentMethod {
    public boolean pay(double amount) {
        System.out.println("Payment via Credit Card successful.");
        return true;
    }
}

class DigitalWallet implements PaymentMethod {
    public boolean pay(double amount) {
        System.out.println("Payment via Digital Wallet failed.");
        return false;
    }
}

class LineItem {
    String item;
    int quantity;

    LineItem(String item, int quantity) {
        this.item = item;
        this.quantity = quantity;
    }
}

class Order {
    LineItem item;
    String status = "Pending Payment";

    void addItem(String name, int quantity) {
        item = new LineItem(name, quantity);
        System.out.println("Added " + name +
                " (Qty " + quantity + ")");
    }

    void place(PaymentMethod payment) {
        if (item == null) {
            System.out.println(
                    "Cannot place order: Order must contain at least one item.");
            return;
        }

        System.out.println("Order placed successfully.");

        if (payment.pay(100)) {
            status = "Paid";
        }

        System.out.println("Order status: " + status);
    }
}

public class F5 {
    public static void main(String[] args) {

        Order o1 = new Order();

        o1.place(new CreditCard());

        o1.addItem("Pizza", 2);
        o1.place(new CreditCard());

        Order o2 = new Order();

        o2.addItem("Burger", 1);
        o2.place(new DigitalWallet());
    }
}