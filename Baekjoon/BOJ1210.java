import java.io.*;
import java.util.*;

public class BOJ1210 {
    private static final long INF = Long.MAX_VALUE >> 4; 
    private static class Edge {
        int to, flow;
        long cap;
        Edge reverse;
        Edge(int to, long cap) {
            this.to = to;
            this.cap = cap;
            this.flow = 0;
        }

        void setReverse(Edge reverse) {
            this.reverse = reverse;
        }

        long residual() {
            return cap-flow;
        }
    }
    private static class MaxFlow {
        private final int size, source, sink;
        private final int[] level, work;
        private final Queue<Integer> que = new ArrayDeque<>();
        private final List<Edge>[] graph;
        MaxFlow(int size, int source, int sink) {
            this.size = size;
            this.source = source;
            this.sink = sink;

            level = new int[size];
            work = new int[size];

            graph = new ArrayList[size];
            for (int i = 0; i < size; i++) {
                graph[i] = new ArrayList<>();
            }
        }

        void add(int from, int to, long cap) {
            Edge edge = new Edge(to, cap);
            Edge reverse = new Edge(from, 0);
            graph[from].add(edge);
            graph[to].add(reverse);
            edge.setReverse(reverse);
            reverse.setReverse(edge);
        }

        long run() {
            long maxFlow = 0;
            while (bfs(source, sink)) {
                Arrays.fill(work, 0);
                while (true) {
                    long flowVal = dfs(source, INF);
                    if (flowVal == 0) break;
                    maxFlow += flowVal;
                }
            }
            return maxFlow;
        }

        boolean bfs(int start, int end) {
            que.clear();
            Arrays.fill(level, 0);
            que.add(start);
            level[start] = 1;
            while (!que.isEmpty()) {
                int crnt = que.poll();
                for (Edge next : graph[crnt]) {
                    if (level[next.to] != 0 || next.residual() <= 0) continue;
                    level[next.to] = level[crnt]+1;
                    que.add(next.to);
                    if (next.to == end) return true;
                }
            }
            return false;
        }

        long dfs(int crnt, long flowVal) {
            if (crnt == sink) return flowVal;
            for (; work[crnt] < graph[crnt].size(); work[crnt]++) {
                Edge next = graph[crnt].get(work[crnt]);
                if (level[next.to] != level[crnt]+1 || next.residual() <= 0) continue;
                long ret = dfs(next.to, Long.min(flowVal, next.residual()));
                if (ret <= 0) continue;
                next.flow += ret;
                next.reverse.flow -= ret;
                return ret;
            }
            return 0L;
        }
        List<Integer> cut() {
            List<Integer> ret = new ArrayList<>();
            Set<Integer> sourceSet = new HashSet<>();
            for (int i = 0; i < size; i++) {
                if (bfs(i, sink)) continue;
                sourceSet.add(i);
            }
            for (int i = 0; i < size; i+=2) {
                if (sourceSet.contains(i) && !sourceSet.contains(i+1)) ret.add(i/2+1);
            }
            return ret;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        
        st = new StringTokenizer(br.readLine());
        int start = Integer.parseInt(st.nextToken())-1;
        int end = Integer.parseInt(st.nextToken())-1;
        
        final int size = 2*n;
        final int source = start*2;
        final int sink = end*2+1;

        MaxFlow maxFlow = new MaxFlow(size, source, sink);
        for (int i = 0; i < n; i++) {
            maxFlow.add(i*2, i*2+1, Integer.parseInt(br.readLine()));
        }
        while (m-- > 0) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken())-1;
            int e = Integer.parseInt(st.nextToken())-1;
            maxFlow.add(s*2+1, e*2, INF);
            maxFlow.add(e*2+1, s*2, INF);
        }
        maxFlow.run();

        StringBuilder sb = new StringBuilder();
        List<Integer> cut = maxFlow.cut();
        cut.stream().sorted().forEach(x -> {
            sb.append(Integer.toString(x));
            sb.append(' ');
        });
        bw.write(sb.toString());
        bw.flush();
    }
}
