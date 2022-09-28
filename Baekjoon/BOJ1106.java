import java.io.*;
import java.util.*;

public class BOJ1106 {
    private static final int INF = Integer.MAX_VALUE/2;
    private static class Pair {
        int cost, customer;
        Pair(int cost, int customer) {
            this.cost = cost;
            this.customer = customer;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int c = Integer.parseInt(st.nextToken());
        int n = Integer.parseInt(st.nextToken());
        Pair[] arr = new Pair[n];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            int cost = Integer.parseInt(st.nextToken());
            int customer = Integer.parseInt(st.nextToken());
            arr[i] = new Pair(cost, customer);
        }
        
        int[] dp = new int[3_000];
        Arrays.fill(dp, INF);
        dp[0] = 0;
        for (int j = 0; j < n; j++) {
            for (int i = 0; i < dp.length; i++) {
                if (i < arr[j].customer) continue;
                dp[i] = Integer.min(dp[i], dp[i-arr[j].customer] + arr[j].cost);
            }
        }
        int min = INF;
        for (int i = c; i < dp.length; i++) {
            if (dp[i] < min) min = dp[i];
        }
        bw.write(Integer.toString(min));
        bw.flush();
    }
}