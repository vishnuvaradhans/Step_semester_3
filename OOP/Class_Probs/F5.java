import java.util.*;

interface IPaymentMethod {
    boolean pay(double amount);
    String getName();
}

class CreditCardPayment implements IPaymentMethod {

    public boolean pay(double amount) {
        return true;
    }

    public String getName() {
        return "Credit Card";
    }
}

class DigitalWalletPayment implements IPaymentMethod {

    public boolean pay(double amount) {
        return false;
    }

    public String getName() {
        return "Digital Wallet";
    }
}

class LineItem {
    String food;
    int quantity;

    LineItem(String food, int quantity) {
        this.food = food;
        this.quantity = quantity;
    }
}

class Order {

    private ArrayList<LineItem> items =
        new ArrayList<>();

    void addItem(String food, int quantity) {
        items.add(new LineItem(food, quantity));

        System.out.println(
            "Added " + food +
            " (Qty " + quantity + ")"
        );
    }

    void place(IPaymentMethod payment) {

        if (items.isEmpty()) {
            System.out.println(
                "Cannot place order: Order must contain at least one item."
            );
            return;
        }

        System.out.println("Order placed successfully.");

        if (payment.pay(100)) {
            System.out.println(
                "Payment via " + payment.getName() +
                " successful."
            );
            System.out.println("Order status: Paid");
        } else {
            System.out.println(
                "Payment via " + payment.getName() +
                " failed."
            );
            System.out.println("Order status: Pending Payment");
        }
    }
}

public class F5 {
    public static void main(String[] args) {

        Order order1 = new Order();

        order1.addItem("Pizza", 2);
        order1.addItem("Soda", 1);

        order1.place(
            new CreditCardPayment()
        );

        Order order2 = new Order();

        order2.addItem("Burger", 1);

        order2.place(
            new DigitalWalletPayment()
        );
    }
}