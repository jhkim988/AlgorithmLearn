import java.io.*;
import java.util.*;

public class BOJ3067 {
    private static int[] dp = new int[10_001];
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int numTest = Integer.parseInt(br.readLine());
        while (numTest-- > 0) {
            int numKind = Integer.parseInt(br.readLine());
            int[] coins = new int[numKind];
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int i = 0; i < numKind; i++) {
                coins[i] = Integer.parseInt(st.nextToken());
            }
            int target = Integer.parseInt(br.readLine());
            int answer = solution(target, coins);
            bw.write(Integer.toString(answer));
            bw.newLine();
        }
        bw.flush();
    }
    private static int solution(int money, int[] coins) {
        Arrays.fill(dp, 0);
        dp[0] = 1;
        for (int i = 0; i < coins.length; i++) {
            for (int j = coins[i]; j <= money; j++) {
                dp[j] += dp[j - coins[i]];
            }
        }
        return dp[money];
    }
}