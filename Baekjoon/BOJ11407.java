import java.io.*;
import java.util.*;

public class BOJ11407 {
    private static final int INF = Integer.MAX_VALUE >> 4;
    private static class Edge {
        int cap, cost, flow;
        Edge(int cap, int cost, int flow) {
            this.cap = cap;
            this.cost = cost;
            this.flow = flow;
        }

        public int residual() {
            return cap - flow;
        }
    }
    private static class MCMF {
        private final int size, source, sink;
        private int minCost = 0, maxFlow = 0;
        private List<Map<Integer, Edge>> graph;

        MCMF(int size, int source, int sink) {
            this.size = size;
            this.source = source;
            this.sink = sink;
            graph = new ArrayList<>();
            for (int i = 0; i <= size; i++) {
                graph.add(new HashMap<>());
            }
        }

        public void add(int from, int to, int cap, int cost) {
            graph.get(from).put(to, new Edge(cap, cost, 0));
            graph.get(to).put(from, new Edge(0, -cost, 0));
        }

        public void run() {
            minCost = 0;
            maxFlow = 0;
            Queue<Integer> que = new LinkedList<>();
            boolean[] inQue = new boolean[size];
            int[] dist = new int[size];
            int[] par = new int[size];
            int[] numVisit = new int[size];
            while (true) {
                que.clear();
                Arrays.fill(inQue, false);
                Arrays.fill(dist, INF);
                Arrays.fill(par, -1);
                Arrays.fill(numVisit, 0);
                que.add(source);
                dist[source] = 0;
                par[source] = -2;
                numVisit[source] = 1;
                while (!que.isEmpty()) {
                    int crnt = que.poll();
                    inQue[crnt] = false;
                    for (Map.Entry<Integer, Edge> nextEntry : graph.get(crnt).entrySet()) {
                        int next = nextEntry.getKey();
                        Edge nextEdge = nextEntry.getValue();
                        if (nextEdge.residual() <= 0) continue;
                        if (dist[next] <= dist[crnt] + nextEdge.cost) continue;
                        dist[next] = dist[crnt] + nextEdge.cost;
                        par[next] = crnt;
                        if (!inQue[next]) {
                            inQue[next] = true;
                            numVisit[next]++;
                            if (numVisit[next] >= size) return;
                            que.add(next);
                        }
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
                minCost += dist[sink]*min;
                maxFlow += min;
            }
        }
    
        public int getMinCost() {
            return minCost;
        }

        public int getMaxFlow() {
            return maxFlow;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        final int n = Integer.parseInt(st.nextToken());
        final int m = Integer.parseInt(st.nextToken());
        final int size = 2*n+2*m+2;
        final int source = 0;
        final int sink = 2*n+2*m+1;
        
        MCMF mcmf = new MCMF(size, source, sink);
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= n; i++) {
            int val = Integer.parseInt(st.nextToken());
            mcmf.add(i*2, sink, INF, 0);
            mcmf.add(i*2-1, i*2, val, 0);
        }
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= m; i++) {
            int val = Integer.parseInt(st.nextToken());
            mcmf.add(source, i*2+2*n-1, INF, 0);
            mcmf.add(i*2+2*n-1, i*2+2*n, val, 0);
        }
        for (int i = 1; i <= m; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= n; j++) {
                int val = Integer.parseInt(st.nextToken());
                mcmf.add(i*2+2*n, 2*j-1, INF, val);
            }
        }

        mcmf.run();
        bw.write(Integer.toString(mcmf.getMinCost()));
        bw.flush();
    }
}
