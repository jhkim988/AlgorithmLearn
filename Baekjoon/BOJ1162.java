import java.io.*;
import java.util.*;

public class BOJ1162 {
    private static final long INF = Long.MAX_VALUE/2;
    private static class Pair {
        int end, numZero;
        long weight;
        Pair(int end, long weight, int numZero) {
            this.end = end;
            this.weight = weight;
            this.numZero = numZero;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw =  new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        ArrayList<Queue<Pair>> graph = new ArrayList<>();
        for (int i = 0; i <= n ; i++) {
            graph.add(new LinkedList<>());
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            graph.get(a).add(new Pair(b, e, 0));
            graph.get(b).add(new Pair(a, e, 0));
        }

        long minDist = dijkstra(graph, 1, n, k);
        
        bw.write(Long.toString(minDist));
        bw.flush();
    }
    private static long dijkstra(ArrayList<Queue<Pair>> graph, int start, int end, int limitZero) {
        int n = graph.size()-1;
        long[][] dist = new long[n+1][limitZero+1];
        for (int i = 0; i <= n; i++) {
            Arrays.fill(dist[i], INF);
        }
        dist[start][0] = 0;

        PriorityQueue<Pair> pq = new PriorityQueue<>((a, b) -> sign(a.weight - b.weight));
        pq.add(new Pair(start, 0, 0));

        while (!pq.isEmpty()) {
            Pair crnt = pq.poll();
            if (dist[crnt.end][crnt.numZero] < crnt.weight) continue;

            for (Pair adj : graph.get(crnt.end)) {
                if (dist[adj.end][crnt.numZero] <= dist[crnt.end][crnt.numZero] + adj.weight) continue;
                dist[adj.end][crnt.numZero] = dist[crnt.end][crnt.numZero] + adj.weight;
                pq.add(new Pair(adj.end, dist[adj.end][crnt.numZero], crnt.numZero));
            }

            for (Pair adj : graph.get(crnt.end)) {
                if (crnt.numZero >= limitZero) continue;
                if (dist[adj.end][crnt.numZero+1] <= dist[crnt.end][crnt.numZero]) continue;
                dist[adj.end][crnt.numZero+1] = dist[crnt.end][crnt.numZero];
                pq.add(new Pair(adj.end, dist[adj.end][crnt.numZero+1], crnt.numZero+1));
            }
        }

        long min = INF;
        for (int i = 0; i <= limitZero; i++) {
            min = Long.min(min, dist[end][i]);
        }
        return min;
    }
    private static int sign(long a) {
        if (a < 0) return -1;
        if (a > 0) return 1;
        return 0;
    }
}
