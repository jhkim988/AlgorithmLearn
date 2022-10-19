import java.io.*;

public class BOJ1720 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int n = Integer.parseInt(br.readLine());
        bw.write(Integer.toString(answer(n)));
        bw.flush();
    } 
    private static int answer(int n) {
        int[] dp = new int[31];
        dp[0] = 1;
        dp[1] = 1;
        dp[2] = 3;
        for (int i = 3; i <= n; i++) {
            dp[i] = dp[i-1] + dp[i-2]*2;
        }
        if (n % 2 == 0) return (dp[n] + dp[n/2] + dp[n/2 - 1]*2) / 2;
        return (dp[n] + dp[(n-1)/2])/2;
    }
}
