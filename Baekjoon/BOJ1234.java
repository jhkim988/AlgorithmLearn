import java.io.*;
import java.util.*;

public class BOJ1234 {
    private static int n;
    private static long[][][][] dp;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int nRed = Integer.parseInt(st.nextToken());
        int nBlue = Integer.parseInt(st.nextToken());
        int nGreen = Integer.parseInt(st.nextToken());

        dp = new long[n+1][nRed+1][nBlue+1][nGreen+1];
        for (int i = 0; i <= n; i++) {
            for (int j = 0; j <= nRed; j++) {
                for (int k = 0; k <= nBlue; k++) {
                    Arrays.fill(dp[i][j][k], -1);
                }
            }
        }

        bw.write(Long.toString(recur(n, nRed, nBlue, nGreen)));
        bw.flush();
    }
    private static long recur(int level, int red, int blue, int green) {
        if (red < 0 || blue < 0 || green < 0) {
            return 0;
        }
        if (level <= 0) {
            return 1;
        }
        if (dp[level][red][blue][green] != -1) {
            return dp[level][red][blue][green];
        }
        long ret = 0;
        if (level % 3 == 0) {
            ret += recur(level - 1, red - level/3, blue - level/3, green - level/3) * comb(level, level/3) * comb(level/3*2, level/3);
        }
        if (level % 2 == 0) {
            ret += recur(level - 1, red - level/2, blue - level/2, green) * comb(level, level/2);
            ret += recur(level - 1, red - level/2, blue, green - level/2) * comb(level, level/2);
            ret += recur(level - 1, red, blue - level/2, green - level/2) * comb(level, level/2);
        }
        ret += recur(level - 1, red - level, blue, green);
        ret += recur(level - 1, red, blue - level, green);
        ret += recur(level - 1, red, blue, green - level);
        return dp[level][red][blue][green] = ret;
    }

    private static long comb(int n, int r) {
        long ret = 1;
        for (int i = 0; i < r; i++) {
            ret *= n - i;
            ret /= i + 1;
        }
        return ret;
    }
}