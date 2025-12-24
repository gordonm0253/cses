import java.io.InputStream;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class palindromereorder {
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        String s = sc.nextLine();
        int[] freq = new int[26];
        for (char c: s.toCharArray()) {
            freq[c - 'A']++;
        }
        int oddCt = 0;
        int odd = -1;
        for (int i = 0; i<26; i++) {
            if (freq[i] % 2 == 1) {
                oddCt++;
                odd = i;
            }
        }
        if (oddCt > 1) {
            System.out.println("NO SOLUTION");
        } else {
            StringBuilder sb = new StringBuilder();

            for (int i = 0; i<26; i++) {
                char c = (char) ('A' + i);
                for (int j = 0; j<freq[i] / 2; j++) {
                    sb.append(c);
                }
            }
            StringBuilder reverse = new StringBuilder(sb).reverse();
            if (oddCt == 1) {
                char oddC = (char) ('A' + odd);
                reverse.append(oddC);
            }
            reverse.append(sb);
            System.out.println(reverse);
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
