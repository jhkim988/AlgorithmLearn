import java.io.*;
import java.util.*;

public class BOJ2662 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int[][] arr = new int[n+1][m]; // arr[invest][benefit]
        for (int i = 1; i <= n; i++) {
            st = new StringTokenizer(br.readLine());
            st.nextToken();
            for (int j = 0; j < m; j++) {
                int benefit = Integer.parseInt(st.nextToken());
                arr[i][j] = benefit;
            }
        }

        // knapsack:
        int[] dp = new int[n+1]; // dp[invest] = max benefit        
        int[][] investToCompany = new int[n+1][m];
        for (int i = 1; i <= n; i++) {
            dp[i] = arr[i][0];
            investToCompany[i][0] = i;
        }

        int[] memo = new int[n+1];
        for (int k = 1; k < m; k++) {
            System.arraycopy(dp, 0, memo, 0, dp.length);
            for (int j = 1; j <= n; j++) {
                for (int i = 1; i <= n; i++) {
                    if (i < j) continue;
                    if (memo[i] >= dp[i-j] + arr[j][k]) continue;
                    memo[i] = dp[i-j] + arr[j][k];
                    investToCompany[i][k] = j;
                }
            }
            System.arraycopy(memo, 0, dp, 0, memo.length);
        }
        bw.write(Integer.toString(dp[n]));
        bw.newLine();

        int[] answer = new int[m];
        int x = n;
        for (int i = m-1; i >= 0; i--) {
            answer[i] = investToCompany[x][i];
            x -= answer[i];
        }
        for (int val : answer) {
            bw.write(Integer.toString(val));
            bw.write(' ');
        }
        bw.flush();
    }
}