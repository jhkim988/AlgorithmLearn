import java.io.*;
import java.util.*;

public class BOJ14863 {
    private static int INIT = -10_000_000;
    private static int n, timeLim;
    private static int[][] data;
    private static long[][] dp;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        timeLim = Integer.parseInt(st.nextToken());
        data = new int[n][4];
        dp = new long[n][100_001];
        for (int i = 0; i < n; i++) {
            Arrays.fill(dp[i], INIT);
        }
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 4; j++) {
                data[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        long answer = recur(0, 0);
        bw.write(Long.toString(answer));
        bw.flush();
    }
    private static long recur(int idx, int time) {
        if (time > timeLim) return -1;
        if (idx >= data.length) return 0;
        if (dp[idx][time] != INIT) return dp[idx][time];
        long bike = recur(idx+1, time + data[idx][0]);
        long walk = recur(idx+1, time + data[idx][2]);
        long ret = -1;
        if (bike >= 0 && walk >= 0) {
            ret = Long.max(bike + data[idx][1], walk + data[idx][3]);
        } else if (bike >= 0) {
            ret = bike + data[idx][1];
        } else if (walk >= 0) {
            ret = walk + data[idx][3];
        }
        return dp[idx][time] = ret;
    }
}
