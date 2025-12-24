import java.io.InputStream;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class raabgame {
    public static void main(String[] args) throws Exception {
        //Scanner sc = new Scanner(System.in);
        FastIO sc = new FastIO(System.in);
        for (int t = sc.nextInt(); t > 0; t--) {
            int n = sc.nextInt();
            int a = sc.nextInt();
            int b = sc.nextInt();
            if (((a == 0 || b == 0) && a != b) || a + b > n) {
                System.out.println("NO");
            } else {
                System.out.println("YES");
                System.out.println(getN(n));
                int[] arr = new int[n + 1];
                int ties = n - a - b;
                boolean[] hash = new boolean[n + 1];
                for (int i = a + 1; i <= a + ties; i++) {
                    arr[i] = n - i + 1;
                    hash[n - i + 1] = true;
                }
                int pointer = n;
                for (int i = ties + a + 1; i<=n; i++) {
                    while (hash[pointer]) {
                        pointer--;
                    }
                    arr[i] = pointer;
                    hash[pointer] = true;
                }
                for (int i = 1; i <= a; i++) {
                    while (hash[pointer]) {
                        pointer--;
                    }
                    arr[i] = pointer;
                    hash[pointer] = true;
                }
                StringBuilder sb = new StringBuilder();
                for (int i = 1; i<n; i++) {
                    sb.append(arr[i]).append(" ");
                }
                sb.append(arr[n]);
                System.out.println(sb);
            }
        }
    }
    public static String getN(int n) {
        StringBuilder sbA = new StringBuilder();
        for (int i = n; i > 1; i--) {
            sbA.append(i).append(' ');
        }
        sbA.append(1);
        return sbA.toString();
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
