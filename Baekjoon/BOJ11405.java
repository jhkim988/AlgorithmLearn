import java.io.*;
import java.util.*;

public class BOJ11405 {
    private static final int INF = Integer.MAX_VALUE >> 2;
    private static class MCMF {
        private final int SIZE;
        private final int SOURCE ;
        private final int SINK ;
        private final int[][] capacity;
        private final int[][] costs;
        private final List<Queue<Integer>> graph;

        MCMF(int size, int source, int sink) {
            this.SIZE = size;
            this.SOURCE = source;
            this.SINK = sink;

            capacity = new int[SIZE][SIZE];
            costs = new int[SIZE][SIZE];
            graph = new ArrayList<>();
            for (int i = 0; i <= SIZE ; i++) {
                graph.add(new LinkedList<>());
            }
        }

        public void add(int from, int to, int cap, int cost, boolean isDirected) {
            graph.get(from).add(to);
            graph.get(to).add(from);
            costs[from][to] += cost;
            costs[to][from] -= cost;
            capacity[from][to] += cap;
            if (!isDirected) capacity[to][from] += cap;
        }

        public int minCost() {
            int minCost = 0;
            Queue<Integer> que = new LinkedList<>();
            int[] dist = new int[SIZE];
            boolean[] inQue = new boolean[SIZE];
            int[] numVisit = new int[SIZE];
            int[] par = new int[SIZE];
            int[][] flow = new int[SIZE][SIZE];
            while (true) {
                que.clear();
                Arrays.fill(dist, INF);
                Arrays.fill(inQue, false);
                Arrays.fill(numVisit, 0);
                Arrays.fill(par, -1);
                que.add(SOURCE);
                dist[SOURCE] = 0;
                inQue[SOURCE] = true;
                numVisit[SOURCE] = 1;
                par[SOURCE] = -2;
                while (!que.isEmpty()) {
                    int crnt = que.poll();
                    inQue[crnt] = false;
                    for (int next : graph.get(crnt)) {
                        if (dist[next] <= dist[crnt] + costs[crnt][next]) continue;
                        if (capacity[crnt][next] - flow[crnt][next] <= 0) continue;
                        dist[next] = dist[crnt] + costs[crnt][next];
                        par[next] = crnt;
                        if (!inQue[next]) {
                            numVisit[next]++;
                            inQue[next] = true;
                            que.add(next);
                            if (numVisit[next] >= SIZE) return minCost;
                        }
                        if (next == SINK) break;
                    }
                }
                if (par[SINK] == -1) break;
                int maxFlow = INF;
                for (int node = SINK; node != SOURCE; node = par[node]) {
                    int f = capacity[par[node]][node] - flow[par[node]][node];
                    if (f < maxFlow) maxFlow = f;
                }
                for (int node = SINK; node != SOURCE; node = par[node]) {
                    flow[par[node]][node] += maxFlow;
                    flow[node][par[node]] -= maxFlow;
                }
                minCost += maxFlow*dist[SINK];
            }
            return minCost;
        }
    } 
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        final int n = Integer.parseInt(st.nextToken());
        final int m = Integer.parseInt(st.nextToken());
        final int size = 2*n+2*m+2;
        final int SOURCE = 2*n+2*m+1;
        final int SINK = 0;
        MCMF mcmf = new MCMF(size, SOURCE, SINK);
        
        for (int i = 1; i <= n; i++) {
            mcmf.add(2*i-1, SINK, INF, 0 , true);
        }

        for (int i = 1; i <= m; i++) {
            mcmf.add(SOURCE, 2*i+2*n, INF, 0, true);
        }

        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= n; i++) {
            int cap = Integer.parseInt(st.nextToken());
            mcmf.add(2*i, 2*i-1, cap, 0, true);
        }

        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= m; i++) {
            int cap = Integer.parseInt(st.nextToken());
            mcmf.add(2*i+2*n, 2*i-1+2*n, cap, 0, true);
        }

        for (int i = 1; i <= m; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= n; j++) {
                int cost = Integer.parseInt(st.nextToken());
                mcmf.add(2*i+2*n-1, 2*j, INF, cost, true);
            }
        }

        int val = mcmf.minCost();
        bw.write(Integer.toString(val));
        bw.flush();
    }
}
