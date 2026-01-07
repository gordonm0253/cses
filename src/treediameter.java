import java.io.InputStream;
import java.util.*;

public class treediameter {
    public static void main(String[] args) throws Exception {
        //Scanner sc = new Scanner(System.in);
        FastIO sc = new FastIO(System.in);
        int n = sc.nextInt();
        ArrayList<ArrayList<Integer>> graph = new ArrayList<>();
        for (int i = 0; i <= n; i++) {
            graph.add(new ArrayList<>());
        }
        for (int i = 0; i < n - 1; i++) {
            int u = sc.nextInt();
            int v = sc.nextInt();
            graph.get(u).add(v);
            graph.get(v).add(u);
        }
        int[] visited = new int[n + 1];
        int[] depth = new int[n + 1];
        int max = 0;
        Stack<Integer> stack = new Stack<>();
        stack.push(1);
        while (!stack.isEmpty()) {
            int currNode = stack.pop();
            ArrayList<Integer> children = graph.get(currNode);
            if (visited[currNode] == 0) {
                visited[currNode] = 1;
                stack.push(currNode);
                for (int child: children) {
                    if (visited[child] != 0) continue;
                    stack.push(child);
                }
            } else {
                int maxDepth = -1;
                for (int child: children) {
                    if (visited[child] != 2) continue;
                    if (maxDepth == -1) {
                        maxDepth = depth[child];
                    } else {
                        max = Math.max(max, maxDepth + depth[child]);
                        maxDepth = Math.max(maxDepth, depth[child]);
                    }
                }
                if (maxDepth == -1) maxDepth = 0;
                depth[currNode] = 1 + maxDepth;
                max = Math.max(depth[currNode] - 1, max);
                visited[currNode] = 2;
            }
        }
        System.out.println(max);
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
