import java.io.*;
import java.util.*;

public class BOJ1126 {
    private static final int INF = Integer.MAX_VALUE >> 1;
    private static int n;
    private static int[] arr;
    private static int[][] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        
        n = Integer.parseInt(br.readLine());
        arr = new int[n];
        dp = new int[n][250_001];
        for (int i = 0; i < n; i++) {
            Arrays.fill(dp[i], -1);
        }

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        int answer = recur(n-1, 0);
        bw.write(Integer.toString(answer == 0 ? -1 : answer));
        bw.flush();
    }

    private static int recur(int pos, int diff) {
        if (diff > 250_000) return -INF;        
        if (pos < 0) return diff == 0 ? 0 : -INF;
        
        if (dp[pos][diff] != -1) return dp[pos][diff];
        int max = recur(pos-1, diff);
        max = Integer.max(max, recur(pos-1, diff + arr[pos]));
        if (arr[pos] > diff) {
            max = Integer.max(max, recur(pos-1, arr[pos] - diff) + diff);
        } else {
            max = Integer.max(max, recur(pos-1, diff - arr[pos]) + arr[pos]);
        }
        return dp[pos][diff] = max;
    }
}
