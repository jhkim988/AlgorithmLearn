import java.io.*;
import java.util.*;

public class BOJ2515 {
    private static class Pair {
        int height, price;
        Pair(int height, int price) {
            this.height = height;
            this.price = price;
        }
    }
    private static int n, s;
    private static Pair[] paints;
    private static int[] dp, max;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        s = Integer.parseInt(st.nextToken());
        paints = new Pair[n+1];
        dp = new int[n+1];
        max = new int[n+1];
        paints[0] = new Pair(0, 0);
        for (int i = 1; i <= n; i++) {
            st = new StringTokenizer(br.readLine());
            int height = Integer.parseInt(st.nextToken());
            int price = Integer.parseInt(st.nextToken());
            paints[i] = new Pair(height, price);
        }

        Arrays.sort(paints, (p1, p2) -> Integer.compare(p1.height, p2.height));
        for (int i = 1; i <= n; i++) {
            for (max[i] = max[i-1]+1; max[i] < i; max[i]++) {
                if (paints[i].height - paints[max[i]].height < s) break;
            }
            max[i]--;
        }
        for (int i = 1; i <= n; i++) {
            dp[i] = dp[max[i]] + paints[i].price;
            dp[i] = Integer.max(dp[i], dp[i-1]);
        }
        bw.write(Integer.toString(dp[n]));
        bw.flush();
    }
}
