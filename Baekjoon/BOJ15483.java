import java.io.*;
import java.util.*;

public class BOJ15483 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        char[] strA = br.readLine().toCharArray();
        char[] strB = br.readLine().toCharArray();

        int[][] dp = new int[strA.length+1][strB.length+1];
        for (int i = 0; i <= strA.length; i++) {
            dp[i][0] = i;
        }
        for (int j = 0; j <= strB.length; j++) {
            dp[0][j] = j;
        }
        for (int i = 1; i <= strA.length; i++) {
            for (int j = 1; j <= strB.length; j++) {
                if (strA[i-1] == strB[j-1]) {
                    dp[i][j] = dp[i-1][j-1];
                } else {
                    dp[i][j] = Integer.min(dp[i-1][j], Integer.min(dp[i][j-1], dp[i-1][j-1])) + 1;
                }
            }
        }
        bw.write(Integer.toString(dp[strA.length][strB.length]));
        bw.flush();
    }
}
