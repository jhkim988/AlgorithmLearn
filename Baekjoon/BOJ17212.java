import java.io.*;
import java.util.*;

public class BOJ17212 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int n = Integer.parseInt(br.readLine());
        int[] dp = new int[100_001];
        Arrays.fill(dp, 100_000);
        dp[1] = dp[2] = dp[5] = dp[7] = 1;
        for (int i = 3; i <= n; i++) {
            dp[i] = Integer.min(dp[i], dp[i-1]+1);
            dp[i] = Integer.min(dp[i], dp[i-2]+1);
            if (i >= 5) dp[i] = Integer.min(dp[i], dp[i-5]+1);
            if (i >= 7) dp[i] = Integer.min(dp[i], dp[i-7]+1);
        }

        bw.write(Integer.toString(dp[n]));
        bw.flush();
    }
}
