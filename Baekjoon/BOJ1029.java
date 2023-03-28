import java.io.*;

public class BOJ1029 {
    private static int n;
    private static int[][] price;
    private static int[][][] dp;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        n = Integer.parseInt(br.readLine());
        price = new int[n][n];
        for (int i = 0; i < n; i++) {
            char[] input = br.readLine().toCharArray();
            for (int j = 0; j < n; j++) {
                price[i][j] = input[j] - '0';
            }
        }
        dp = new int[n][10][1 << n];
        bw.write(Integer.toString(recur(0, 0, 1)));
        bw.flush();
    }
    private static int recur(int has, int p, int checked) {
        if (dp[has][p][checked] != 0) return dp[has][p][checked];
        int ret = 1;
        for (int i = 0; i < n; i++) {
            if ((checked & (1 << i)) != 0) continue;
            if (p > price[has][i]) continue;
            ret = Integer.max(ret, recur(i, price[has][i], checked | (1<<i))+1);
        }
        return dp[has][p][checked] = ret;
    }
}
