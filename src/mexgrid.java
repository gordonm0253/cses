import java.io.InputStream;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Random;
import java.util.Scanner;

public class mexgrid {
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[][] grid = new int[n][n];
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i<n; i++) {
            grid[0][i] = i;
            grid[i][0] = i;
        }
        for (int i = 1; i<n; i++) {
            int[] ct = new int[128];
            ct[i]++;
            for (int j = 1; j<n; j++) {
                for (int prevR = 0; prevR < i; prevR++) {
                    ct[grid[prevR][j]]++;
                }
                int ptr = 0;
                while (ct[ptr] > 0) {
                    ptr++;
                }
                ct[ptr]++;
                grid[i][j] = ptr;
                for (int prevR = 0; prevR < i; prevR++) {
                    ct[grid[prevR][j]]--;
                }
            }
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n - 1; j++) {
                sb.append(grid[i][j]).append(" ");
            }
            sb.append(grid[i][n - 1]).append("\n");
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
