import java.io.*;
import java.util.*;

public class BOJ15709 {
    private static long INF = Long.MAX_VALUE >> 4;
    private static Queue<Edge> pq = new PriorityQueue<>((e1, e2) -> Long.compare(e1.weight, e2.weight));
    private static List<Queue<Edge>> graph;
    private static int n, m, b;
    private static class Edge {
        int end;
        long weight;
        Edge(int end, long weight) {
            this.end = end;
            this.weight = weight;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        b = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        int q = Integer.parseInt(st.nextToken());

        graph = new ArrayList<>();
        for (int i = 0; i <= n+m+b; i++) {
            graph.add(new ArrayDeque<>());
        }

        while (k-- > 0) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            long weight = Long.parseLong(st.nextToken());
            graph.get(start).add(new Edge(end, weight));
            graph.get(end).add(new Edge(start, weight));
        }

        long[][] dist = new long[b+1][n+m+b+1];
        for (int i = 1; i <= b; i++) {
            Arrays.fill(dist[i], INF);
        }
        for (int i = 1; i <= b; i++) {
            dijkstra(i, dist);
        }

        while (q-- > 0) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            long min = INF;
            for (int i = 1; i <= b; i++) {
                if (dist[i][start] + dist[i][end] < min) min = dist[i][start] + dist[i][end];
            }
            if (min == INF) bw.write("-1\n");
            else {
                bw.write(Long.toString(min));
                bw.newLine();
            }
        }
        bw.flush();
    }
    private static void dijkstra(int start, long[][] dist) {
        dist[start][n+m+start] = 0;
        pq.add(new Edge(n+m+start,0));
        while (!pq.isEmpty()) {
            Edge crnt = pq.poll();
            if (dist[start][crnt.end] < crnt.weight) continue;
            for (Edge adj : graph.get(crnt.end)) {
                if (dist[start][adj.end] <= dist[start][crnt.end] + adj.weight) continue;
                dist[start][adj.end] = dist[start][crnt.end] + adj.weight;
                pq.add(new Edge(adj.end, dist[start][adj.end]));
            }
        }
    }
}
