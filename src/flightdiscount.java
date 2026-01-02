import java.io.InputStream;
import java.util.*;

public class flightdiscount {
    public static void main(String[] args) throws Exception {
        //Scanner sc = new Scanner(System.in);
        FastIO sc = new FastIO(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        ArrayList<ArrayList<Edge>> graph = new ArrayList<>();
        for (int i = 0; i <= n; i++) {
            graph.add(new ArrayList<>());
        }
        for (int i = 0; i < m; i++) {
            int u = sc.nextInt();
            int v = sc.nextInt();
            int w = sc.nextInt();
            graph.get(u).add(new Edge(v, w));
        }
        System.out.println(findMinCost(n, graph));
    }
    public static long findMinCost(int n, ArrayList<ArrayList<Edge>> graph) {
        PriorityQueue<Node> pq = new PriorityQueue<>(Comparator.comparingLong(a -> a.cost));
        boolean[][] visited = new boolean[n + 1][2];
        pq.add(new Node(1, true, 0L));
        while (!pq.isEmpty()) {
            Node curr = pq.poll();
            int currNode = curr.node;
            //System.out.println(currNode + " " + curr.discount + " " + curr.cost);
            if (currNode == n) {
                return curr.cost;
            }
            int idx = curr.discount ? 0: 1;
            if (visited[currNode][idx]) {
                continue;
            }
            visited[currNode][idx] = true;
            for (Edge e: graph.get(currNode)) {
                long newCost = (long) e.weight + curr.cost;
                pq.add(new Node(e.v, curr.discount, newCost));
                if (curr.discount) {
                    long discountCost = (long) (e.weight / 2) + curr.cost;
                    pq.add(new Node(e.v, false, discountCost));
                }
            }
        }
        return -1;
    }
    public static class Node {
        int node;
        boolean discount;
        long cost;
        public Node(int node, boolean discount, long cost) {
            this.node = node;
            this.discount = discount;
            this.cost = cost;
        }
    }
    public static class Edge {
        int v;
        int weight;
        public Edge(int v, int weight) {
            this.v = v;
            this.weight = weight;
        }
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
