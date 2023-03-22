import java.io.*;
import java.util.*;

public class BOJ13308 {
    private static class Pair {
        int end, cost;
        long dist;
        Pair(int end, int cost, long dist) {
            this.end = end;
            this.cost = cost;
            this.dist = dist;
        }
    }
    private static int[] price;
    private static List<Queue<Pair>> graph;
    private static int n;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        
        st = new StringTokenizer(br.readLine());
        price = new int[n+1];
        for (int i = 1; i <= n; i++) {
            price[i] = Integer.parseInt(st.nextToken());
        }

        graph = new ArrayList<>();
        for (int i = 0; i <= n; i++) {
            graph.add(new ArrayDeque<>());
        }
        while (m-- > 0) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int dist = Integer.parseInt(st.nextToken());
            graph.get(start).add(new Pair(end, 0, dist));
            graph.get(end).add(new Pair(start, 0, dist));
        }

        bw.write(Long.toString(dijkstra()));
        bw.flush();
    }

    private static long dijkstra() {
        PriorityQueue<Pair> pq = new PriorityQueue<>((p1, p2) -> Long.compare(p1.dist, p2.dist));
        pq.add(new Pair(1, price[1], 0));

        boolean[][] check = new boolean[2501][2501];
        while (!pq.isEmpty()) {
            Pair crnt = pq.poll();
            int idx = crnt.end;
            int cost = crnt.cost;
            long dist = crnt.dist;
            
            if (check[idx][cost]) continue;
            if (idx == n) return dist;

            check[idx][cost] = true;
            for (Pair adj : graph.get(idx)) {
                pq.add(new Pair(adj.end, Integer.min(cost, price[adj.end]), cost*adj.dist + dist));
            }
        }
        return -1;
    }
}
