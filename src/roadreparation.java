import java.io.InputStream;
import java.util.*;

public class roadreparation {
    public static void main(String[] args) throws Exception {
        //Scanner sc = new Scanner(System.in);
        FastIO sc = new FastIO(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        ArrayList<ArrayList<Edge>> graph = new ArrayList<>();
        for (int i = 0; i <= n; i++) {
            graph.add(new ArrayList<>());
        }
        PriorityQueue<Edge> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a.c));
        for (int i = 0; i < m; i++) {
            int a = sc.nextInt();
            int b = sc.nextInt();
            int temp = Math.min(a, b);
            b = Math.max(a, b);
            a = temp;
            int c = sc.nextInt();
            Edge e1 = new Edge(a, b, c);
            Edge e2 = new Edge(b, a, c);
            graph.get(a).add(e1);
            graph.get(b).add(e2);
            if (a == 1) {
                pq.add(e1);
            }
        }
        boolean[] visited = new boolean[n + 1];
        visited[1] = true;
        int ct = 1;
        long cost = 0;
        //System.out.println();
        while (!pq.isEmpty() && ct < n) {
            Edge e = pq.poll();
            int a = e.a;
            int b = e.b;
            int c = e.c;
            //System.out.println(a + " " + b + " " + c);
            if (visited[a] && visited[b]) {
                continue;
            }
            int unvisited = a;
            if (!visited[b]) {
                unvisited = b;
            }
            visited[a] = true;
            visited[b] = true;
            cost += c;
            ct++;
            pq.addAll(graph.get(unvisited));
        }
        if (ct < n) {
            System.out.println("IMPOSSIBLE");
        } else {
            System.out.println(cost);
        }
    }

    public static class Edge {
        int a;
        int b;
        int c;
        public Edge(int a, int b, int c) {
            this.a = a;
            this.b = b;
            this.c = c;
        }
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
