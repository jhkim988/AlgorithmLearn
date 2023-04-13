import java.io.*;

public class BOJ2780 {
    private static final int d = 1234567;
    private static int[][] adj = {{7}, {2, 4}, {1, 3, 5}, {2, 6}, {1, 5, 7}, {2, 4, 6, 8}, {3, 5, 9}, {4, 8, 0}, {5, 7, 9}, {6, 8}};
    private static int[][] dp = new int[10][1001];
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int nTest = Integer.parseInt(br.readLine());
        while (nTest-- > 0) {
            int n = Integer.parseInt(br.readLine());
            int answer = 0;
            for (int i = 0; i < 10; i++) {
                answer += recur(i, n);
                answer %= d;
            }
            bw.write(Integer.toString(answer));
            bw.newLine();
        }
        bw.flush();
    }
    private static int recur(int pos, int n) {
        if (n <= 1) return 1;
        if (dp[pos][n] != 0) return dp[pos][n];
        int ret = 0;
        for (int i = 0; i < adj[pos].length; i++) {
            ret += recur(adj[pos][i], n-1);
            ret %= d;
        }
        return dp[pos][n] = ret;
    }
}
