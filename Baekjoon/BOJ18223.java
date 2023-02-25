import java.io.*;
import java.util.*;

public class BOJ18223 {
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
        int v = Integer.parseInt(st.nextToken());
        int e = Integer.parseInt(st.nextToken());
        int p = Integer.parseInt(st.nextToken());

        List<Queue<Pair>> graph = new ArrayList<>();
        for (int i = 0; i <= v; i++) {
            graph.add(new ArrayDeque<>());
        }

        while (e-- > 0) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());
            graph.get(start).add(new Pair(end, weight));
            graph.get(end).add(new Pair(start, weight));
        }

        int[] dist = dijkstra(graph, 1, v);
        boolean answer = bfs(graph, v, 1, p, dist);
        bw.write(answer ? "SAVE HIM" : "GOOD BYE");
        bw.flush();
    }
    private static int[] dijkstra(List<Queue<Pair>> graph, int start, int end) {
        int[] dist = new int[graph.size()];
        Arrays.fill(dist, INF);
        PriorityQueue<Pair> pq = new PriorityQueue<>((p1, p2) -> Integer.compare(p1.weight, p2.weight));
        pq.add(new Pair(start, 0));
        dist[start] = 0;
        
        while (!pq.isEmpty()) {
            Pair crnt = pq.poll();
            if (dist[crnt.end] < crnt.weight) continue;
            for (Pair adj : graph.get(crnt.end)) {
                if (dist[adj.end] <= dist[crnt.end] + adj.weight) continue;
                dist[adj.end] = dist[crnt.end] + adj.weight;
                pq.add(new Pair(adj.end, dist[adj.end]));
            }
        }

        return dist;
    }
    private static boolean bfs(List<Queue<Pair>> graph, int start, int end, int via, int[] dist) {
        if (start == via || end == via) return true;
        boolean[] visit = new boolean[graph.size()];
        Queue<Integer> que = new ArrayDeque<>();
        visit[start] = true;
        que.add(start);
        while (!que.isEmpty()) {
            int crnt = que.poll();
            for (Pair adj : graph.get(crnt)) {
                if (visit[adj.end] || dist[adj.end] + adj.weight != dist[crnt]) continue;
                if (adj.end == via) return true;
                visit[adj.end] = true;
                que.add(adj.end);
            }
        }
        return false;
    }
}