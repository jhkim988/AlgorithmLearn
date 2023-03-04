import java.io.*;
import java.util.*;

public class BOJ13302 {
    private static int n, m;
    private static boolean[] noResort;
    private static int[][] dp;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        noResort = new boolean[n+1];
        dp = new int[n+1][n+1];
        if (m > 0) {
            st = new StringTokenizer(br.readLine());
            while (m-- > 0) {
                noResort[Integer.parseInt(st.nextToken())] = true;
            }    
        }
        int answer = recur(1, 0);
        bw.write(Integer.toString(answer));
        bw.flush();
    }
    private static int recur(int day, int nCoupon) {
        if (day > n) return 0;
        if (dp[day][nCoupon] != 0) return dp[day][nCoupon];
        if (noResort[day]) return dp[day][nCoupon] = recur(day+1, nCoupon);
        int ret = recur(day+1, nCoupon) + 10_000;
        ret = Integer.min(ret, recur(day+3, nCoupon+1) + 25_000);
        ret = Integer.min(ret, recur(day+5, nCoupon+2) + 37_000);
        if (nCoupon >= 3) ret = Integer.min(ret, recur(day+1, nCoupon-3));
        return dp[day][nCoupon] = ret;
    }
}
