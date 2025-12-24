import java.util.Scanner;

public class weirdalgo {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        long n = sc.nextInt();
        StringBuilder sb = new StringBuilder();
        while (n > 1) {
            sb.append(n).append(" ");
            if (n % 2 == 0) {
                n /= 2;
            } else {
                n = 3 * n + 1;
            }
        }
        sb.append(1);
        System.out.println(sb.toString());
    }
}
