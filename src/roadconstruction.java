import java.io.InputStream;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class roadconstruction {
    public static void main(String[] args) throws Exception {
        //Scanner sc = new Scanner(System.in);
        FastIO sc = new FastIO(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        int[] dsu = new int[n + 1];
        int[] size = new int[n + 1];
        int max = 1;
        int connected = n;
        for (int i = 1; i<=n; i++) {
            dsu[i] = i;
            size[i] = 1;
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < m; i++) {
            int a = sc.nextInt();
            int b = sc.nextInt();
            int parentA = findParent(dsu, a);
            int parentB = findParent(dsu, b);
            if (parentA != parentB) {
                dsu[parentA] = parentB;
                size[parentB] += size[parentA];
                max = Math.max(max, size[parentB]);
                connected--;
            }
            sb.append(connected).append(' ').append(max).append('\n');
        }
        System.out.println(sb);
    }

    public static int findParent(int[] dsu, int node) {
        if (dsu[node] != node) {
            dsu[node] = findParent(dsu, dsu[node]);
        }
        return dsu[node];
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
