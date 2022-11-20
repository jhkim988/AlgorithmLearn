import java.io.*;
import java.util.*;

public class BOJ11408 {
    private static final int INF = Integer.MAX_VALUE >> 1;

    private static class MCMF {
        private final int SOURCE;
        private final int SINK;
        private final int size;
        private int[][] costArr, capacityArr;
        private List<Queue<Integer>> graph;

        private int minimumCost = 0;
        private int maximumFlow = 0;

        public MCMF(final int SOURCE, final int SINK, int size) {
            this.SOURCE = SOURCE;
            this.SINK = SINK;
            this.size = size;

            this.costArr = new int[size][size];
            this.capacityArr = new int[size][size];

            graph = new ArrayList<>();
            for (int i = 0; i <= size; i++) {
                graph.add(new LinkedList<>());
            }
        }

        public void add(int node1, int node2, int cost, int capacity, boolean isDirect) {
            graph.get(node1).add(node2);
            graph.get(node2).add(node1);
            costArr[node1][node2] = cost;
            costArr[node2][node1] = -cost;
            capacityArr[node1][node2] = capacity;
            if (!isDirect) capacityArr[node2][node1] = capacity;
        }

        public void run() {
            Queue<Integer> que = new LinkedList<>();
            int[][] flow = new int[size][size];
            int[] par = new int[size];
            int[] minCost = new int[size];
            int[] numVisit = new int[size];
            boolean[] inQue = new boolean[size];
            while (true) {
                que.clear();
                Arrays.fill(par, -1);
                Arrays.fill(minCost, INF);
                Arrays.fill(numVisit, 0);
                Arrays.fill(inQue, false);
                que.add(SOURCE);
                par[SOURCE] = -2;
                minCost[SOURCE] = 0;
                numVisit[SOURCE] = 1;
                inQue[SOURCE] = true;
                // SPFA
                while (!que.isEmpty()) {
                    int crnt = que.poll();
                    inQue[crnt] = false;
                    for (int next : graph.get(crnt)) {
                        if (minCost[next] <= minCost[crnt] + costArr[crnt][next]) continue;
                        if (capacityArr[crnt][next] - flow[crnt][next] <= 0) continue;
                        minCost[next] = minCost[crnt] + costArr[crnt][next];
                        par[next] = crnt;
                        if (!inQue[next]) {
                            numVisit[next]++;
                            que.add(next);
                            inQue[next] = true;
                            if (numVisit[next] >= size) return;
                        }
                        if (next == SINK) break;
                    }
                }
                if (par[SINK] == -1) break;
                
                int minFlow = INF;
                for (int node = SINK; node != SOURCE; node = par[node]) {
                    int f = capacityArr[par[node]][node] - flow[par[node]][node];
                    if (f < minFlow) minFlow = f;
                }
                // System.out.println("path start");
                for (int node = SINK; node != SOURCE; node = par[node]) {
                    flow[par[node]][node] += minFlow;
                    flow[node][par[node]] -= minFlow;
                    // System.out.println(par[node] + "->" + node);
                }
                minimumCost += minCost[SINK];
                maximumFlow += minFlow;
            }
        }

        public int getMinimumCost() {
            return minimumCost;
        }

        public int getMaximumFlow() {
            return maximumFlow;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        final int SOURCE = 0;
        final int SINK = 2*n+2*m+1;
        MCMF mcmf = new MCMF(SOURCE, SINK, 2*n+2*m+2);

        for (int i = 1; i <= n; i++) {
            mcmf.add(SOURCE, 2*i-1, 0, INF, true);
            mcmf.add(2*i-1, 2*i, 0, 1, true);
        }
        for (int i = 1; i <= m; i++) {
            mcmf.add(2*i-1+2*n, 2*i+2*n, 0, 1, true);
            mcmf.add(2*i+2*n, SINK, 0, INF, true);
        }

        for (int i = 1; i <= n; i++) {
            st = new StringTokenizer(br.readLine());
            int num = Integer.parseInt(st.nextToken());
            while (num-- > 0) {
                int node = Integer.parseInt(st.nextToken());
                int cost = Integer.parseInt(st.nextToken());
                mcmf.add(2*i, 2*n+2*node-1, cost, 1, true);
            }
        }
        mcmf.run();
        bw.write(Integer.toString(mcmf.getMaximumFlow()));
        bw.newLine();
        bw.write(Integer.toString(mcmf.getMinimumCost()));
        bw.flush();
    }
}
