import java.io.*;
import java.util.*;

public class BOJ1135 {
    private static int n;
    private static int[] tree, dp;
    private static class Pair {
        int idx, val;
        Pair(int idx, int val) {
            this.idx = idx;
            this.val = val;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        n = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        tree = new int[n];
        for (int i = 0; i < n; i++) {
            tree[i] = Integer.parseInt(st.nextToken());
        }

        dp = new int[n];
        Arrays.fill(dp, -1);
        int answer = recur(0);
        bw.write(Integer.toString(answer));
        bw.flush();
    }
    private static int recur(int node) {
        if (dp[node] != -1) return dp[node];
        List<Pair> arr = new ArrayList<>();
        int ret = 0;
        for (int i = 0; i < n; i++) {
            if (tree[i] != node) continue;
            arr.add(new Pair(i, recur(i)));
        }
        Collections.sort(arr, (p1, p2) -> Integer.compare(p2.val, p1.val));
        for (int i = 0; i < arr.size(); i++) {
            ret = Integer.max(ret, recur(arr.get(i).idx) + i+1);
        }
        return dp[node] = ret;
    }
}