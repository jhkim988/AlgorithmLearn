import java.io.*;
import java.util.*;

public class BOJ1577 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        long[][] dp = new long[n+1][m+1];
        Set<Integer> set = new HashSet<>();
        int k = Integer.parseInt(br.readLine());
        while (k--> 0) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            if (c < a || d < b) {
                int tmp = a;
                a = c;
                c = tmp;
                tmp = b;
                b = d;
                d = tmp;
            }
            int hashCode = hashCode(a, b, c, d); 
            set.add(hashCode);
        }

        dp[0][0] = 1;
        for (int i = 0; i <= n; i++) {
            for (int j = 0; j <= m; j++) {
                if (i > 0 && !set.contains(hashCode(i-1, j, i, j))) dp[i][j] += dp[i-1][j];
                if (j > 0 && !set.contains(hashCode(i, j-1, i, j))) dp[i][j] += dp[i][j-1];
            }
        }

        bw.write(Long.toString(dp[n][m]));
        bw.flush();
    }

    private static int hashCode(int a, int b, int c, int d) {
        return a*101*101*101 + b*101*101 + c*101 + d;
    }
}
