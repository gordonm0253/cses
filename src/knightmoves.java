import java.io.InputStream;
import java.util.*;

public class knightmoves {
    public static void main(String[] args) throws Exception {
        //Scanner sc = new Scanner(System.in);
        FastIO sc = new FastIO(System.in);
        int n =  sc.nextInt();
        int[][] grid = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                grid[i][j] = Integer.MAX_VALUE;
            }
        }
        Queue<Integer> r = new LinkedList<>();
        Queue<Integer> c = new LinkedList<>();
        int step = 0;
        r.add(0);
        c.add(0);
        while (!r.isEmpty()) {
            int size = r.size();
            for (int i = 0; i < size; i++) {
                int row = r.poll();
                int col = c.poll();
                if (grid[row][col] == Integer.MAX_VALUE) {
                    grid[row][col] = step;
                    if (row + 1 < n) {
                        if (col + 2 < n) {
                            r.add(row + 1);
                            c.add(col + 2);
                        }
                        if (col - 2 >= 0) {
                            r.add(row + 1);
                            c.add(col - 2);
                        }
                    }
                    if (row - 1 >= 0) {
                        if (col + 2 < n) {
                            r.add(row - 1);
                            c.add(col + 2);
                        }
                        if (col - 2 >= 0) {
                            r.add(row - 1);
                            c.add(col - 2);
                        }
                    }
                    if (row + 2 < n) {
                        if (col + 1 < n) {
                            r.add(row + 2);
                            c.add(col + 1);
                        }
                        if (col - 1 >= 0) {
                            r.add(row + 2);
                            c.add(col - 1);

                        }
                    }
                    if (row - 2 >= 0) {
                        if (col + 1 < n) {
                            r.add(row - 2);
                            c.add(col + 1);
                        }
                        if (col - 1 >= 0) {
                            r.add(row - 2);
                            c.add(col - 1);

                        }
                    }

                }
            }
            step++;
        }
        StringBuilder sb = new StringBuilder();
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
