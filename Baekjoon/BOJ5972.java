import java.io.*;
import java.util.*;

public class BOJ5972 {
    private static final long INF = Long.MAX_VALUE/2;
    private static class Pair {
        int end;
        long weight;
        Pair (int end, long weight) {
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
            graph.add(new LinkedList<>());
        }
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            graph.get(a).add(new Pair(b, w));
            graph.get(b).add(new Pair(a, w));
        }

        long min = minDist(graph);
        bw.write(Long.toString(min));
        bw.flush();
    }
    private static long minDist(List<Queue<Pair>> graph) {
        int n = graph.size()-1;
        long[] dist = new long[n+1];
        Arrays.fill(dist, INF);

        dist[1] = 0;
        PriorityQueue<Pair> pq = new PriorityQueue<>((a, b) -> Long.compare(a.weight, b.weight));
        pq.add(new Pair(1, 0));
        while (!pq.isEmpty()) {
            Pair crnt = pq.poll();
            if (dist[crnt.end] < crnt.weight) continue;
            for (Pair adj : graph.get(crnt.end)) {
                if (dist[adj.end] <= dist[crnt.end] + adj.weight) continue;
                dist[adj.end] = dist[crnt.end] + adj.weight;
                pq.add(new Pair(adj.end, dist[adj.end]));
            }
        } 
        return dist[n];
    }
}
