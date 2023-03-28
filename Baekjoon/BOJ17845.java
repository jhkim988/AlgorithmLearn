import java.io.*;
import java.util.*;

public class BOJ17845 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        int[] important = new int[k];
        int[] time = new int[k];
        for (int i = 0; i < k; i++) {
            st = new StringTokenizer(br.readLine());
            int l = Integer.parseInt(st.nextToken());
            int t = Integer.parseInt(st.nextToken());
            important[i] = l;
            time[i] = t;
        }
        
        long[] dp = new long[n + 1];
        for (int j = 0; j < k; j++) {
            for (int i = n; i >= 0; i--) {
                if (i < time[j]) continue;
                dp[i] = Long.max(dp[i], dp[i - time[j]] + important[j]);
            }
        }

        bw.write(Long.toString(dp[n]));
        bw.flush();
    }
}