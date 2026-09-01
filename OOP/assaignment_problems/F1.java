class FoodOrder {
    private String studentName;
    private String dishName;
    private boolean delivered;

    public FoodOrder(String studentName, String dishName) {
        if (studentName == null || studentName.trim().isEmpty())
            throw new IllegalArgumentException("Invalid student name");

        if (dishName == null || dishName.trim().isEmpty())
            throw new IllegalArgumentException("Invalid dish name");

        this.studentName = studentName;
        this.dishName = dishName;
        this.delivered = false;
    }

    void markDelivered() {
        if (delivered)
            System.out.println("Order already delivered");
        else {
            delivered = true;
            System.out.println("Order delivered");
        }
    }

    static void processBatch(String[][] rawOrders) {
        int valid = 0, rejected = 0;

        for (String[] order : rawOrders) {
            try {
                new FoodOrder(order[0], order[1]);
                valid++;
            } catch (Exception e) {
                rejected++;
            }
        }

        System.out.println("Valid: " + valid + " | Rejected: " + rejected);
    }
}

public class F1 {
    public static void main(String[] args) {
        String[][] orders = {
            {"Ravi", "Paneer Butter Masala"},
            {"", "Chole Bhature"},
            {"Meera", " "},
            {"Divya", "Veg Biryani"}
        };

        FoodOrder.processBatch(orders);
    }
}