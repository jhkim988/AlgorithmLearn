import java.io.*;

public class BOJ1670 {
    private static final long d = 987_654_321;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int n = Integer.parseInt(br.readLine());
        long[] dp = new long[n+1];
        dp[0] = 1;
        dp[2] = 1;
        for (int i = 4; i <= n; i += 2) {
            dp[i] += 2 * dp[i-2] % d;
            dp[i] %= d;
            for (int j = 2; j < i-2; j += 2) {
                dp[i] += dp[j]*dp[i-j-2] % d; 
                dp[i] %= d;
            }
        }

        bw.write(Long.toString(dp[n]));
        bw.flush();
    }
}
