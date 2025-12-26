import java.io.InputStream;
import java.util.*;

public class buildingteams {
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
            int a = sc.nextInt();
            int b = sc.nextInt();
            graph.get(a).add(b);
            graph.get(b).add(a);
        }
        boolean works = twocoloring(graph, n);
        if (!works) {
            System.out.println("IMPOSSIBLE");
        }
    }

    public static boolean twocoloring(ArrayList<ArrayList<Integer>> graph, int n) {
        int[] colors = new int[n + 1];
        boolean[] visited = new boolean[n + 1];
        for (int i = 1; i <= n; i++) {
            if (colors[i] == 0) {
                colors[i] = 1;
                Queue<Integer> q = new LinkedList<>();
                q.add(i);
                while (!q.isEmpty()) {
                    int u = q.poll();
                    if (visited[u]) {
                        continue;
                    }
                    visited[u] = true;
                    int other = colors[u] ^ 3;
                    for (Integer v : graph.get(u)) {
                        if (colors[u] == colors[v]) {
                            return false;
                        }
                        colors[v] = other;
                        q.add(v);
                    }
                }
            }
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= n; i++) {
            sb.append(colors[i]).append(" ");
        }
        sb.deleteCharAt(sb.length() - 1);
        System.out.println(sb.toString());
        return true;
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
