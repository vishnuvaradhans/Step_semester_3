import java.util.*;

public class F5
{
    static int[][] exitDistance(char[][] plan)
    {
        int m = plan.length;
        int n = plan[0].length;

        int[][] dist = new int[m][n];
        Queue<int[]> q = new LinkedList<>();

        for (int i = 0; i < m; i++)
        {
            for (int j = 0; j < n; j++)
            {
                dist[i][j] = -1;

                if (plan[i][j] == 'E')
                {
                    dist[i][j] = 0;
                    q.add(new int[]{i, j});
                }
            }
        }

        int[] dr = {-1, 1, 0, 0};
        int[] dc = {0, 0, -1, 1};

        while (!q.isEmpty())
        {
            int[] cell = q.poll();

            int r = cell[0];
            int c = cell[1];

            for (int k = 0; k < 4; k++)
            {
                int nr = r + dr[k];
                int nc = c + dc[k];

                if (nr >= 0 && nr < m &&
                    nc >= 0 && nc < n &&
                    plan[nr][nc] == '.' &&
                    dist[nr][nc] == -1)
                {
                    dist[nr][nc] = dist[r][c] + 1;
                    q.add(new int[]{nr, nc});
                }
            }
        }

        return dist;
    }

    public static void main(String[] args)
    {
        char[][] plan = {
            {'.', '#', '.', 'E'},
            {'.', '.', '.', '#'},
            {'E', '#', '.', '.'}
        };

        int[][] result = exitDistance(plan);

        for (int i = 0; i < result.length; i++)
        {
            for (int j = 0; j < result[0].length; j++)
                System.out.print(result[i][j] + " ");

            System.out.println();
        }
    }
}