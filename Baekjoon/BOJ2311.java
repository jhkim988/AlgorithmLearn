
import java.io.*;
import java.util.*;

public class BOJ2311 {
    private static final int INF = Integer.MAX_VALUE >> 4;
    private static class Edge {
        int to, capacity, flow, cost;
        Edge reverse;

        Edge(int to, int capacity, int cost) {
            this.to = to;
            this.capacity = capacity;
            this.flow = 0;
            this.cost = cost;
        }

        void setReverse(Edge reverse) {
            this.reverse = reverse;
        }

        int residual() {
            return capacity - flow;
        }
    }
    private static class MCMF {
        private final int size, source, sink;
        private final int[] work, level, dist;
        private final boolean[] inQue;
        private final Queue<Integer> que = new ArrayDeque<>();
        private final List<Edge>[] graph;

        MCMF(int size, int source, int sink) {
            this.size = size;
            this.source = source;
            this.sink = sink;
            work = new int[size];
            level = new int[size];
            dist = new int[size];
            inQue = new boolean[size];
            graph = new ArrayList[size];
            for (int i = 0; i < size; i++) {
                graph[i] = new ArrayList<>();
            }
        }

        void add(int from, int to, int cap, int cost) {
            Edge edge = new Edge(to, cap, cost);
            Edge reverse = new Edge(from, 0, -cost);
            graph[from].add(edge);
            graph[to].add(reverse);
            edge.setReverse(reverse);
            reverse.setReverse(edge);
        }

        int run() {
            int minCost = 0;
            while (spfa()) {
                int sumFlow = 0;
                Arrays.fill(work, 0);
                while (true) {
                    int flowVal = dfs(source, INF);
                    if (flowVal == 0) break;
                    sumFlow += flowVal;
                }
                minCost += sumFlow*dist[sink];
            }
            return minCost;
        }

        private boolean spfa() {
            que.clear();
            Arrays.fill(level, 0);
            Arrays.fill(inQue, false);
            Arrays.fill(dist, INF);
            que.add(source);
            level[source] = 1;
            inQue[source] = true;
            dist[source] = 0;
            while (!que.isEmpty()) {
                int crnt = que.poll();
                inQue[crnt] = false;
                for (Edge next : graph[crnt]) {
                    if (dist[next.to] <= dist[crnt] + next.cost || next.residual() <= 0) continue;
                    level[next.to] = level[crnt] + 1;
                    dist[next.to] = dist[crnt] + next.cost;
                    if (inQue[next.to]) continue;
                    inQue[next.to] = true;
                    que.add(next.to);
                }
            }
            return dist[sink] != INF;
        }

        private int dfs(int crnt, int flowVal) {
            if (crnt == sink) return flowVal;
            for (; work[crnt] < graph[crnt].size(); work[crnt]++) {
                Edge next = graph[crnt].get(work[crnt]);
                if (level[next.to] != level[crnt]+1 || dist[next.to] != dist[crnt] + next.cost|| next.residual() <= 0) continue;
                int ret = dfs(next.to, Integer.min(flowVal, next.residual()));
                if (ret <= 0) continue;
                next.flow += ret;
                next.reverse.flow -= ret;
                return ret;
            }
            return 0;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        final int size = n+1;
        final int source = 0;
        final int sink = n;

        MCMF mcmf = new MCMF(size, source, sink);
        mcmf.add(source, 1, 2, 0);
        while (m-- > 0) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            mcmf.add(a, b, 1, c);
            mcmf.add(b, a, 1, c);
        }
        bw.write(Integer.toString(mcmf.run()));
        bw.flush();
    }
}
