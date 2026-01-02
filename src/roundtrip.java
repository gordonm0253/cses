import java.io.InputStream;
import java.util.*;

public class roundtrip {
    public static void main(String[] args) throws Exception {
        //Scanner sc = new Scanner(System.in);
        FastIO sc = new FastIO(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        ArrayList<ArrayList<Integer>> graph = buildUndirectedGraph(n, m, sc);
        boolean found = false;
        boolean[] visited = new boolean[n + 1];
        for (int i = 1; i <= n && !found; i++) {
            if (!visited[i]) {
                StringBuilder sb = new StringBuilder();
                found = dfs(graph, visited, i, n, sb);
                if (found) {
                    String str = sb.toString();
                    String[] split = str.split(" ");
                    HashMap<String, Integer> map = new HashMap<>();
                    boolean foundMatching = false;
                    int i1 = 0;
                    int i2 = 0;
                    for (int j = 0; j < split.length && !foundMatching; j++) {
                        if (map.containsKey(split[j])) {
                            i1 = map.get(split[j]);
                            i2 = j;
                            foundMatching = true;
                        }
                        map.put(split[j], j);
                    }
                    if (!foundMatching) {
                        found = false;
                        continue;
                    }
                    StringBuilder finalStr = new StringBuilder();
                    int ct = 0;
                    for (int j = i1; j <= i2; j++) {
                        finalStr.append(split[j]).append(" ");
                        ct++;
                    }
                    finalStr.deleteCharAt(finalStr.length() - 1);
                    System.out.println(ct);
                    System.out.println(finalStr);
                }
            }
        }
        if (!found) {
            System.out.println("IMPOSSIBLE");
        }
    }

    public static boolean dfs(ArrayList<ArrayList<Integer>> graph, boolean[] visited, int curr, int prev, StringBuilder sb) {
        if (visited[curr]) {
            sb.append(curr);
            return true;
        }
        visited[curr] = true;
        for (Integer v: graph.get(curr)) {
            if (v != prev) {
                if (dfs(graph, visited, v, curr, sb)) {
                    sb.append(" ").append(curr);
                    return true;
                }
            }
        }
        return false;
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
