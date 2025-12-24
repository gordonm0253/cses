import java.util.*;

public class creatingstring {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String s = sc.nextLine();
        int n = s.length();
        int[] freq = new int[26];
        for (int i = 0; i < s.length(); i++) {
            freq[s.charAt(i) - 'a']++;
        }
        ArrayList<String> arr = new ArrayList<>();
        dfs(n, new StringBuilder(), freq, arr);
        StringBuilder sb = new StringBuilder();
        sb.append(arr.size());
        for (String string : arr) {
            sb.append('\n').append(string);
        }
        System.out.println(sb);
    }
    public static void dfs(int n, StringBuilder sb, int[] freq, ArrayList<String> arr) {
        //System.out.println(sb);
        if (sb.length() == n) {
            arr.add(sb.toString());
            return;
        }
        for (int i = 0; i<freq.length; i++) {
            if (freq[i] > 0) {
                sb.append((char) ('a' + i));
                freq[i]--;
                dfs(n, sb, freq, arr);
                sb.deleteCharAt(sb.length() - 1);
                freq[i]++;
            }
        }
    }
}
