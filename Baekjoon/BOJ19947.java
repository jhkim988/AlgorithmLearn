import java.io.*;
import java.util.*;

public class BOJ19947 {
    private static int year;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        long init = Long.parseLong(st.nextToken());
        year = Integer.parseInt(st.nextToken());
        long max = 0;
        long[] dp = new long[year+1];
        dp[0] = init;
        for (int i = 1; i <= year; i++) {
            dp[i] = Long.max(dp[i], (long) (dp[i-1]*1.05));
            if (i >= 3) dp[i] = Long.max(dp[i], (long) (dp[i-3]*1.2));
            if (i >= 5) dp[i] = Long.max(dp[i], (long) (dp[i-5]*1.35));
            max = Long.max(dp[i], max);
        }
        bw.write(Long.toString(max));
        bw.flush();
    }
}
