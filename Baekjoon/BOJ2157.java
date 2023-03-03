import java.io.*;
import java.util.*;

public class BOJ2157 {
    private static int n, m, k;
    private static int[][] path;
    private static long[][] dp;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken()) - 1;
        k = Integer.parseInt(st.nextToken());
        path = new int[n+1][n+1];
        for (int i = 0; i < k; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            if (b <= a) continue;
            path[a][b] = Integer.max(path[a][b], c);
        }

        dp = new long[m][n+1];
        for (int i = 0; i < m; i++) {
            Arrays.fill(dp[i], -1);
        }
        long answer = recur(0, 1);
        bw.write(Long.toString(answer));
        bw.flush();
    }

    private static long recur(int depth, int point) {
        if (point >= n) return 0;
        if (depth >= m) return Integer.MIN_VALUE;
        if (dp[depth][point] >= 0) return dp[depth][point];
        long ret = Integer.MIN_VALUE;
        for (int i = point+1; i <= n; i++) {
            if (path[point][i] == 0) continue;
            ret = Long.max(ret, recur(depth+1, i) + path[point][i]);
        }
        return dp[depth][point] = ret;
    }
}
