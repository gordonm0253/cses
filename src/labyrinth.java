import java.io.InputStream;
import java.util.*;

public class labyrinth {
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        //FastIO sc = new FastIO(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        char[][] grid = new char[n][m];
        for (int i = 0; i < n; i++) {
            grid[i] = sc.next().toCharArray();
        }
        int rowA = 0;
        int colA = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (grid[i][j] == 'A') {
                    rowA = i;
                    colA = j;
                }
            }
        }
        String res = bfs(grid, rowA, colA, n, m);
        if (res.equals("NO")) {
            System.out.println(res);
        } else {
            System.out.println("YES");
            System.out.println(res.length());
            System.out.println(res);
        }
    }

    public static String bfs(char[][] grid, int startR, int startC, int n, int m) {
        Queue<Integer> r = new ArrayDeque<>();
        Queue<Integer> c = new ArrayDeque<>();
        int[] dx = {-1, 0, 1, 0};
        int[] dy = {0, 1, 0, -1};
        String dir = "URDL";

        Queue<String> string_q = new ArrayDeque<>();
        r.add(startR);
        c.add(startC);
        string_q.add("");
        boolean[][] visited = new boolean[n][m];
        visited[startR][startC] = true;
        int[][] prevMove = new int[n][m];
        while (!r.isEmpty()) {
            int row = r.poll();
            int col = c.poll();
            if (grid[row][col] == 'B') {
                StringBuilder sb = new StringBuilder();
                while (grid[row][col] != 'A') {
                    int tempR = row;
                    int tempC = col;
                    sb.append(dir.charAt(prevMove[tempR][tempC]));
                    row = row - dx[prevMove[tempR][tempC]];
                    col = col - dy[prevMove[tempR][tempC]];
                }
                sb.reverse();
                return sb.toString();
            }
            //System.out.println(row + " " + col);
            for (int i = 0; i < 4; i++) {
                int newR = row + dx[i];
                int newC = col + dy[i];
                if (newR >= n || newR < 0 || newC >= m || newC < 0 || grid[newR][newC] == '#' || visited[newR][newC]) {
                    continue;
                }
                visited[newR][newC] = true;
                prevMove[newR][newC] = i;
                r.add(newR);
                c.add(newC);
            }

        }
        return "NO";
    }

}