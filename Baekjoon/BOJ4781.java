import java.io.*;
import java.util.*;

public class BOJ4781 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        String input = br.readLine();
        while (input != null) {
            StringTokenizer st = new StringTokenizer(input);
            int n = Integer.parseInt(st.nextToken());
            int m = (int) (Double.parseDouble(st.nextToken()) * 100 + 0.5);
            if (n == 0 && (int) (m*100+0.5) == 0) break;
            int[] dp = new int[m+1];
            for (int i = 0; i < n; i++) {
                st = new StringTokenizer(br.readLine());
                int calorie = Integer.parseInt(st.nextToken());
                int money = (int) (Double.parseDouble(st.nextToken()) * 100 + 0.5);
                for (int j = 1; j <= m; j++) {
                    if (j < money) continue;
                    dp[j] = Integer.max(dp[j], dp[j-money] + calorie);
                    dp[j] = Integer.max(dp[j], dp[j-1]);
                }
            }

            bw.write(Integer.toString(dp[m]));
            bw.newLine(); 
            input = br.readLine();
        }
        bw.flush();
    }
}
