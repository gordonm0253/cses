import java.io.InputStream;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class digitqueries {
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        //FastIO sc = new FastIO(System.in);

        long[][] intervals = new long[19][2];
        intervals[1][0] = 1;
        intervals[1][1] = 9;
        long pow = 90;
        for (int i = 2; i<=18; i++) {
            intervals[i][0] = intervals[i-1][1] + 1;
            intervals[i][1] = intervals[i][0] + (i * pow) - 1;
            pow *= 10;
        }
        intervals[18][1] = Long.MAX_VALUE;
        StringBuilder sb = new StringBuilder();
        for (int t = sc.nextInt(); t > 0; t--) {
            long k = sc.nextLong();
            int digits = findDigits(intervals, k);
            //System.out.println(k);
            long lower = intervals[digits][0];
            k -= lower;
            int place = (int) (k % digits);
            long number = (long) (Math.pow(10, digits - 1)) + (k / digits);
            //System.out.println(digits + " " + k + " " + place + " " + number);
            String str = Long.toString(number);
            sb.append(str.charAt(place)).append('\n');

        }
        System.out.println(sb);
    }

    public static int findDigits(long[][] intervals, long k) {
        int l = 1;
        int r = 18;
        while (l <= r) {
            int mid = l + (r - l)/2;
            if (intervals[mid][0] <= k && k <= intervals[mid][1]) {
                return mid;
            } else if (intervals[mid][0] > k) {
                r = mid - 1;
            } else {
                l = mid + 1;
            }
        }
        System.out.println("digit: " + l);
        System.out.println(k >= intervals[16][0]);
        System.out.println(k <= intervals[16][1]);
        return l;
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
