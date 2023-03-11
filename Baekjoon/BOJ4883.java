import java.io.*;
import java.util.*;

public class BOJ4883 {
    private static long INF = Long.MAX_VALUE >> 2;
    private static int[] adj = {-1, 0, 1};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int size = Integer.parseInt(br.readLine());
        int numTest = 1;
        while (size != 0) {
            int[][] cost = new int[size][3];
            long[][] dp = new long[size][3];
            for (int i = 0; i < size; i++) {
                Arrays.fill(dp[i], INF);
            } 
            for (int i = 0; i < size; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                for (int j = 0; j < 3; j++) {
                    cost[i][j] = Integer.parseInt(st.nextToken());
                }
            }
            dp[0][1] = cost[0][1];
            dp[0][2] = Long.min(dp[0][2], dp[0][1] + cost[0][2]);
            for (int i = 1; i < size; i++) {
                for (int j = 0; j < 3; j++) {
                    for (int k = 0; k < 3; k++) {
                        int x = j + adj[k];
                        if (x < 0 || x >= 3) continue;
                        dp[i][j] = Long.min(dp[i][j], dp[i-1][x] + cost[i][j]);
                    }
                }
                for (int j = 1; j < 3; j++) {
                    dp[i][j] = Long.min(dp[i][j], dp[i][j-1] + cost[i][j]);
                }
            }
            bw.write(Integer.toString(numTest++));
            bw.write('.');
            bw.write(' ');
            bw.write(Long.toString(dp[size-1][1]));
            bw.newLine();
            size = Integer.parseInt(br.readLine());
        }
        bw.flush();
    }
}
