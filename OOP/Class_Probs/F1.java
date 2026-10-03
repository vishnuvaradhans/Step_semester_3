// F1.java

import java.util.*;

public class F1 {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        int target = sc.nextInt();

        int[] arr = new int[n];

        for (int i = 0; i < n; i++)
            arr[i] = sc.nextInt();

        boolean found = false;

        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {

                if (arr[i] + arr[j] == target) {
                    System.out.println(i + " " + j);
                    found = true;
                    break;
                }
            }

            if (found)
                break;
        }

        if (!found)
            System.out.println("-1");

        sc.close();
    }
}