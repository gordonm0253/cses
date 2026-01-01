import java.io.InputStream;
import java.util.*;

public class shortestroutes2 {

    public static void main(String[] args) throws Exception {
        //Scanner sc = new Scanner(System.in);
        FastIO sc = new FastIO(System.in);
        ArrayList<ArrayList<Edge>> graph = new ArrayList<>();
        int n = sc.nextInt();
        int m = sc.nextInt();
        int q = sc.nextInt();
        /*
        for (int i = 0; i <= n; i++) {
            graph.add(new ArrayList<>());
        }
        long[][] dist = new long[n + 1][n + 1];
        for (int i = 0; i < m; i++) {
            int u = sc.nextInt();
            int v = sc.nextInt();
            int w = sc.nextInt();
            graph.get(u).add(new Edge(v, w));
            graph.get(v).add(new Edge(u, w));
        }
        for (int i = 1; i <= n; i++) {
            runDjikstra(i, dist, graph);
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < q; i++) {
            int u = sc.nextInt();
            int v = sc.nextInt();
            if (dist[u][v] == Long.MAX_VALUE) {
                dist[u][v] = -1;
            }
            sb.append(dist[u][v]).append("\n");
        }
         */
        long maxValue = 10000000000000L;
        long[][] dist = new long[n + 1][n + 1];
        for (int i = 0; i <= n; i++) {
            Arrays.fill(dist[i], maxValue);
            dist[i][i] = 0;
        }
        for (int i = 0; i < m; i++) {
            int u = sc.nextInt();
            int v = sc.nextInt();
            int w = sc.nextInt();
            dist[u][v] = Math.min(dist[u][v], w);
            dist[v][u] = Math.min(dist[v][u], w);
        }
        //System.out.println();
        for (int k = 1; k <= n; k++) {
            for (int i = 1; i <= n; i++) {
                for (int j = 1; j <= n; j++) {
                    long val = dist[i][k] + dist[k][j];
                    if (dist[i][j] > val) {
                        dist[i][j] = val;
                    }
                }
                //System.out.println(Arrays.toString(dist[i]));
            }
            //System.out.println();
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < q; i++) {
            int u = sc.nextInt();
            int v = sc.nextInt();
            if (dist[u][v] == maxValue) {
                dist[u][v] = -1;
            }
            sb.append(dist[u][v]).append("\n");
        }
        sb.deleteCharAt(sb.length() - 1);
        System.out.println(sb);
    }
    public static void runDjikstra(int start, long[][] dist, ArrayList<ArrayList<Edge>> graph) {
        PriorityQueue<Node> pq = new PriorityQueue<>(Comparator.comparingLong(a -> a.dist));
        pq.add(new Node(start, 0));
        Arrays.fill(dist[start], Long.MAX_VALUE);
        while (!pq.isEmpty()) {
            Node node = pq.poll();
            int u = node.node;
            //System.out.println(node.node + " " + node.dist);
            if (dist[start][u] == Long.MAX_VALUE) {
                dist[start][u] = node.dist;
                for (Edge e: graph.get(u)) {
                    pq.add(new Node(e.v, (long) e.w + node.dist));
                }
            }
        }
    }
    public static class Node {
        int node;
        long dist;
        public Node(int node, long dist) {
            this.node = node;
            this.dist = dist;
        }
    }
    public static class Edge {
        int w;
        int v;
        public Edge(int v, int w) {
            this.v = v;
            this.w = w;
        }
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
