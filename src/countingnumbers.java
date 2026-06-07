import java.io.InputStream;
import java.util.*;

public class countingnumbers {
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        long a = sc.nextLong();
        long b = sc.nextLong();
        long ctB = countDigits(b);
        long ctA = countDigits(a - 1);
        System.out.println(ctB - ctA);
    }

    public static long countDigits(long num) {
        if (num == -1) return 0;
        if (num <= 10) return num + 1;
        int[] arr = new int[19];
        int ptr = 0;
        while (num > 0) {
            arr[ptr] = (int) (num % 10);
            num /= 10;
            ptr++;
        }
        int l = 0;
        int r = ptr - 1;
        while (l < r) {
            int temp = arr[l];
            arr[l] = arr[r];
            arr[r] = temp;
            l++;
            r--;
        }
        //System.out.println(Arrays.toString(arr));
        long[][][][] dp = new long[10][ptr][2][2]; // digit, place, tight, start
        dp[0][0][0][0] = 1;
        for (int i = 1; i < arr[0]; i++) {
            dp[i][0][0][1] = 1;
        }
        dp[arr[0]][0][1][1] = 1;
        for (int i = 1; i < ptr; i++) {
            // tight bound
            for (int j = 0; j < arr[i]; j++) {
                if (j == arr[i - 1]) continue;
                dp[j][i][0][1] += dp[arr[i - 1]][i - 1][1][1];
            }
            if (arr[i] != arr[i - 1]) {
                dp[arr[i]][i][1][1] += dp[arr[i - 1]][i - 1][1][1];
            }
            // start
            dp[0][i][0][0] += dp[0][i - 1][0][0];
            for (int j = 1; j < 10; j++) {
                dp[j][i][0][1] += dp[0][i - 1][0][0];
            }
            // non-tight bound
            long sum = 0;
            for (int j = 0; j < 10; j++) {
                sum += dp[j][i - 1][0][1];
            }
            //System.out.println(sum);
            for (int j = 0; j < 10; j++) {
                dp[j][i][0][1] += (sum - dp[j][i - 1][0][1]);
            }
        }
        long total = 0;
        for (int i = 0; i < 10; i++) {
            total += dp[i][ptr - 1][0][0] + dp[i][ptr - 1][1][0] + dp[i][ptr - 1][0][1] + dp[i][ptr - 1][1][1];
        }
        return total;
    }

    public static ArrayList<ArrayList<Integer>> buildGraph(int n, int m, FastIO sc, boolean directed) throws Exception {
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

    public static ArrayList<ArrayList<Integer>> buildDirectedGraph(int n, int m, FastIO sc) throws Exception {
        return buildGraph(n, m, sc, true);
    }

    public static ArrayList<ArrayList<Integer>> buildUndirectedGraph(int n, int m, FastIO sc) throws Exception {
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
