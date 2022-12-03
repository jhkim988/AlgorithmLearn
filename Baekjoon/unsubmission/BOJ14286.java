package unsubmission;
import java.io.*;
import java.util.*;

public class BOJ14286 {
    private static final int INF = Integer.MAX_VALUE >> 4;
    private static class Edge {
        int to, capacity, flow;
        Edge reverse;
        Edge(int to, int capacity) {
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
        private int size, source, sink;
        private final int[] work, level;
        private final boolean[] inQue;
        private final Queue<Integer> que = new ArrayDeque<>();
        private final List<Edge>[] graph;
        MaxFlow(int size) {
            this.size = size;
            this.source = source;
            this.sink = sink;

            work = new int[size];
            level = new int[size];
            inQue = new boolean[size];
            graph = new ArrayList[size];
            for (int i = 0; i < size; i++) {
                graph[i] = new ArrayList<>();
            }
        }
        void set(int source, int sink) {
            this.source = source;
            this.sink = sink;
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
            int maxFlow = 0;
            while (bfs()) {
                Arrays.fill(work, 0);
                while (true) {
                    int flowVal = dfs(source, INF);
                    if (flowVal <= 0) break;
                    maxFlow += flowVal;
                }
            }
            return maxFlow;
        }

        private boolean bfs() {
            que.clear();
            Arrays.fill(level, 0);
            Arrays.fill(inQue, false);
            que.add(source);
            level[source] = 1;
            inQue[source] = true;
            while (!que.isEmpty()) {
                int crnt = que.poll();
                inQue[crnt] = false;
                for (Edge next : graph[crnt]) {
                    if (level[next.to] != 0 || next.residual() <= 0) continue;
                    level[next.to] = level[crnt] + 1;
                    if (next.to == sink) return true;
                    if (inQue[next.to]) continue;
                    inQue[next.to] = true;
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
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int numV = Integer.parseInt(st.nextToken());
        int numE = Integer.parseInt(st.nextToken());
        
        MaxFlow maxFlow = new MaxFlow(numV+1);

        while (numE-- > 0) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            maxFlow.add(a, b, c);
            maxFlow.add(b, a, c);
        }
        st = new StringTokenizer(br.readLine());
        int start = Integer.parseInt(st.nextToken());
        int end = Integer.parseInt(st.nextToken());
        maxFlow.set(start, end);
        
        bw.write(Integer.toString(maxFlow.run()));
        bw.flush();
    }
}
