import java.io.InputStream;
import java.util.*;

public class monsters {
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        //FastIO sc = new FastIO(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        char[][] grid = new char[n][m];
        int[][] monstersDist = new int[n][m];
        boolean[][] monstersVisited = new boolean[n][m];
        Queue<Integer> monsterR = new LinkedList<>();
        Queue<Integer> monsterC = new LinkedList<>();
        int startR = 0;
        int startC = 0;
        for (int i = 0; i < n; i++) {
            grid[i] = sc.next().toCharArray();
            for (int j = 0; j < m; j++) {
                monstersDist[i][j] = Integer.MAX_VALUE;
                if (grid[i][j] == 'A') {
                    startR = i;
                    startC = j;
                } else if (grid[i][j] == 'M') {
                    monsterR.add(i);
                    monsterC.add(j);
                    monstersVisited[i][j] = true;
                }
            }
        }
        String dirs = "UDLR";
        int[] dR = {-1, 1, 0, 0};
        int[] dC = {0, 0, -1, 1};
        int dist = 0;
        while (!monsterC.isEmpty()) {
            int size = monsterC.size();
            for (int i = 0; i < size; i++) {
                int row = monsterR.poll();
                int col = monsterC.poll();
                monstersDist[row][col] = dist;
                for (int dir = 0; dir < 4; dir++) {
                    int newRow = row + dR[dir];
                    int newCol = col + dC[dir];
                    if (newRow < 0 || newRow >= n || newCol < 0 || newCol >= m || grid[newRow][newCol] == '#' || monstersVisited[newRow][newCol]) {
                        continue;
                    }
                    monstersVisited[newRow][newCol] = true;
                    monsterR.add(newRow);
                    monsterC.add(newCol);
                }

            }
            dist++;
        }
        boolean res = bfs(grid, monstersDist, startR, startC, n, m);
        if (!res) {
            System.out.println("NO");
        }
    }

    public static boolean bfs(char[][] grid, int[][] distances, int startR, int startC, int n, int m) {
        String dirs = "UDLR";
        int[] dR = {-1, 1, 0, 0};
        int[] dC = {0, 0, -1, 1};
        Queue<Integer> rows = new LinkedList<>();
        Queue<Integer> cols = new LinkedList<>();
        rows.add(startR);
        cols.add(startC);
        boolean[][] visited = new boolean[n][m];
        visited[startR][startC] = true;
        int[][] prevMove = new int[n][m];
        int dist = 0;
        while (!rows.isEmpty()) {
            int size = rows.size();
            for (int i = 0; i < size; i++) {
                int row = rows.poll();
                int col = cols.poll();
                if (distances[row][col] <= dist) {
                    continue;
                }
                if (row == 0 || col == 0 || row == n - 1 || col == m - 1) {
                    StringBuilder sb = new StringBuilder();
                    System.out.println("YES");
                    while (row != startR || col != startC) {
                        int tempR = row;
                        int tempC = col;
                        int prev = prevMove[tempR][tempC];
                        sb.append(dirs.charAt(prev));
                        row -= dR[prev];
                        col -= dC[prev];
                    }
                    sb.reverse();
                    System.out.println(sb.length());
                    System.out.println(sb);
                    return true;
                }
                for (int dir = 0; dir < 4; dir++) {
                    int newRow = row + dR[dir];
                    int newCol = col + dC[dir];
                    if (newRow < 0 || newRow >= n || newCol < 0 || newCol >= m || grid[newRow][newCol] == '#' || visited[newRow][newCol]) {
                        continue;
                    }
                    visited[newRow][newCol] = true;
                    prevMove[newRow][newCol] = dir;
                    rows.add(newRow);
                    cols.add(newCol);
                }

            }
            dist++;
        }
        return false;
    }

    public static ArrayList<ArrayList<Integer>> buildGraph(int n, int m, Scanner sc, boolean directed) {
        ArrayList<ArrayList<Integer>> graph = new ArrayList<>();
        for (int i = 0; i <= n; i++) {
            graph.add(new ArrayList<>());
        }
        for (int i = 0; i < m; i++) {
            int u = sc.nextInt();
            int v = sc.nextInt();
            graph.get(u).add(v);
            if (!directed) {
                graph.get(v).add(u);
            }
        }
        return graph;
    }

    public static ArrayList<ArrayList<Integer>> buildDirectedGraph(int n, int m, Scanner sc) {
        return buildGraph(n, m, sc, true);
    }

    public static ArrayList<ArrayList<Integer>> buildUndirectedGraph(int n, int m, Scanner sc) {
        return buildGraph(n, m, sc, false);
    }

    static final Random random = new Random();

    static void ruffleSort(int[] a) {
        int n = a.length;
        for (int i = 0; i < n; i++) {
            int oi = random.nextInt(n), temp = a[oi];
            a[oi] = a[i];
            a[i] = temp;
        }
        Arrays.sort(a);
    }

    public static class FastIO {
        InputStream dis;
        byte[] buffer = new byte[1 << 17];
        int pointer = 0;

        public FastIO(InputStream is) {
            dis = is;
        }

        int nextInt() throws Exception {
            int ret = 0;
            byte b;
            do {
                b = nextByte();
            } while (b <= ' ');
            boolean negative = false;
            if (b == '-') {
                negative = true;
                b = nextByte();
            }
            while (b >= '0' && b <= '9') {
                ret = 10 * ret + b - '0';
                b = nextByte();
            }
            return (negative) ? -ret : ret;
        }

        byte nextByte() throws Exception {
            if (pointer == buffer.length) {
                dis.read(buffer, 0, buffer.length);
                pointer = 0;
            }
            return buffer[pointer++];
        }

        public void close() throws Exception {
            if (dis == null) {
                return;
            }
            dis.close();
        }
    }
}
