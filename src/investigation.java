import java.io.InputStream;
import java.util.*;

public class investigation {
    public static void main(String[] args) throws Exception {
        FastIO sc = new FastIO(System.in);
        int mod = 1000000007;
        int n = sc.nextInt();
        int m = sc.nextInt();

        ArrayList<ArrayList<long[]>> graph = new ArrayList<>(); // {to, cost}
        for (int i = 0; i <= n; i++) graph.add(new ArrayList<>());
        int[] ea = new int[m], eb = new int[m], ec = new int[m];
        for (int i = 0; i < m; i++) {
            int a = sc.nextInt(), b = sc.nextInt(), c = sc.nextInt();
            ea[i] = a; eb[i] = b; ec[i] = c;
            graph.get(a).add(new long[]{b, c});
        }

        // Phase 1: Dijkstra for dist[]
        long[] dist = new long[n + 1];
        Arrays.fill(dist, Long.MAX_VALUE);
        dist[1] = 0;
        PriorityQueue<long[]> pq = new PriorityQueue<>((x, y) -> Long.compare(x[1], y[1]));
        pq.add(new long[]{1, 0});
        boolean[] done = new boolean[n + 1];
        while (!pq.isEmpty()) {
            long[] cur = pq.poll();
            int u = (int) cur[0];
            long du = cur[1];
            if (done[u]) continue;
            done[u] = true;
            for (long[] e : graph.get(u)) {
                int v = (int) e[0];
                long nd = du + e[1];
                if (nd < dist[v]) {
                    dist[v] = nd;
                    pq.add(new long[]{v, nd});
                }
            }
        }

        // Phase 2: filter to shortest-path DAG, then Kahn's algorithm DP
        ArrayList<ArrayList<Integer>> dag = new ArrayList<>();
        int[] indeg = new int[n + 1];
        for (int i = 0; i <= n; i++) dag.add(new ArrayList<>());
        for (int i = 0; i < m; i++) {
            int a = ea[i], b = eb[i], c = ec[i];
            if (dist[a] != Long.MAX_VALUE && dist[a] + c == dist[b]) {
                dag.get(a).add(b);
                indeg[b]++;
            }
        }

        long[] ways = new long[n + 1];
        long[] minE = new long[n + 1];
        long[] maxE = new long[n + 1];
        Arrays.fill(minE, Long.MAX_VALUE);
        ways[1] = 1;
        minE[1] = 0;
        maxE[1] = 0;

        ArrayDeque<Integer> q = new ArrayDeque<>();
        q.add(1); // indeg[1] is always 0 since c >= 1
        while (!q.isEmpty()) {
            int u = q.poll();
            for (int v : dag.get(u)) {
                ways[v] = (ways[v] + ways[u]) % mod;
                minE[v] = Math.min(minE[v], minE[u] + 1);
                maxE[v] = Math.max(maxE[v], maxE[u] + 1);
                if (--indeg[v] == 0) q.add(v);
            }
        }

        System.out.println(dist[n] + " " + ways[n] + " " + minE[n] + " " + maxE[n]);
    }

    public static class FastIO {
        InputStream dis;
        byte[] buffer = new byte[1 << 17];
        int pointer = 0;

        public FastIO(InputStream is) { dis = is; }

        int nextInt() throws Exception {
            int ret = 0;
            byte b;
            do { b = nextByte(); } while (b <= ' ');
            boolean negative = false;
            if (b == '-') { negative = true; b = nextByte(); }
            while (b >= '0' && b <= '9') { ret = 10 * ret + b - '0'; b = nextByte(); }
            return negative ? -ret : ret;
        }

        byte nextByte() throws Exception {
            if (pointer == buffer.length) { dis.read(buffer, 0, buffer.length); pointer = 0; }
            return buffer[pointer++];
        }
    }
}