import java.io.*;
import java.util.*;

public class BOJ2300 {
    private static class Pair {
        int x, y;
        public Pair(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int n = Integer.parseInt(br.readLine());
        Pair[] arr = new Pair[n];
        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            arr[i] = new Pair(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
        }
        Arrays.sort(arr, (p1, p2) -> p1.x != p2.x ? p1.x - p2.x : p1.y - p2.y);
        long[] dp = new long[n];
        Arrays.fill(dp, Long.MAX_VALUE >> 2);
        dp[0] = Math.abs(arr[0].y)*2;
        for (int i = 1; i < n; i++) {
            dp[i] = dp[i-1] + Math.abs(arr[i].y)*2;
            long minX = arr[i].x;
            long maxX = arr[i].x;
            long maxY = Math.abs(arr[i].y);
            for (int j = i-1; j >= 1; j--) {
                minX = Math.min(minX, arr[j].x);
                maxX = Math.max(maxX, arr[j].x);
                maxY = Math.max(maxY, Math.abs(arr[j].y));
                dp[i] = Math.min(dp[i], dp[j-1] + Long.max((maxX-minX), 2*maxY));
            }
            minX = Math.min(minX, arr[0].x);
            maxX = Math.max(maxX, arr[0].x);
            maxY = Math.max(maxY, Math.abs(arr[0].y));
            dp[i] = Math.min(dp[i], Long.max((maxX-minX), 2*maxY));
        }

        bw.write(Long.toString(dp[n-1]));
        bw.flush();
    }
}