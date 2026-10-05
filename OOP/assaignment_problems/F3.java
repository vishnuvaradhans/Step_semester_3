import java.util.*;

public class F3 {

    static long countPeriods(
            int[] transactions,
            long k) {

        HashMap<Long, Integer> map =
            new HashMap<>();

        map.put(0L, 1);

        long prefixSum = 0;
        long count = 0;

        for (int value : transactions) {

            prefixSum += value;

            long required = prefixSum - k;

            if (map.containsKey(required)) {
                count += map.get(required);
            }

            map.put(
                prefixSum,
                map.getOrDefault(prefixSum, 0) + 1
            );
        }

        return count;
    }

    public static void main(String[] args) {

        int[] transactions =
            {3, 4, -7, 1, 3, 3, 1, -4};

        long k = 7;

        System.out.println(
            countPeriods(transactions, k)
        );
    }
}