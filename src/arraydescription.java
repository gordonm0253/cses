import java.io.InputStream;
import java.lang.reflect.Array;
import java.util.*;

public class arraydescription {
    public static void main(String[] args) throws Exception {
        //Scanner sc = new Scanner(System.in);
        FastIO sc = new FastIO(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        int[] x = new int[n];
        int mod = 1000000007;
        for (int i = 0; i < n; i++) {
            x[i] = sc.nextInt();
        }
        long[][] dp = new long[n][m + 1];
        if (x[0] != 0) {
            dp[0][x[0]] = 1;
        } else {
            for (int i = 1; i <= m; i++) {
                dp[0][i] = 1;
            }
        }
        for (int i = 0; i < n - 1; i++) {
            if (x[i] != 0) {
                distribute(dp, i, x[i], m);
            } else {
                for (int j = 1; j <= m; j++) {
                    if (dp[i][j] == 0) continue;
                    distribute(dp, i, j, m);
                }
            }
            for (int j = 1; j <= m; j++) {
                if (dp[i + 1][j] > mod) {
                    dp[i + 1][j] %= mod;
                }
            }
            //System.out.println(Arrays.toString(dp[i]));
        }
        //System.out.println(Arrays.toString(dp[n - 1]));
        long res = 0;
        if (x[n - 1] != 0) {
            res = dp[n - 1][x[n - 1]];
        } else {
            for (int i = 1; i <= m; i++) {
                res += dp[n - 1][i];
                res %= mod;
            }
        }
        System.out.println(res % mod);
    }

    public static void distribute(long[][] dp, int idx, int num, int m) {
        if (num != m) {
            dp[idx + 1][num + 1] += dp[idx][num];
        }
        if (num != 1) {
            dp[idx + 1][num - 1] += dp[idx][num];
        }
        dp[idx + 1][num] += dp[idx][num];
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
