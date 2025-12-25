import java.io.InputStream;
import java.util.*;

public class countingrooms {
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        char[][] grid = new char[n][m];
        for (int i = 0; i < n; i++) {
            grid[i] = sc.next().toCharArray();
        }
        int rooms = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (grid[i][j] == '.') {
                    dfs(grid, i, j, n, m);
                    rooms++;
                }
            }
        }
        System.out.println(rooms);
        sc.close();
    }

    public static void dfs(char[][] grid, int r, int c, int n, int m) {
        Queue<Integer> rows = new LinkedList<>();
        Queue<Integer> cols = new LinkedList<>();
        rows.add(r);
        cols.add(c);
        while (!rows.isEmpty()) {
            int row = rows.poll();
            int col = cols.poll();
            if (row < n && row >= 0 && col >= 0 && col < m && grid[row][col] == '.') {
                grid[row][col] = '#';
                rows.add(row + 1);
                cols.add(col);
                rows.add(row - 1);
                cols.add(col);
                rows.add(row);
                cols.add(col + 1);
                rows.add(row);
                cols.add(col - 1);
            }
        }
    }
}
