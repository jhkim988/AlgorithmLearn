import java.io.*;
import java.util.*;

public class BOJ14284 {
    private static int INF = Integer.MAX_VALUE >> 2;
    private static class Pair {
        int end, weight;
        Pair(int end, int weight) {
            this.end = end;
            this.weight = weight;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        List<Queue<Pair>> graph = new ArrayList<>();
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

        st = new StringTokenizer(br.readLine());
        int s = Integer.parseInt(st.nextToken());
        int t = Integer.parseInt(st.nextToken());
        int answer = dijkstra(graph, s, t);

        bw.write(Integer.toString(answer));
        bw.flush();
    }
    
    private static int dijkstra(List<Queue<Pair>> graph, int start, int end) {
        int[] dist = new int[graph.size()];
        Arrays.fill(dist, INF);
        dist[start] = 0;
        PriorityQueue<Pair> pq = new PriorityQueue<>((p1, p2) -> Integer.compare(p1.weight, p2.weight));
        pq.add(new Pair(start, 0));
        while (!pq.isEmpty()) {
            Pair crnt = pq.poll();
            if (dist[crnt.end] < crnt.weight) continue;
            for (Pair adj : graph.get(crnt.end)) {
                if (dist[adj.end] <= dist[crnt.end] + adj.weight) continue;
                dist[adj.end] = dist[crnt.end] + adj.weight;
                pq.add(new Pair(adj.end, dist[adj.end]));
            }
        }
        return dist[end];
    }
}