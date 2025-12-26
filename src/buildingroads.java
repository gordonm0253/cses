import java.io.InputStream;
import java.util.*;

public class buildingroads {
    public static void main(String[] args) throws Exception {
        //Scanner sc = new Scanner(System.in);
        FastIO sc = new FastIO(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        ArrayList<ArrayList<Integer>> graph = new ArrayList<>();
        int[] connectedComponents = new int[n + 1];
        for (int i = 0; i <= n; i++) {
            graph.add(new ArrayList<>());
        }
        for (int i = 0; i < m; i++) {
            int u = sc.nextInt();
            int v = sc.nextInt();
            graph.get(u).add(v);
            graph.get(v).add(u);
        }
        int ct = 0;
        for (int i = 1; i <= n; i++) {
            if (connectedComponents[i] == 0) {
                ct++;
                Queue<Integer> q = new LinkedList<>();
                q.add(i);
                connectedComponents[i] = ct;
                while (!q.isEmpty()) {
                    int u = q.poll();
                    for (int v : graph.get(u)) {
                        if (connectedComponents[v] == 0) {
                            connectedComponents[v] = ct;
                            q.add(v);
                        }
                    }
                }
            }
        }
        //System.out.println(Arrays.toString(connectedComponents));
        StringBuilder sb = new StringBuilder();
        sb.append(ct - 1);
        int curr = 2;
        for (int i = 1; curr <= ct && i <= n; i++) {
            if (connectedComponents[i] == curr) {
                sb.append('\n').append(1).append(" ").append(i);
                curr++;
            }
        }
        System.out.println(sb);
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
