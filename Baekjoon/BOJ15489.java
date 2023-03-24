import java.io.*;
import java.util.*;

public class BOJ15489 {
    private static long[][][] dp = new long[31][31][31];
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int r = Integer.parseInt(st.nextToken());
        int c = Integer.parseInt(st.nextToken());
        int w = Integer.parseInt(st.nextToken());
        bw.write(Long.toString(recur(r, c, w)));
        bw.flush();
    }
    private static long recur(int r, int c, int w) {
        if (r < 1 || c < 1 || w < 1) return 0;
        if (dp[r][c][w] != 0) return dp[r][c][w];
        if (w == 1) {
            if (c == 1) return dp[r][c][w] = 1;
            return dp[r][c][w] = recur(r-1, c-1, 1) + recur(r-1, c, 1);
        }
        return dp[r][c][w] = recur(r, c, 1) + recur(r+1, c, w-1) + recur(r+1, c+1, w-1) - recur(r+2, c+1, w-2);
    }
}