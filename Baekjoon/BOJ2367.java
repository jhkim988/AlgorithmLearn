import java.io.*;
import java.util.*;

public class BOJ2367 {
    private static final int INF = Integer.MAX_VALUE >> 4;
    private static class Edge {
        int cap, flow;
        Edge(int cap, int flow) {
            this.cap = cap;
            this.flow = flow;
        }
        int residual() {
            return cap - flow;
        }
    }
    private static class MaxFlow {
        private final int size, source, sink;
        private List<Map<Integer, Edge>> graph; 
        MaxFlow(int size, int source, int sink) {
            this.size = size;
            this.source = source;
            this.sink = sink;
            graph = new ArrayList<>();
            for (int i = 0; i <= size; i++) {
                graph.add(new HashMap<>());
            }
        }

        public void add(int from, int to, int cap) {
            graph.get(from).put(to, new Edge(cap, 0));
            graph.get(to).put(from, new Edge(0, 0));
        }

        public int run() {
            int answer = 0;
            Queue<Integer> que = new LinkedList<>();
            int[] par = new int[size];
            while (true) {
                que.clear();
                Arrays.fill(par, -1);
                que.add(source);
                par[source] = -2;
                while (!que.isEmpty()) {
                    int crnt = que.poll();
                    for (Map.Entry<Integer, Edge> entry : graph.get(crnt).entrySet()) {
                        int next = entry.getKey();
                        Edge nextEdge = entry.getValue();
                        if (par[next] != -1 || nextEdge.residual() <= 0) continue;
                        par[next] = crnt;
                        que.add(next);
                        if (next == sink) break;
                    }
                }
                if (par[sink] == -1) break;
                int min = INF;
                for (int node = sink; node != source; node = par[node]) {
                    int f = graph.get(par[node]).get(node).residual();
                    if (f < min) min = f;
                }
                for (int node = sink; node != source; node = par[node]) {
                    graph.get(par[node]).get(node).flow += min;
                    graph.get(node).get(par[node]).flow -= min;
                }
                answer += min;
            }
            return answer;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        final int n = Integer.parseInt(st.nextToken());
        final int k = Integer.parseInt(st.nextToken());
        final int d = Integer.parseInt(st.nextToken());
        final int size = 2*n+2*d+2;
        final int source = 0;
        final int sink = 2*n+2*d+1;
        MaxFlow maxFlow = new MaxFlow(size, source, sink);
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= d; i++) {
            maxFlow.add(2*n+2*i-1, 2*n+2*i, Integer.parseInt(st.nextToken()));
            maxFlow.add(2*n+2*i, sink, INF);
        }
        for (int i = 1; i <= n; i++) {
            maxFlow.add(source, 2*i-1, INF);
            maxFlow.add(2*i-1, 2*i, k);
            st = new StringTokenizer(br.readLine());
            int num = Integer.parseInt(st.nextToken());
            while (num-- > 0) {
                int to = Integer.parseInt(st.nextToken());
                maxFlow.add(2*i, 2*n+2*to-1, 1);
            }
        }
        bw.write(Integer.toString(maxFlow.run()));
        bw.flush();
    }
}
