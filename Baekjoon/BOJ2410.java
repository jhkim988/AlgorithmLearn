import java.io.*;

public class BOJ2410 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int n = Integer.parseInt(br.readLine());
        final int D = 1_000_000_000;

        int[] dp = new int[n+1];
        dp[0] = 1;
        for (int i = 0; (1<<i) <= n; i++) {
            for (int j = 1<<i; j <= n; j++) {
                dp[j] = (dp[j] + dp[j-(1<<i)])%D;
            }
        }

        bw.write(Integer.toString(dp[n]));
        bw.flush();
    }
}