import java.io.InputStream;
import java.util.*;

public class courseschedule {
    public static void main(String[] args) throws Exception {
        //Scanner sc = new Scanner(System.in);
        FastIO sc = new FastIO(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        ArrayList<ArrayList<Integer>> graph = buildDirectedGraph(n, m, sc);
        int[] visited = new int[n + 1];
        ArrayList<Integer> order = new ArrayList<>();
        boolean hasCycle = false;
        for (int i = 1; i <= n && !hasCycle; i++) {
            if (visited[i] == 0) {
                hasCycle = !dfs(graph, visited, i, order);
            }
        }
        if (hasCycle) {
            System.out.println("IMPOSSIBLE");
        } else {
            StringBuilder sb = new StringBuilder();
            for (int i = order.size() - 1; i >= 0; i--) {
                sb.append(order.get(i)).append(" ");
            }
            sb.deleteCharAt(sb.length() - 1);
            System.out.println(sb);
        }
    }

    public static boolean dfs(ArrayList<ArrayList<Integer>> graph, int[] visited, int u, ArrayList<Integer> order) {
        if (visited[u] == 2) {
            return true;
        } else if (visited[u] == 1) {
            return false;
        }
        visited[u] = 1;
        for (Integer v: graph.get(u)) {
            boolean res = dfs(graph, visited, v, order);
            if (!res) {
                return false;
            }
        }
        visited[u] = 2;
        order.add(u);
        return true;
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
