import java.io.InputStream;
import java.util.*;

public class gridpathdescription {
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        //FastIO sc = new FastIO(System.in);
        for (int t = sc.nextInt(); t > 0; t--) {

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
