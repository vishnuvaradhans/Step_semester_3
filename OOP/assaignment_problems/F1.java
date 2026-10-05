import java.util.*;

public class F1 {

    static List<Long> footfallReport(
            int[] visitors,
            int[][] queries) {

        int n = visitors.length;

        long[] prefix = new long[n];
        prefix[0] = visitors[0];

        for (int i = 1; i < n; i++) {
            prefix[i] = prefix[i - 1] + visitors[i];
        }

        List<Long> result = new ArrayList<>();

        for (int[] query : queries) {

            int start = query[0];
            int end = query[1];

            long sum;

            if (start == 0)
                sum = prefix[end];
            else
                sum = prefix[end] - prefix[start - 1];

            result.add(sum);
        }

        return result;
    }

    public static void main(String[] args) {

        int[] visitors = {12, 7, 3, 9, 15, 4, 8};

        int[][] queries = {
            {0, 2},
            {2, 5},
            {4, 6},
            {3, 3}
        };

        System.out.println(
            footfallReport(visitors, queries)
        );
    }
}