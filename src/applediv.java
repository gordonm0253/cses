import java.io.InputStream;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class applediv {
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        //FastIO sc = new FastIO(System.in);
        int n = sc.nextInt();
        long[] arr = new long[n];
        long sum = 0;
        for (int i = 0; i < n; i++) {
            arr[i] = sc.nextLong();
            sum += arr[i];
        }
        Arrays.sort(arr);
        long min = Long.MAX_VALUE;
        for (int i = 0; i < n; i++) {
            min = Math.min(min, findMin(arr, i + 1, sum, arr[i], n));
        }
        System.out.println(min);
    }
    public static long findMin(long[] arr, int position, long total, long curr, int n) {
        if (position >= n) return Math.abs((total - curr) - curr);
        long min = Math.abs(total - 2 * curr);
        for (int i = position; i < n; i++) {
            min = Math.min(min, findMin(arr, i + 1, total, curr + arr[i], n));
        }
        return min;
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
