import java.io.*;
import java.util.*;

public class BOJ2655 {
    private static class Pair {
        int idx, area, height, weight;
        Pair(int idx, int area, int height, int weight) {
            this.idx = idx;
            this.area = area;
            this.height = height;
            this.weight = weight;
        }
    }
    private static Pair[] arr;
    private static int[] dp;
    private static Deque<Integer> idxDeq = new ArrayDeque<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int n = Integer.parseInt(br.readLine());
        arr = new Pair[n];
        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int area = Integer.parseInt(st.nextToken());
            int height = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());
            arr[i] = new Pair(i, area, height, weight); 
        }
        dp = new int[n];
        Arrays.sort(arr, (p1, p2) -> Integer.compare(p2.area, p1.area));
        int maxHeight = 0;
        for (int i = 0; i < n; i++) {
            maxHeight = Integer.max(maxHeight, recur(i));
        }
        for (int i = 0; i < n; i++) {
            if (dp[i] == maxHeight) {
                idxDeq.addFirst(arr[i].idx+1);
                maxHeight -= arr[i].height;
            }
        }
        bw.write(Integer.toString(idxDeq.size()));
        bw.newLine();
        while (!idxDeq.isEmpty()) {
            bw.write(Integer.toString(idxDeq.removeFirst()));
            bw.newLine();
        }
        bw.flush();
    }

    private static int recur(int depth) {
        if (depth >= arr.length) return 0;
        if (dp[depth] > 0) return dp[depth];
        dp[depth] = arr[depth].height;
        for (int i = depth+1; i < arr.length; i++) {
            if (arr[i].weight < arr[depth].weight) {
                dp[depth] = Integer.max(dp[depth], recur(i) + arr[depth].height);
            }
        }
        return dp[depth];
    }
}
