import java.io.*;
import java.util.*;

public class BOJ20171 {
    private static List<Queue<Integer>> graph;
    private static Set<Integer> apart;
    private static int[] dp;
    private static int root, answer = 0;
    public static void main(String[] args) throws IOException {
        // input & init
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        
        graph = new ArrayList<>();
        for (int i = 0; i <= n; i++) {
            graph.add(new ArrayDeque<>());
        }
        
        for (int i = 1; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            graph.get(start).add(end);
            graph.get(end).add(start);
        }

        st = new StringTokenizer(br.readLine());
        apart = new HashSet<>();
        while (k-- > 0) {
            apart.add(Integer.parseInt(st.nextToken()));
        }

        // dp table construction
        dp = new int[n+1];
        root = ((int) (Math.random() * n)) + 1;
        // root = 1;
        recur(root, -1);
        answerRecur(root, -1);
        bw.write(Integer.toString(answer));
        bw.flush();
    }
    private static int recur(int node, int prev) {
        for (int adj : graph.get(node)) {
            if (adj == prev) continue;
            dp[node] += recur(adj, node);
        }
        if (apart.contains(node)) dp[node]++;
        return dp[node];
    }
    private static void answerRecur(int node, int prev) {
        if (dp[node] == 0) return;
        int count = 0;
        for (int adj : graph.get(node)) {
            if (prev == adj) continue;
            if (dp[adj] > 0) count++;
            answerRecur(adj, node);

        }
        if (count >= 2) answer++;
        else if (count == 1 && dp[root] > dp[node]) answer++;
        else if (apart.contains(node)) answer++;
    }
}
