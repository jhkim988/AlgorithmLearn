import java.io.*;
import java.util.*;

public class BOJ2560 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int a = Integer.parseInt(st.nextToken());
        int b = Integer.parseInt(st.nextToken());
        int d = Integer.parseInt(st.nextToken());
        int n = Integer.parseInt(st.nextToken());
        int[] dp = new int[n + 1];
        dp[0] = 1;
        for (int i = 1; i <= n; i++) {
            dp[i] = dp[i - 1];
            if (i >= a) {
                dp[i] += dp[i - a];
                dp[i] %= 1000;    
            }
            if (i - b >= 0) {
                dp[i] += 1000 - dp[i - b];
                dp[i] %= 1000;
            }
        }

        int answer = n >= d ? (dp[n]-dp[n-d] + 1000)%1000 : dp[n]%1000;
        bw.write(Integer.toString(answer));
        bw.flush();
    }
}