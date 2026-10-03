// F3.java

import java.util.*;

public class F3 {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        sc.nextLine();

        String[] books = new String[n];

        for (int i = 0; i < n; i++) {
            books[i] = sc.nextLine();
        }

        String search = sc.nextLine();

        boolean found = false;

        for (String book : books) {

            if (book.equalsIgnoreCase(search)) {
                System.out.println("Book Found");
                found = true;
                break;
            }
        }

        if (!found)
            System.out.println("Book Not Found");

        sc.close();
    }
}