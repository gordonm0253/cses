import java.io.InputStream;
import java.util.*;

public class sumofthreevalues {
    public static void main(String[] args) throws Exception {
        //Scanner sc = new Scanner(System.in);
        FastIO sc = new FastIO(System.in);
        int n = sc.nextInt();
        long x = sc.nextInt();
        Pair[] arr = new Pair[n];
        for (int i = 0; i < n; i++) {
            arr[i] = new Pair(sc.nextInt(), i);
        }
        Arrays.sort(arr, Comparator.comparingInt(a -> a.val));
        boolean found = false;
        for (int i = 0; i < n - 2 && !found; i++) {
            int l = i + 1;
            int r = n - 1;
            long sum = arr[i].val + arr[l].val + arr[r].val;
            while (l < r && sum != x) {
                if (sum > x) {
                    sum -= arr[r].val;
                    r--;
                    sum += arr[r].val;
                } else {
                    sum -= arr[l].val;
                    l++;
                    sum += arr[l].val;
                }
            }
            if (l != r) {
                found = true;
                StringBuilder sb = new StringBuilder();
                sb.append(arr[i].idx + 1).append(" ").append(arr[l].idx + 1).append(" ").append(arr[r].idx + 1);
                System.out.println(sb);
            }
        }
        if (!found) {
            System.out.println("IMPOSSIBLE");
        }
    }

    public static class Pair {
        int val;
        int idx;
        public Pair(int val, int idx) {
            this.val = val;
            this.idx = idx;
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
