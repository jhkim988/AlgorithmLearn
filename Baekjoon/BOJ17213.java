import java.io.*;
import java.util.*;

public class BOJ17213 {
    private static int n, m;
    private static long[][] dp = new long[11][31];
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        n = Integer.parseInt(br.readLine());
        m = Integer.parseInt(br.readLine());
        for (int i = 0; i < dp.length; i++) {
            Arrays.fill(dp[i], -1);
        }
        long answer = recur(0, m);
        bw.write(Long.toString(answer));
        bw.flush();
    }

    private static long recur(int depth, int count) {
        if (depth >= n) return count == 0 ? 1 : 0;
        if (dp[depth][count] != -1) return dp[depth][count];
        long ret = 0;
        for (int i = 1; i <= count; i++) {
            ret += recur(depth+1, count-i);
        }
        return dp[depth][count] = ret;
    }
}