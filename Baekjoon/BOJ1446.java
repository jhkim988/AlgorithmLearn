import java.io.*;
import java.util.*;

public class BOJ1446 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    private static class Pair {
        int end, dist;
        Pair(int end, int dist) {
            this.end = end;
            this.dist = dist;
        }
    }
    private static class Shortcut {
        int start, end, dist;
        Shortcut(int start, int end, int dist) {
            this.start = start;
            this.end = end;
            this.dist = dist;
        }
    }
    public static void main(String[] args) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int d = Integer.parseInt(st.nextToken());
        
        int answer = dp(n, d);
        bw.write(Integer.toString(answer));
        bw.flush();
    }
    static ArrayList<Queue<Pair>> inputAsGraph(int n, int d) throws IOException {
        ArrayList<Queue<Pair>> graph = new ArrayList<>();
        for (int i = 0; i <= d; i++) {
            graph.add(new LinkedList<>());
        }
        for (int i = 0; i < d; i++) {
            graph.get(i).add(new Pair(i+1, 1));
        }
        while (n-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int dist = Integer.parseInt(st.nextToken());
            if (start > d) continue;
            graph.get(start).add(new Pair(end, dist));
        }
        return graph;
    }
    static int dijkstra(int n, int d) throws IOException{
        ArrayList<Queue<Pair>> graph = inputAsGraph(n, d);
        int[] dist = new int[d+1];
        Arrays.fill(dist, Integer.MAX_VALUE/2);
        dist[0] = 0;
        PriorityQueue<Pair> pq = new PriorityQueue<>((a, b) -> a.dist-b.dist);
        pq.add(new Pair(0, 0));
        while (!pq.isEmpty()) {
            Pair crnt = pq.poll();
            if (crnt.end == d) return dist[crnt.end];
            if (dist[crnt.end] < crnt.dist) continue;
            for (Pair adj : graph.get(crnt.end)) {
                if (adj.end > d || dist[adj.end] <= dist[crnt.end] + adj.dist) continue;
                dist[adj.end] = dist[crnt.end] + adj.dist;
                pq.add(new Pair(adj.end, dist[adj.end]));
            }
        }
        return -1;
    }
    static int dp(int n, int d) throws IOException {
        ArrayList<Shortcut> shortcut = new ArrayList<>();
        while (n-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int dist = Integer.parseInt(st.nextToken());
            if (end > d) continue;
            shortcut.add(new Shortcut(start, end, dist));
        }
        for (int i = 0; i < d; i++) {
            shortcut.add(new Shortcut(i, i+1, 1));
        }
        Collections.sort(shortcut, (a, b) -> b.end-a.end);
        int[] dp = new int[d+1];
        for (int i = 0; i <= d; i++) {
            dp[i] = d-i;
        }
        for (Shortcut sc : shortcut) {
            dp[sc.start] = Integer.min(dp[sc.start], dp[sc.end]+sc.dist);
        }
        return dp[0];
    }
}
