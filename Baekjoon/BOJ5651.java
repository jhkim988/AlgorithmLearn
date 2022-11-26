import java.io.*;
import java.util.*;

public class BOJ5651 {
    private static final int INF = Integer.MAX_VALUE >> 4;
    private static class Edge {
        int from, to;
        Edge(int from, int to) {
            this.from = from;
            this.to = to;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o.getClass() != getClass()) return false;
            Edge e = (Edge) o;
            return this.from == e.from && this.to == e.to;
        }

        @Override
        public int hashCode() {
            return from * 307 + to;
        }
    }
    private static class MaxFlow {
        private final int size, source, sink;
        private final int[][] capacity, flow;
        private final int[][] numEdge;
        private final List<Queue<Integer>> graph;
        private final Set<Edge> edges;

        MaxFlow(int size, int source, int sink) {
            this.size = size;
            this.source = source;
            this.sink = sink;
            capacity = new int[size][size];
            flow = new int[size][size];
            numEdge = new int[size][size];
            graph = new ArrayList<>();
            for (int i = 0; i <= size; i++) {
                graph.add(new LinkedList<>());
            }
            edges = new HashSet<>();
        }

        void add(int from, int to, int cap) {
            graph.get(from).add(to);
            graph.get(to).add(from);
            capacity[from][to] += cap;
            numEdge[from][to]++;
            edges.add(new Edge(from, to));
        }

        boolean bfs(int[] par, int start, int end) {
            Queue<Integer> que = new LinkedList<>();
            que.add(start);
            while (!que.isEmpty()) {
                int crnt = que.poll();
                for (int next: graph.get(crnt)) {
                    if (par[next] != -1 || capacity[crnt][next] - flow[crnt][next]<= 0) continue;
                    par[next] = crnt;
                    que.add(next);
                    if (next == end) return true;
                }
            }
            return false;
        }

        int getMaxFlow() {
            int answer = 0;
            int[] par = new int[size];
            while (true) {
                Arrays.fill(par, -1);
                par[source] = -2;
                if (!bfs(par, source, sink)) break;
                int min = INF;
                for (int node = sink; node != source; node = par[node]) {
                    int f = capacity[par[node]][node] - flow[par[node]][node];
                    if (f < min) min = f;
                }
                for (int node = sink; node != source; node = par[node]) {
                    flow[par[node]][node] += min;
                    flow[node][par[node]] -= min;
                }
                answer += min;
            }
            return answer;
        }
        int numImportantEdge() {
            int answer = 0;
            getMaxFlow();
            for (Edge edge : edges) {
                int[] par = new int[size];
                Arrays.fill(par, -1);
                par[edge.from] = -2;
                if (!bfs(par, edge.from, edge.to)) {
                    answer += numEdge[edge.from][edge.to];
                }
            }
            return answer;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int numTest = Integer.parseInt(br.readLine());
        while (numTest-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            int m = Integer.parseInt(st.nextToken());
            MaxFlow maxFlow = new MaxFlow(n+1, 1, n);
            while (m-- > 0) {
                st = new StringTokenizer(br.readLine());
                int from = Integer.parseInt(st.nextToken());
                int to = Integer.parseInt(st.nextToken());
                int capacity = Integer.parseInt(st.nextToken());
                maxFlow.add(from, to, capacity);
            }
            bw.write(Integer.toString(maxFlow.numImportantEdge()));
            bw.newLine();
        }
        bw.flush();
    }
}
