import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class twosets {
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        //FastIO sc = new FastIO(System.in);
        long n = sc.nextLong();
        long sum = (n * (n + 1)) / 2;
        if (sum % 2 == 1) {
            System.out.println("NO");
        } else {
            StringBuilder sb = new StringBuilder("YES\n");
            ArrayList<Integer> l1 = new ArrayList<>();
            ArrayList<Integer> l2 = new ArrayList<>();
            int l = 1;
            int r = (int) n;
            boolean lst1 = true;
            if (n % 2 == 1) {
                l1.add(1);
                l1.add(2);
                l2.add(3);
                l = 4;
            }
            while (l < r) {
                if (lst1) {
                    l1.add(l);
                    l1.add(r);
                } else {
                    l2.add(l);
                    l2.add(r);
                }
                lst1 = !lst1;
                l++;
                r--;
            }
            sb.append(lstToString(l1)).append('\n').append(lstToString(l2));
            System.out.println(sb);
        }
    }
    public static StringBuilder lstToString(ArrayList<Integer> lst) {
        StringBuilder sb = new StringBuilder();
        sb.append(lst.size()).append('\n');
        StringBuilder sb1 = new StringBuilder();
        for (Integer i : lst) {
            sb1.append(' ').append(i);
        }
        return sb.append(sb1.substring(1));
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
