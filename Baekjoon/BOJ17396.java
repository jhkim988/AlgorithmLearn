import java.io.*;
import java.util.*;

public class BOJ17396 {
    private static class Pair {
        int end;
        long weight;
        Pair(int end, long weight) {
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
        
        st = new StringTokenizer(br.readLine());
        int[] vision = new int[n];
        for (int i = 0; i < n; i++) {
            vision[i] = Integer.parseInt(st.nextToken());
        }
        vision[n-1] = 0;

        List<Queue<Pair>> graph = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            graph.add(new ArrayDeque<>());
        }
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());
            if (vision[start] == 1 || vision[end] == 1) continue;
            graph.get(start).add(new Pair(end, weight));
            graph.get(end).add(new Pair(start, weight));
        }

        long answer = dijkstra(graph);
        bw.write(Long.toString(answer));
        bw.flush();
    }

    private static long dijkstra(List<Queue<Pair>> graph) {
        long INF = Long.MAX_VALUE >> 3;
        long[] dist = new long[graph.size()];
        Arrays.fill(dist, INF);
        PriorityQueue<Pair> pq = new PriorityQueue<>((p1, p2) -> Long.compare(p1.weight, p2.weight));
        dist[0] = 0;
        pq.add(new Pair(0, 0));
        while (!pq.isEmpty()) {
            Pair crnt = pq.poll();
            if (dist[crnt.end] < crnt.weight) continue;
            for (Pair adj : graph.get(crnt.end)) {
                if (dist[adj.end] <= dist[crnt.end] + adj.weight) continue;
                dist[adj.end] = dist[crnt.end] + adj.weight;
                pq.add(new Pair(adj.end, dist[adj.end]));
            }
        }
        return dist[graph.size()-1] == INF ? -1 : dist[graph.size()-1];
    }
}
