import java.io.*;

public class BOJ1563 {
    private static int n;
    private static final int MOD = 1_000_000;
    private static int[][][] dp;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        n = Integer.parseInt(br.readLine());
        dp = new int[n][2][3];
        bw.write(Integer.toString(recur(0, 0, 0)));
        bw.flush();
    }
    private static int recur(int date, int late, int absent) {
        if (late >= 2 || absent >= 3) return 0;
        if (date == n) return 1;
        if (dp[date][late][absent] != 0) return dp[date][late][absent];
        int ret = 0;
        ret = recur(date+1, late, 0) + recur(date+1, late+1, 0) + recur(date+1, late, absent+1);
        ret %= MOD;

        return dp[date][late][absent] = ret;
    }
}