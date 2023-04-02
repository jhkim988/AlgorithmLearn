import java.io.*;
import java.util.*;

public class BOJ1727 {
    private static int n, m;
    private static int[] male, female;
    private static long[][] dp;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        male = new int[n+1];
        female = new int[m+1];
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= n; i++) {
            male[i] = Integer.parseInt(st.nextToken());
        }
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= m; i++) {
            female[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(male);
        Arrays.sort(female);
        if (n < m) {
            int tmp = n;
            n = m;
            m = tmp;
        
            int[] tmpArr = male;
            male = female;
            female = tmpArr;
        }

        dp = new long[n+1][m+1];
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                if (i > j) dp[i][j] = Long.min(dp[i-1][j], dp[i-1][j-1]+Math.abs(male[i] - female[j]));
                if (i < j) dp[i][j] = Long.min(dp[i][j-1], dp[i-1][j-1]+Math.abs(male[i] - female[j]));
                if (i == j)
                dp[i][j] = dp[i-1][j-1] + Math.abs(male[i] - female[j]);
            }
        }

        bw.write(Long.toString(dp[n][m]));
        bw.flush();

    }
}
