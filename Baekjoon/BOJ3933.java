import java.io.*;
import java.util.*;

public class BOJ3933 {
    private static class TopDown {
        private long[][][] dp = new long[5][200][(1<<15)+1];
        public TopDown() {
            for (int i = 0; i < dp.length; i++) {
                for (int j = 0; j < dp[i].length; j++) {
                    Arrays.fill(dp[i][j], -1);
                }
            }
        }

        public long answer(int x) {
            return recur(4, 1, x);
        }

        private long recur(int k, int prev, int n) {
            if (n == 0) return 1;
            if (k <= 0 && n > 0) return 0;
            if (dp[k][prev][n] != -1) return dp[k][prev][n];
            long ret = isSquare(n) ? 1 : 0;
            for (int i = prev; 2*i*i <= n; i++) {
                ret += recur(k-1, i, n-i*i);
            }
            return dp[k][prev][n] = ret;
        }
        private boolean isSquare(int x) {
            int sqrt = (int) Math.sqrt(x);
            return sqrt*sqrt == x;
        }
    }

    public static class BottomUp {
        private long[][] dp = new long[5][(1<<15)+1];
        public BottomUp() {
            int len = (1<<15)+1;
            for (int i = 1; i*i < len; i++) {
                dp[1][i*i] = 1;
                for (int j = i*i; j < len; j++) {
                    for (int k = 2; k <= 4; k++) {
                        dp[k][j] += dp[k-1][j-i*i];
                    }
                }
            }
        }

        public long answer(int x) {
            long answer = 0;
            for (int k = 1; k <= 4; k++) {
                answer += dp[k][x];
            }
            return answer;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        // TopDown dp = new TopDown();
        BottomUp dp = new BottomUp();
        int n = 0;
        while ((n = Integer.parseInt(br.readLine())) != 0) {
            long answer = dp.answer(n);
            bw.write(Long.toString(answer));
            bw.newLine();
        }
        bw.flush();
    }

}
