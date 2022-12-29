import java.io.*;
import java.util.*;

public class BOJ2666 {
    private static int[] que;
    private static int[][][] dp;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int n = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        int open1 = Integer.parseInt(st.nextToken());
        int open2 = Integer.parseInt(st.nextToken());
     
        int m = Integer.parseInt(br.readLine());
        que = new int[m];
        for (int i = 0; i < m; i++) {
            que[i] = Integer.parseInt(br.readLine());
        }
        dp = new int[m][n+1][n+1];
        Arrays.stream(dp)
            .forEach(doubleArr -> Arrays.stream(doubleArr)
            .forEach(arr -> Arrays.fill(arr, -1)));
    
        int answer = recur(0, open1, open2);
        bw.write(Integer.toString(answer));
        bw.flush();
    }
    
    private static int recur(int depth, int left, int right) {
        if (depth >= que.length) {
            return 0;
        }
        if (dp[depth][left][right] != -1) return dp[depth][left][right]; 
        if (left == que[depth] || right == que[depth]) {
            return dp[depth][left][right] = recur(depth+1, left, right);
        }

        int ret = 0;
        if (que[depth] < left) {
            ret = recur(depth+1, que[depth], right) + left-que[depth];
        } else if (que[depth] > right) {
            ret = recur(depth+1, left, que[depth]) + que[depth] - right;
        } else {
            int leftMove = recur(depth+1, que[depth], right) + que[depth] - left;
            int rightMove = recur(depth+1, left, que[depth]) + right - que[depth];
            ret = Integer.min(leftMove, rightMove);
        } 
        return dp[depth][left][right] = ret;
    }
}