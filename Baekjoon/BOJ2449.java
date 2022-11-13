import java.io.*;
import java.util.*;

public class BOJ2449 {
    private static long INF = Long.MAX_VALUE >> 1;
    private static int[] arr;
    private static long[][] dp;
    private static int n, k;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        arr = new int[n];
        
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        dp = new long[n][n];
        for (int i = 0; i < n; i++) {
            Arrays.fill(dp[i], -1);
        }

        bw.write(Long.toString(recur(0, n-1)));
        bw.flush();
    }
    private static long recur(int left, int right) {
        if (dp[left][right] != -1) return dp[left][right];

        if (left == right) {
            return dp[left][right] = 0;
        }

        long min = INF;
        for (int i = left; i < right; i++) {
            long val = recur(left, i) + recur(i+1, right) + (arr[left] == arr[i+1] ? 0 : 1);
            if (val < min) min = val;
        }

        return dp[left][right] = min;
    }
}