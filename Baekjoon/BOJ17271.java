import java.io.*;
import java.util.*;

public class BOJ17271 {
    private static long[] dp = new long[10_001];
    private static final int d = 1_000_000_007;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        Arrays.fill(dp, -1);

        bw.write(Long.toString(recur(n, m)));
        bw.newLine();
        bw.flush();
    }
    private static long recur(int n, int m) {
        if (n == 0) return 1;
        if (n < 0) return 0;
        if (dp[n] != -1) return dp[n];
        long ret = (recur(n-1, m) + recur(n-m, m))%d;
        return dp[n] = ret%d;
    }
}
