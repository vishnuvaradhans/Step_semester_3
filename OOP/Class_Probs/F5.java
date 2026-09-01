public class F5 {

    static String reverseCustomerName(String customerName) {
        char[] name = customerName.toCharArray();
        String reversed = "";

        for (int i = name.length - 1; i >= 0; i--)
            reversed += name[i];

        return reversed;
    }

    public static void main(String[] args) {
        String customerName = "Sunil";

        System.out.println("Original Name: " + customerName);
        System.out.println(
                "Reversed Name: " + reverseCustomerName(customerName));
    }
}