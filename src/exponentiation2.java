import java.io.InputStream;
import java.util.*;

public class exponentiation2 {
    public static void main(String[] args) throws Exception {
        FastIO sc = new FastIO(System.in);
        int mod = 1000000007;
        StringBuilder sb = new StringBuilder();
        for (int t = sc.nextInt(); t > 0; t--) {
            int a = sc.nextInt();
            int b = sc.nextInt();
            int c = sc.nextInt();

            long ans;
            if (a == 0) {
                // a^anything is 0, unless the exponent b^c is 0 (i.e. a^0 = 1)
                boolean exponentIsZero = (b == 0 && c != 0);
                ans = exponentIsZero ? 1 : 0;
            } else {
                // exponent = b^c mod (mod-1), by Fermat's little theorem
                long bc = exponent(mod - 1, c, b);
                ans = exponent(mod, (int) bc, a);
            }

            sb.append(ans);
            if (t > 1) sb.append('\n');
        }
        System.out.println(sb);
    }

    // computes exp^c mod mod  (note: c is the exponent, exp is the base)
    public static long exponent(int mod, int c, long exp) {
        long res = 1;
        exp %= mod;
        while (c > 0) {
            if (c % 2 == 1) {
                res *= exp;
                res %= mod;
            }
            exp *= exp;
            exp %= mod;
            c /= 2;
        }
        return res;
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
