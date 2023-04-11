import java.io.*;
import java.util.*;

public class BOJ15992 {
    private static long[][] dp = new long[1001][1001];
    private static long d = 1_000_000_009;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        
        for (int i = 0; i < dp.length; i++) {
            Arrays.fill(dp[i], -1);
        }
        
        int nTest = Integer.parseInt(br.readLine());
        while (nTest-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            int m = Integer.parseInt(st.nextToken());
            bw.write(Long.toString(recur(n, m)));
            bw.newLine();
        }
        bw.flush();
    }

    private static long recur(int n, int m) {
        if (n == 0 && m == 0) return 1;
        if (n <= 0 || m <= 0) return 0;
        if (dp[n][m] != -1) return dp[n][m];
        long ret = ((recur(n-1, m-1) + recur(n-2, m-1))%d + recur(n-3, m-1))%d;
        return dp[n][m] = ret%d;
    }
}
