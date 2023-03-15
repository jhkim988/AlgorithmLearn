import java.io.*;
import java.util.*;

public class BOJ5569 {
    private static int w, h;
    private static int[] wDi = {0, 1}, hDi = {1, 0};
    private static final int d = 100_000;
    private static int[][][][] dp;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        w = Integer.parseInt(st.nextToken());
        h = Integer.parseInt(st.nextToken());
        dp = new int[3][2][w+1][h+1];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 2; j++) {
                for (int k = 0; k <= w; k++) {
                    Arrays.fill(dp[i][j][k], -1);
                }
            }
        }

        int answer = recur(2, 0, 1, 1) + recur(2, 1, 1, 1);
        answer %= d;
        bw.write(Integer.toString(answer));
        bw.flush();
    }
    private static int recur(int prevDirection, int direction, int posW, int posH) {
        if (posW == w && posH == h) return 1;
        if (prevDirection >= 0 && dp[prevDirection][direction][posW][posH] != -1) return dp[prevDirection][direction][posW][posH];
        if (direction != prevDirection) {
            if (posW+wDi[direction] > w || posH+hDi[direction] > h) return 0;
            return dp[prevDirection][direction][posW][posH] = recur(direction, direction, posW + wDi[direction], posH + hDi[direction]);
        } else {
            int ret = 0;
            for (int i = 0; i < 2; i++) {
                if (posW+wDi[i] > w || posH+hDi[i] > h) continue;
                ret += recur(direction, i, posW + wDi[i], posH + hDi[i]);
                ret %= d;
            }
            return dp[prevDirection][direction][posW][posH] = ret;
        }
    }
}