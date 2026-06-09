import java.io.InputStream;
import java.util.*;

public class trafficlights {
    public static void main(String[] args) throws Exception {
        // Note - this approach times out in Java, so I translated it to C++
        FastIO sc = new FastIO(System.in);
        int x = sc.nextInt();
        StringBuilder sb = new StringBuilder();

        TreeSet<Integer> lights = new TreeSet<>();
        lights.add(0);
        lights.add(x);

        // segMap: length -> count (replaces both PQ and freqMap)
        TreeMap<Integer, Integer> segMap = new TreeMap<>();
        segMap.put(x, 1);
        int n = sc.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = sc.nextInt();
        }
        for (int i = 0; i < n; i++) {
            int p = arr[i];
            int right = lights.higher(p);
            int left = lights.lower(p);
            int prevSeg = right - left;

            int count = segMap.get(prevSeg);
            if (count == 1) segMap.remove(prevSeg);
            else segMap.put(prevSeg, count - 1);

            int newLeft = p - left;
            int newRight = right - p;
            segMap.merge(newLeft, 1, Integer::sum);
            segMap.merge(newRight, 1, Integer::sum);

            lights.add(p);

            if (i != 0) sb.append(' ');
            sb.append(segMap.lastKey());

        }
        System.out.println(sb);
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
