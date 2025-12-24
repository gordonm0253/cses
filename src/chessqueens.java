import java.io.InputStream;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class chessqueens {
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        //FastIO sc = new FastIO(System.in);
        char[][] board = new char[8][8];
        for (int i = 0; i < 8; i++) {
            String line = sc.nextLine();
            for (int j = 0; j < 8; j++) {
                board[i][j] = line.charAt(j);
            }
        }
        System.out.println(count(board, 0));
    }
    public static int count(char[][] board, int col) {
        if (col == 8 && check(board)) {
            return 1;
        }
        if (!check(board)) return 0;
        int ct = 0;
        for (int i = 0; i < 8; i++) {
            if (board[i][col] != '*') {
                board[i][col] = 'q';
                ct += count(board, col + 1);
                board[i][col] = '.';
            }
        }
        return ct;
    }
    public static boolean check(char[][] board) {
        int q = 0;
        int[] rows = new int[8];
        int[] cols = new int[8];
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (board[i][j] == 'q') {
                    rows[q] = i;
                    cols[q] = j;
                    q++;
                }
            }
        }
        for (int i = 0; i < q; i++) {
            for (int j = i + 1; j < q; j++) {
                int rowDiff = rows[j] - rows[i];
                int colDiff = cols[j] - cols[i];
                if (rowDiff == 0 || colDiff == 0 || Math.abs(rowDiff) == Math.abs(colDiff)) {
                    return false;
                }
            }
        }
        return true;
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
