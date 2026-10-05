import java.util.*;

public class F5 {

    static List<Integer> auditRoute(int[][] grid) {

        List<Integer> result =
            new ArrayList<>();

        int top = 0;
        int bottom = grid.length - 1;

        int left = 0;
        int right = grid[0].length - 1;

        while (top <= bottom && left <= right) {

            // Top row
            for (int j = left; j <= right; j++) {
                result.add(grid[top][j]);
            }

            top++;

            // Right column
            for (int i = top; i <= bottom; i++) {
                result.add(grid[i][right]);
            }

            right--;

            // Bottom row
            if (top <= bottom) {

                for (int j = right; j >= left; j--) {
                    result.add(grid[bottom][j]);
                }

                bottom--;
            }

            // Left column
            if (left <= right) {

                for (int i = bottom; i >= top; i--) {
                    result.add(grid[i][left]);
                }

                left++;
            }
        }

        return result;
    }

    public static void main(String[] args) {

        int[][] grid = {
            {1, 2, 3, 4},
            {5, 6, 7, 8},
            {9, 10, 11, 12}
        };

        System.out.println(
            auditRoute(grid)
        );
    }
}