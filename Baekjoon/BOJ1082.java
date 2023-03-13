import java.io.*;
import java.util.*;

public class BOJ1082 {
    private static int n;
    private static int[] cost;
    private static String[] dp;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        n = Integer.parseInt(br.readLine());
        cost = new int[n];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            cost[i] = Integer.parseInt(st.nextToken());
        }

        int m = Integer.parseInt(br.readLine());
        dp = new String[m+1];
        String answer = recur(m);
        if (answer.length() == 0) answer = "0";
        bw.write(answer);
        bw.flush();
    }
    private static String recur(int money) {
        if (dp[money] != null) return dp[money];
        String ret = "";
        for (int i = n-1; i >= 0; i--) {
            if (money < cost[i]) continue;
            if (ret.length() == 0 && i == 0) continue;
            String val = recur(money - cost[i]) + i;
            if (compare(ret, val) < 0) ret = val;
        }
        return dp[money] = ret;
    }
    private static int compare(String a, String b) {
        if (a.length() != b.length()) return Integer.compare(a.length(), b.length());
        for (int i = 0; i < a.length(); i++) {
            if (a.charAt(i) != b.charAt(i)) return Character.compare(a.charAt(i), b.charAt(i));
        }
        return 0;
    }
}
