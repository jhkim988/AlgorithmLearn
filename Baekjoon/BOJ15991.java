import java.io.*;
import java.util.*;

public class BOJ15991 {
    private static int d = 1_000_000_009;
    private static int[] dp = new int[100_001];
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        Arrays.fill(dp, -1);
        dp[0] = dp[1] = 1;
        dp[2] = 2; dp[3] = 2; 
        int nTest = Integer.parseInt(br.readLine());
        while (nTest-- > 0) {
            int x = Integer.parseInt(br.readLine());
            bw.write(Integer.toString(recur(x)));
            bw.newLine();
        }
        bw.flush();
    }

    private static int recur(int x) {
        if (dp[x] != -1) return dp[x];
        int ret = 0;
        for (int i = 1; i <= 3 && i*2 <= x; i++) {
            ret += recur(x-2*i);
            ret %= d;
        }
        return dp[x] = ret;
    }
}
