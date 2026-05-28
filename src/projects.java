import java.io.InputStream;
import java.util.*;

public class projects {
    public static void main(String[] args) throws Exception {
        //Scanner sc = new Scanner(System.in);
        FastIO sc = new FastIO(System.in);
        int n = sc.nextInt();
        int[][] events = new int[n + 1][3];
        for (int i = 1; i <= n; i++) {
            events[i][0] = sc.nextInt();
            events[i][1] = sc.nextInt();
            events[i][2] = sc.nextInt();
        }
        Arrays.sort(events, Comparator.comparingInt(a -> a[1]));
        long[] dp = new long[n + 1];
        dp[1] = events[1][2];
        for (int i = 2; i <= n; i++) {
            int prev = search(events, i);
            //System.out.println(prev);
            dp[i] = Math.max(dp[i - 1], dp[prev] + events[i][2]);
        }
        System.out.println(dp[n]);
    }

    public static int search(int[][] events, int idx) {
        int l = 1;
        int r = idx - 1;
        if (!disjoint(events, 1, idx)) return 0;
        while (l <= r) {
            int mid = l + (r - l) / 2;
            boolean workMid = disjoint(events, mid, idx);
            boolean workMid2 = disjoint(events, mid + 1, idx);
            if (workMid && !workMid2) {
                return mid;
            } else if (workMid && workMid2) {
                l = mid + 1;
            } else {
                r = mid - 1;
            }
        }
        return l;
    }

    public static boolean disjoint(int[][] events, int i1, int i2) {
        int s1 = events[i1][0];
        int e1 = events[i1][1];
        int s2 = events[i2][0];
        int e2 = events[i2][1];
        //System.out.println(i1 + " " + i2 + " " + !(e1 < s2 || (e2 < s1)));
        return (e1 < s2 || e2 < s1);
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
