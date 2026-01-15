import java.io.InputStream;
import java.util.*;

public class roomallocation {
    public static void main(String[] args) throws Exception {
        //Scanner sc = new Scanner(System.in);
        FastIO sc = new FastIO(System.in);
        PriorityQueue<Event> currentStaying = new PriorityQueue<>((a, b) -> (a.end - b.end));
        Queue<Integer> availableRooms = new PriorityQueue<>();
        availableRooms.add(1);
        int n = sc.nextInt();
        int usedRooms = 1;
        int[] rooms = new int[n];
        Event[] customers = new Event[n];
        for (int i = 0; i < n; i++) {
            customers[i] = new Event(sc.nextInt(), sc.nextInt(), i);
        }
        Arrays.sort(customers, (a, b) -> (a.start - b.start));
        for (Event e: customers) {
            int time = e.start;
            while (!currentStaying.isEmpty() && currentStaying.peek().end < time) {
                // customer leaves
                Event evt = currentStaying.poll();
                int roomNumber = rooms[evt.idx];
                availableRooms.add(roomNumber);
            }
            if (availableRooms.isEmpty()) {
                usedRooms++;
                availableRooms.add(usedRooms);
            }
            int roomNumber = availableRooms.poll();
            currentStaying.add(e);
            rooms[e.idx] = roomNumber;
        }
        System.out.println(usedRooms);
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            sb.append(rooms[i]);
            if (i < n - 1) sb.append(" ");
        }
        System.out.println(sb);

    }

    public static class Event {
        int start;
        int end;
        int idx;
        public Event(int start, int end, int idx) {
            this.start = start;
            this.end = end;
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
