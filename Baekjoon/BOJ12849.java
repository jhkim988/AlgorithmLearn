import java.io.*;
import java.util.*;

public class BOJ12849 {
    private static final int d = 1_000_000_007;
    private static List<Queue<Integer>> graph = initGraphByString();
    private static int[][] dp;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int dist = Integer.parseInt(br.readLine());
        
        dp = new int[8][dist+1];
        for (int i = 0; i < 8; i++) Arrays.fill(dp[i], -1);
        dp[0][0] = 1;

        int answer = recur(0, dist);
        bw.write(Integer.toString(answer));
        bw.flush();
    }
    private static int recur(int pos, int time) {
        if (time < 0) return 0;
        if (dp[pos][time] != -1) return dp[pos][time];

        int ret = 0;
        for (int adj : graph.get(pos)) {
            ret += recur(adj, time-1);
            ret %= d;
        }
        return dp[pos][time] = ret;
    }

    private static List<Queue<Integer>> initGraph() {
        List<Queue<Integer>> graph = new ArrayList<>();
        for (int i = 0; i < 8; i++) {
            graph.add(new ArrayDeque<>());
        }
        
        graph.get(0).add(1);
        graph.get(0).add(2);
    
        graph.get(1).add(0);
        graph.get(1).add(2);
        graph.get(1).add(3);

        graph.get(2).add(0);
        graph.get(2).add(1);
        graph.get(2).add(3);
        graph.get(2).add(4);

        graph.get(3).add(1);
        graph.get(3).add(2);
        graph.get(3).add(4);
        graph.get(3).add(5);
    
        graph.get(4).add(2);
        graph.get(4).add(3);
        graph.get(4).add(5);
        graph.get(4).add(6);

        graph.get(5).add(3);
        graph.get(5).add(4);
        graph.get(5).add(7);
    
        graph.get(6).add(4);
        graph.get(6).add(7);
    
        graph.get(7).add(5);
        graph.get(7).add(6);

        return graph;
    }
    private static List<Queue<Integer>> initGraphByString() {
        String init = "8 12\n0 1\n0 2\n1 2\n1 3\n2 3\n2 4\n3 4\n3 5\n4 5\n4 6\n5 7\n6 7";
        StringTokenizer st = new StringTokenizer(init , " \n");
        int v = Integer.parseInt(st.nextToken());
        int e = Integer.parseInt(st.nextToken());
        System.out.println("v: " + v + ", e: " + e);
        List<Queue<Integer>> graph = new ArrayList<>();
        for (int i = 0; i < v; i++) graph.add(new ArrayDeque<>());
        while (e-- > 0) {
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            graph.get(start).add(end);
            graph.get(end).add(start);
            System.out.println("edge: " + start + ", " + end);
        }
        return graph;
    }
}
