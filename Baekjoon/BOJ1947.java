import java.io.*;

public class BOJ1947 {
    private static long d = 1_000_000_000;
    private static long[] dp;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int n = Integer.parseInt(br.readLine());
        dp = new long[n+1];
        bw.write(Long.toString(recur(n)));
        bw.flush();
    }
    private static long recur(int x) {
        if (x == 1) return dp[x] = 0;
        if (x == 2) return dp[x] = 1;
        if (dp[x] != 0) return dp[x];
        return dp[x] = (x-1) * ((recur(x-1) + recur(x-2)) % d) % d;
    }
}