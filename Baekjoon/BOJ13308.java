import java.io.*;
import java.util.*;

public class BOJ13308 {
    private static int INF = Integer.MAX_VALUE >> 2;
    private static class Pair {
        int end, weight;
        Pair(int end, int weight) {
            this.end = end;
            this.weight = weight;
        }
    }
    private static int[] cost;
    private static List<Queue<Pair>> graph;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        
        st = new StringTokenizer(br.readLine());
        cost = new int[n+1];
        for (int i = 1; i <= n; i++) {
            cost[i] = Integer.parseInt(st.nextToken());
        }

        graph = new ArrayList<>();
        for (int i = 0; i <= n; i++) {
            graph.add(new ArrayDeque<>());
        }
        while (m-- > 0) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());
            graph.get(start).add(new Pair(end, weight));
            graph.get(end).add(new Pair(start, weight));
        }

        bw.write(Integer.toString(recur(1, n)));
        bw.flush();
    }

    private static int recur(int node, int gas) {
        
    }
}
