import java.io.*;

public class BOJ20500 {
    private static final int d = 1_000_000_007;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int n = Integer.parseInt(br.readLine());
        int[][] dp = new int[n+1][15];
        dp[1][1] = dp[1][5] = 1;
        int exp1 = 100 % 15;
        int exp2 = 500 % 15;
        for (int i = 2; i <= n; i++) {
            for (int j = 0; j < 15; j++) {
                dp[i][j] = dp[i-1][(exp1-j+15)%15] + dp[i-1][(exp2-j+15)%15];
                dp[i][j] %= d;
            }
            exp1 *= 10; exp1 %= 15;
            exp2 *= 10; exp2 %= 15;
        }

        bw.write(Integer.toString(dp[n][0]));
        bw.flush();
    }
}