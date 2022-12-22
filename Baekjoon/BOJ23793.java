import java.io.*;
import java.util.*;

public class BOJ23793 {
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

        List<List<Pair>> graph = new ArrayList<>();
        for (int i = 0; i <= n; i++) {
            graph.add(new LinkedList<>());
        }

        while (m-- > 0) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());
            graph.get(start).add(new Pair(end, weight));
        }

        st = new StringTokenizer(br.readLine());
        int start = Integer.parseInt(st.nextToken());
        int via = Integer.parseInt(st.nextToken());
        int end = Integer.parseInt(st.nextToken());

        long path1 = dijkstra(start, via, graph);
        long path2 = dijkstra(via, end, graph);
        long answer1 = path1 != -1 && path2 != -1 ? path1+path2 : -1;
        graph.get(via).clear();
        long answer2 = dijkstra(start, end, graph);
    
        bw.write(Long.toString(answer1));
        bw.write(' ');
        bw.write(Long.toString(answer2));
        bw.flush();
    }
    private static final long INF = Long.MAX_VALUE >> 4;
    private static long dijkstra(int start, int end, List<List<Pair>> graph) {
        long[] dist = new long[graph.size()+1];
        Arrays.fill(dist, INF);
        dist[start] = 0;

        PriorityQueue<Pair> pq = new PriorityQueue<>((pair1, pair2) -> Long.compare(pair1.weight, pair2.weight));
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
        return dist[end] == INF ? -1 : dist[end];
    }
}