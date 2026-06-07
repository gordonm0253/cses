import java.io.InputStream;
import java.util.*;

public class elevatorrides {
    public static void main(String[] args) throws Exception {
        //Scanner sc = new Scanner(System.in);
        FastIO sc = new FastIO(System.in);
        int n = sc.nextInt();
        int x = sc.nextInt();
        int[] w = new int[n];
        for (int i = 0; i < n; i++) {
            w[i] = sc.nextInt();
        }
        int[] lastWeight = new int[1 << n];
        int[] minRide = new int[1 << n];
        Arrays.fill(minRide, Integer.MAX_VALUE);
        Arrays.fill(lastWeight, Integer.MAX_VALUE);
        for (int i = 0; i < n; i++) {
            minRide[1 << i] = 1;
            lastWeight[1 << i] = w[i];
        }
        ArrayList<ArrayList<Integer>> countBits = new ArrayList<>();
        for (int i = 0; i <= n; i++) {
            countBits.add(new ArrayList<>());
        }
        for (int i = 1; i < (1 << n); i++) {
            int bits = bitCount(i);
            countBits.get(bits).add(i);
        }
        for (int i = 1; i < n; i++) {
            for (Integer mask: countBits.get(i))  {
                int currentRides = minRide[mask];
                int currentWeight = lastWeight[mask];
                for (int j = 0; j < n; j++) {
                    int person = 1 << j;
                    int weight = w[j];
                    if ((mask & person) == 0) {
                        int newMask = mask + person;
                        if (currentWeight + weight <= x) {
                            if (currentRides < minRide[newMask]) {
                                minRide[newMask] = currentRides;
                                lastWeight[newMask] = currentWeight + weight;
                            } else if (currentRides == minRide[newMask] && currentWeight + weight <= lastWeight[newMask]) {
                                lastWeight[newMask] = currentWeight + weight;
                            }
                        } else {
                            if (currentRides + 1 < minRide[newMask]) {
                                minRide[newMask] = currentRides + 1;
                                lastWeight[newMask] = weight;
                            } else if (currentRides + 1 == minRide[newMask] && weight <= lastWeight[newMask]) {
                                lastWeight[newMask] = weight;
                            }
                        }
                    }
                }
            }
        }
        System.out.println(minRide[(1 << n) - 1]);
    }

    public static int bitCount(int num) {
        int ct = 0;
        while (num > 0) {
            if ((num & 1) != 0) ct++;
            num /= 2;
        }
        return ct;
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
