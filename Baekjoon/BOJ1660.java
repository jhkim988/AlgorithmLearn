import java.io.*;
import java.util.*;

public class BOJ1660 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int n = Integer.parseInt(br.readLine());
        int[] dp = new int[n+1];
        Arrays.fill(dp, Integer.MAX_VALUE >> 2);
        dp[0] = 0;
        for (int i = 1; i <= n; i++) {
            for (int j = 0; j < 125 && j*(j+1)*(j+2)/6 <= i; j++) {
                dp[i] = Integer.min(dp[i], dp[i-(j*(j+1)*(j+2)/6)]);
            }
            dp[i]++;
        }

        bw.write(Integer.toString(dp[n]));
        bw.flush();
    }    
}
