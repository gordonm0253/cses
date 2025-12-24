import java.io.InputStream;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class numberspiral {
    public static void main(String[] args) throws Exception {
        //Scanner sc = new Scanner(System.in);
        FastIO sc = new FastIO(System.in);
        for (int t = sc.nextInt(); t > 0; t--) {
            int y = sc.nextInt();
            int x = sc.nextInt();
            if (x == 1 && y == 1) {
                System.out.println(1);
            } else {
                long ring = Math.max(x, y);
                long ring0 = (ring - 1) * (ring - 1);
                ring0++;
                long ring1 = ring * ring;
                // [ring0, ..., ring1]
                boolean down = (ring - 1) % 2 == 0;
                if (x == y) {
                    long mid = ring0 + (ring1 - ring0) / 2L;
                    System.out.println(mid);
                } else if ((down && x > y) || (!down && x < y)) {
                    long ans = ring1 - Math.min(x, y) + 1;
                    System.out.println(ans);
                } else {
                    long ans = ring0 + Math.min(x, y) - 1;
                    System.out.println(ans);
                }
            }

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
