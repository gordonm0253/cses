import java.io.InputStream;
import java.util.*;

public class collectingnumbers2 {
    public static void main(String[] args) throws Exception {
        //Scanner sc = new Scanner(System.in);
        FastIO sc = new FastIO(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        int[] x = new int[n + 2];
        int[] positions = new int[n + 2];
        for (int i = 1; i <= n; i++) {
            int in = sc.nextInt();
            positions[in] = i;
            x[i] = in;
        }
        positions[n + 1] = n + 1;
        x[n + 1] = n + 1;
        int rounds = 1;
        int prevIdx = 0;
        for (int i = 1; i <= n; i++) {
            if (positions[i] < prevIdx) {
                rounds++;
            }
            prevIdx = positions[i];
        }
        StringBuilder sb = new StringBuilder();
        //System.out.println(rounds);
        //System.out.println(Arrays.toString(x));
        for (int t = m; t > 0; t--) {
            int a = sc.nextInt();
            int b = sc.nextInt();
            int lower = Math.min(a, b);
            int higher = Math.max(a, b);
            a = lower;
            b = higher;
            int numA = x[a];
            int numB = x[b];

            if (Math.abs(numA - numB) == 1) {
                int prevPos = positions[Math.min(numA, numB) - 1];
                int midLeft = positions[Math.min(numA, numB)];
                int midRight = positions[Math.max(numA, numB)];
                int nextPos = positions[Math.max(numA, numB) + 1];
                int[] arr = {0, prevPos, midLeft, midRight, nextPos};
                int[] arr2 = {0, prevPos, midRight, midLeft, nextPos};
                int prevRounds = countRounds(arr);
                int nextRounds = countRounds(arr2);
                rounds += (nextRounds - prevRounds);
            } else {
                int prevA = positions[numA - 1];
                int nextA = positions[numA + 1];
                int[] arrA1 = {0, prevA, a, nextA};
                int[] arrA2 = {0, prevA, b, nextA};

                int prevB = positions[numB - 1];
                int nextB = positions[numB + 1];
                int[] arrB1 = {0, prevB, b, nextB};
                int[] arrB2 = {0, prevB, a, nextB};

                rounds += (countRounds(arrA2) - countRounds(arrA1) + countRounds(arrB2) - countRounds(arrB1));
            }

            positions[numA] = b;
            positions[numB] = a;
            x[a] = numB;
            x[b] = numA;
            sb.append(rounds);
            if (t > 1) sb.append('\n');
        }
        System.out.println(sb);
    }

    public static int countRounds(int[] positions) {
        int rounds = 1;
        int prevIdx = -1;
        for (int i = 1; i <= positions.length - 1; i++) {
            if (positions[i] < prevIdx) {
                rounds++;
            }
            prevIdx = positions[i];
        }
        return rounds;
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
