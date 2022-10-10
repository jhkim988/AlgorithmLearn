import java.io.*;
import java.util.*;

public class BOJ2698 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        
        int[][][] dp = new int[2][101][101]; // dp[firstBit][length][numAdjBit]
        
        dp[0][1][0] = dp[1][1][0] = 1;
        for (int i = 2; i < 101; i++) {
            dp[0][i][0] = dp[0][i-1][0] + dp[1][i-1][0];
            dp[1][i][0] = dp[0][i-1][0];
            for (int k = 1; k <= i; k++) {
                dp[0][i][k] = dp[0][i-1][k] + dp[1][i-1][k];
                dp[1][i][k] = dp[0][i-1][k] + dp[1][i-1][k-1];
            }
        }
        
        int numTest = Integer.parseInt(br.readLine());
        while (numTest-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            int k = Integer.parseInt(st.nextToken());
            bw.write(Integer.toString(dp[0][n][k] + dp[1][n][k]));
            bw.newLine();
        }
        bw.flush();
    }
}