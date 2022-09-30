import java.io.*;
import java.util.*;

public class BOJ7570 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int n = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());

        int[] index = new int[n+1];
        for (int i = 0; i < n; i++) {
            int input = Integer.parseInt(st.nextToken());
            index[input] = i;
        }

        // dp:
        int max = 0;
        int[] dp = new int[n+1];
        for (int i = n-1; i > 0; i--) {
            if (index[i] >= index[i+1]) continue;
            dp[i] = dp[i+1] + 1;
            if (max < dp[i]) max = dp[i];
        }

        bw.write(Integer.toString(n-max-1));
        bw.flush();
    }
}