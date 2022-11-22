import java.io.*;
import java.util.*;

public class BOJ11409 {
    private static final int INF = Integer.MAX_VALUE >> 2;
    private static class MCMF {
        private final int size, source, sink;
        private final int[][] capacity, costs;
        private final List<Queue<Integer>> graph;
        private int maxFlow = 0;
        private int minCost = 0;

        public MCMF(int size, int source, int sink) {
            this.size = size; 
            this.source = source;
            this.sink = sink;
            capacity = new int[size][size];
            costs = new int[size][size];
            graph = new ArrayList<>();
            for (int i = 0; i <= size; i++) {
                graph.add(new LinkedList<>());
            }
        }

        public void add(int from, int to, int cap, int cost, boolean isDirected) {
            graph.get(from).add(to);
            graph.get(to).add(from);
            costs[from][to] +=  cost;
            costs[to][from] -= cost;
            capacity[from][to] += cap;
            if (!isDirected) capacity[to][from] += cap;
        }

        public void maxFlow() {
            maxFlow = 0;
            minCost = 0;
            Queue<Integer> que = new LinkedList<>();
            int[][] flow = new int[size][size];
            int[] par = new int[size];
            int[] dist = new int[size];
            boolean[] inQue = new boolean[size];
            int[] numVisit = new int[size];
            while (true) {
                que.clear();
                Arrays.fill(par, -1);
                Arrays.fill(dist, INF);
                Arrays.fill(inQue, false);
                Arrays.fill(numVisit, 0);
                que.add(source);
                par[source] = -2;
                dist[source] = 0;
                inQue[source] = true;
                numVisit[source] = 1;
                while (!que.isEmpty()) {
                    int crnt = que.poll();
                    for (int next : graph.get(crnt)) {
                        if (dist[next] <= dist[crnt] + costs[crnt][next]) continue;
                        if (capacity[crnt][next] - flow[crnt][next] <= 0) continue;
                        par[next] = crnt;
                        dist[next] = dist[crnt] + costs[crnt][next];
                        if (next == sink) break;
                        if (!inQue[next]) {
                            numVisit[next]++;
                            que.add(next);
                            if (numVisit[next] >= size) return; // has negative cycle
                        }
                    }
                }
                if (par[sink] == -1) break;
                int min = INF;
                for (int node = sink; node != source; node = par[node]) {
                    int f = capacity[par[node]][node] - flow[par[node]][node];
                    if (f < min) min = f;
                }
                for (int node = sink; node != source; node = par[node]) {
                    flow[par[node]][node] += min;
                    flow[node][par[node]] -= min;
                }
                maxFlow += min;
                minCost += dist[sink];
            }
        }

        public int getMaxFlow() {
            return maxFlow;
        }

        public int getMinCost() {
            return minCost;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int size = m+n+2;
        int source = 0;
        int sink = m+n+1;
        MCMF mcmf = new MCMF(size, source, sink);
        for (int i = 1; i <= n; i++) {
            mcmf.add(source, i, 1, 0, true);
        }
        for (int i = 1; i <= m; i++) {
            mcmf.add(i+n, sink, 1, 0, true);
        }
        for (int i = 1; i <= n; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            while (x-- > 0) {
                int work = Integer.parseInt(st.nextToken());
                int salary = Integer.parseInt(st.nextToken());
                mcmf.add(i, work+n, 1, -salary, true);
            }
        }

        mcmf.maxFlow();
        bw.write(Integer.toString(mcmf.getMaxFlow()));
        bw.newLine();
        bw.write(Integer.toString(-mcmf.getMinCost()));
        bw.flush();
    }
}
