// F4.java

import java.util.*;

public class F4 {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        sc.nextLine();

        String[] books = new String[n];

        for (int i = 0; i < n; i++) {
            books[i] = sc.nextLine();
        }

        String search = sc.nextLine();

        for (int i = 0; i < n; i++) {

            if (books[i].equalsIgnoreCase(search)) {
                System.out.println("Book Found at index " + i);
                sc.close();
                return;
            }
        }

        System.out.println("Book Not Found");

        sc.close();
    }
}