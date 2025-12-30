import java.io.InputStream;
import java.util.*;

public class messageroute {
    public static void main(String[] args) throws Exception {
        //Scanner sc = new Scanner(System.in);
        FastIO sc = new FastIO(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        ArrayList<ArrayList<Integer>> graph = new ArrayList<>();
        for (int i = 0; i <= n; i++) {
            graph.add(new ArrayList<>());
        }
        for (int i = 0; i < m; i++) {
            int u = sc.nextInt();
            int v = sc.nextInt();
            graph.get(u).add(v);
            graph.get(v).add(u);
        }
        bfs(graph, n);
    }

    public static void bfs(ArrayList<ArrayList<Integer>> graph, int n) {
        Queue<Integer> q = new LinkedList<>();
        q.add(1);
        boolean[] visited = new boolean[n + 1];
        int[] prevMove = new int[n + 1];
        visited[1] = true;
        while (!q.isEmpty()) {
            int u = q.poll();
            if (u == n) {
                int curr_node = n;
                ArrayList<Integer> path = new ArrayList<>();
                while (curr_node != 1) {
                    path.add(curr_node);
                    curr_node = prevMove[curr_node];
                }
                path.add(1);
                System.out.println(path.size());
                StringBuilder sb = new StringBuilder();
                for (int i = path.size() - 1; i >= 0; i--) {
                    sb.append(path.get(i));
                    sb.append(" ");
                }
                sb.deleteCharAt(sb.length() - 1);
                System.out.println(sb);
                return;
            }
            for (Integer v: graph.get(u)) {
                if (!visited[v]) {
                    visited[v] = true;
                    prevMove[v] = u;
                    q.add(v);
                }
            }
        }
        System.out.println("IMPOSSIBLE");
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
