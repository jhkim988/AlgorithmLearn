import java.io.*;
import java.util.*;

public class BOJ14606 {
    private static int[] dp;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int n = Integer.parseInt(br.readLine());
        dp = new int[n+1];
        Arrays.fill(dp, -1);
        bw.write(Integer.toString(recur(n)));
        bw.flush();
    }

    private static int recur(int n) {
        if (dp[n] != -1) return dp[n];
        
        int ret = 0;
        for (int i = 1; i <= n/2; i++) {
            ret = Integer.max(ret, i*(n-i) + recur(i) + recur(n-i));
        }

        return dp[n] = ret;
    }
}
