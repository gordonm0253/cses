import java.util.Scanner;

public class permutations {
    public static void main(String[] args) {
        Scanner sc=  new Scanner(System.in);
        int n = sc.nextInt();
        if (n == 2 || n == 3) {
            System.out.println("NO SOLUTION");
        } else {
            if (n == 1) {
                System.out.println("1");
            } else {
                StringBuilder sb = new StringBuilder();
                if (n % 2 == 0) {
                    for (int i = n - 1; i>=1; i-=2) {
                        sb.append(i).append(" ");
                    }
                    for (int i = n; i > 2; i -= 2) {
                        sb.append(i).append(" ");
                    }
                    sb.append(2);
                } else {
                    for (int i = n - 1; i>=1; i-=2) {
                        sb.append(i).append(" ");
                    }
                    for (int i = n; i > 2; i -= 2) {
                        sb.append(i).append(" ");
                    }
                    sb.append(1);
                }
                System.out.println(sb.toString());
            }
        }
    }
}
