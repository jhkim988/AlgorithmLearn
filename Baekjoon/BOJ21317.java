import java.io.*;
import java.util.*;

public class BOJ21317 {
    private static int n;
    private static int[][] cost;
    private static int[][] dp;
    private static int k;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        n = Integer.parseInt(br.readLine());
        cost = new int[2][n];
        for (int i = 0; i < n-1; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            cost[0][i] = Integer.parseInt(st.nextToken());
            cost[1][i] = Integer.parseInt(st.nextToken());
        }
        k = Integer.parseInt(br.readLine());
        dp = new int[2][n];

        bw.write(Integer.toString(recur(0, 1)));
        bw.flush();
    }
    private static int recur(int depth, int hasBigJump) {
        if (depth == n-1) return 0;
        if (depth > n-1) return Integer.MAX_VALUE >> 2;
        if (dp[hasBigJump][depth] != 0) return dp[hasBigJump][depth];
        dp[hasBigJump][depth] = Math.min(recur(depth + 1, hasBigJump) + cost[0][depth], recur(depth + 2, hasBigJump) + cost[1][depth]);
        if (hasBigJump != 0) dp[hasBigJump][depth] = Math.min(dp[hasBigJump][depth], recur(depth + 3, 0) + k);
        return dp[hasBigJump][depth];
    }
}