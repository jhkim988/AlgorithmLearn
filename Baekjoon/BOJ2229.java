import java.io.*;
import java.util.*;

public class BOJ2229 {
    private static int n;
    private static int[] score;
    private static long[] dp;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        n = Integer.parseInt(br.readLine());
        score = new int[n];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            score[i] = Integer.parseInt(st.nextToken());
        }

        dp = new long[n];
        Arrays.fill(dp, -1);
        long answer = recur(0);
        bw.write(Long.toString(answer));
        bw.flush();
    }
    private static long recur(int depth) {
        if (depth >= n) return 0;
        if (dp[depth] != -1) return dp[depth];
        long totalMax = 0;
        int min = score[depth], max = score[depth];
        for (int i = depth; i < n; i++) {
            if (score[i] < min) min = score[i];
            if (max < score[i]) max = score[i];
            if (totalMax < max - min + recur(i+1)) totalMax = max - min + recur(i+1);
        }
        return dp[depth] = totalMax;
    }
}