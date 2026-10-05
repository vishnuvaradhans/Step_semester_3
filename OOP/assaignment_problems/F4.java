public class F4 {

    static int firstGreaterOrEqual(
            int[] scores,
            int target) {

        int left = 0;
        int right = scores.length;

        while (left < right) {

            int mid = left + (right - left) / 2;

            if (scores[mid] >= target)
                right = mid;
            else
                left = mid + 1;
        }

        return left;
    }

    static int firstGreater(
            int[] scores,
            int target) {

        int left = 0;
        int right = scores.length;

        while (left < right) {

            int mid = left + (right - left) / 2;

            if (scores[mid] > target)
                right = mid;
            else
                left = mid + 1;
        }

        return left;
    }

    static int countInBand(
            int[] scores,
            int low,
            int high) {

        int first = firstGreaterOrEqual(scores, low);
        int afterLast = firstGreater(scores, high);

        return afterLast - first;
    }

    public static void main(String[] args) {

        int[] scores =
            {35, 42, 42, 50, 58, 58, 58, 63, 71, 88};

        System.out.println(
            countInBand(scores, 42, 58)
        );

        System.out.println(
            countInBand(scores, 90, 100)
        );
    }
}