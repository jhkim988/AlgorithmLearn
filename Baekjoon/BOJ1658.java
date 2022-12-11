import java.io.*;
import java.util.*;

public class BOJ1658 {
    private static int n, m;
    private static final int INF = Integer.MAX_VALUE >> 4;
    private static class Edge {
        int to, capacity, flow;
        Edge reverse;
        Edge (int to, int capacity) {
            this.to = to;
            this.capacity = capacity;
            this.flow = 0;
        }

        void setReverse(Edge reverse) {
            this.reverse = reverse;
        }

        int residual() {
            return capacity - flow;
        }
    }
    private static class MaxFlow {
        private final int size, source, sink;
        private final int[] work, level;
        private final Queue<Integer> que = new ArrayDeque<>();
        private List<Edge>[] graph;
        MaxFlow(int size, int source, int sink) {
            this.size = size;
            this.source = source;
            this.sink = sink;

            work = new int[size];
            level = new int[size];
            graph = new ArrayList[size];
            for (int i = 0; i < size; i++) {
                graph[i] = new ArrayList<>();
            }
        }

        void add(int from, int to, int capacity) {
            Edge edge = new Edge(to, capacity);
            Edge reverse = new Edge(from, 0);
            graph[from].add(edge);
            graph[to].add(reverse);
            edge.setReverse(reverse);
            reverse.setReverse(edge);
        }

        int run() {
            redayToRun();
            int maxFlow = 0;
            while (bfs()) {
                Arrays.fill(work, 0);
                while (true) {
                    int flowVal = dfs(source, INF);
                    if (flowVal == 0) break;
                    maxFlow += flowVal;
                }
            }
            return maxFlow;
        }

        private boolean bfs() {
            que.clear();
            Arrays.fill(level, 0);
            que.add(source);
            level[source] = 1;
            while (!que.isEmpty()) {
                int crnt = que.poll();
                for (Edge next : graph[crnt]) {
                    if (level[next.to] != 0 || next.residual() <= 0) continue;
                    level[next.to] = level[crnt] + 1;
                    if (next.to == sink) return true;
                    que.add(next.to);
                }
            }
            return false;
        }

        private int dfs(int crnt, int flowVal) {
            if (crnt == sink) return flowVal;
            for (; work[crnt] < graph[crnt].size(); work[crnt]++) {
                Edge next = graph[crnt].get(work[crnt]);
                if (level[next.to] != level[crnt]+1 || next.residual() <= 0) continue;
                int ret = dfs(next.to, Integer.min(flowVal, next.residual()));
                if (ret <= 0) continue;
                next.flow += ret;
                next.reverse.flow -= ret;
                return ret;
            }
            return 0;
        }

        private void redayToRun() {
            for (int i = 1; i <= m; i++) {
                List<Edge> edges = graph[i+n];
                for (int j = edges.size()-1; j > 1; j--) {
                    add(graph[i+n].get(j).to, graph[i+n].get(j-1).to, INF);
                }
            }
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        m = Integer.parseInt(st.nextToken());
        n = Integer.parseInt(st.nextToken());

        final int size = n+m+2;
        final int source = 0;
        final int sink = n+m+1;

        MaxFlow maxFlow = new MaxFlow(size, source, sink);
        
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= m; i++) {
            maxFlow.add(n+i, sink, Integer.parseInt(st.nextToken()));
        }

        for (int i = 1; i <= n; i++) {
            st = new StringTokenizer(br.readLine());
            int num = Integer.parseInt(st.nextToken());
            while (num-- > 0) {
                int pigHouse = Integer.parseInt(st.nextToken()) + n;
                maxFlow.add(i, pigHouse, INF);
            }
            maxFlow.add(source, i, Integer.parseInt(st.nextToken()));
        }
        bw.write(Integer.toString(maxFlow.run()));
        bw.flush();
    }
}
