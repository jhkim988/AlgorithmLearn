import java.io.*;
import java.util.*;

public class BOJ1693 {

    private static List<Queue<Integer>> graph;
    private static int nColor = 0;
    private static int[][] dp;
    private static boolean[] visit;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int n = Integer.parseInt(br.readLine());
        graph = new ArrayList<>();
        for (int i = 0; i <= n; i++) {
            graph.add(new ArrayDeque<>());
        }
        for (int i = 1; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            graph.get(a).add(b);
            graph.get(b).add(a);
        }
        for (int t = 1; t <= n; t <<= 1) nColor++;
        dp = new int[n+1][nColor];
        visit = new boolean[n+1];

        int answer = Integer.MAX_VALUE;
        for (int c = 1; c < nColor; c++) {
            Arrays.fill(visit, false);
            visit[1] = true;
            answer = Integer.min(answer, recur(1, c));
        }

        bw.write(Integer.toString(answer));
        bw.flush();
    }
    private static int recur(int node, int color) {
        if (dp[node][color] != 0) return dp[node][color];
        int ret = 0;
        for (int adj : graph.get(node)) {
            if (visit[adj]) continue;
            visit[adj] = true;
            int subTreeValue = Integer.MAX_VALUE;
            for (int c = 1; c < nColor; c++) {
                if (c == color) continue;
                subTreeValue = Integer.min(subTreeValue, recur(adj, c));
            }
            visit[adj] = false;
            ret += subTreeValue;
        }
        return dp[node][color] = ret + color;
    }
}