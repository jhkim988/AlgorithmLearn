import java.util.*;

public class MCMF {
    private static final int INF = Integer.MAX_VALUE >> 2;
    private final int SIZE;
    private final int SOURCE;
    private final int SINK;
    private final int[][] capacity;
    private final int[][] costs;
    private final List<Queue<Integer>> graph;

    private int minimumCost = 0;
    private int maxFlow = 0;

    public MCMF(int size, int source, int sink) {
        this.SIZE = size;
        this.SOURCE = source;
        this.SINK = sink;

        capacity = new int[SIZE][SIZE];
        costs = new int[SIZE][SIZE];
        graph = new ArrayList<>();
        for (int i = 0; i <= SIZE; i++) {
            graph.add(new LinkedList<>());
        }
    }

    public void add(int from, int to, int cap, int cost, boolean isDirected) {
        graph.get(from).add(to);
        graph.get(to).add(from);
        costs[from][to] = cost;
        costs[to][from] = -cost;
        capacity[from][to] = cap;
        if (!isDirected) capacity[to][from] = cap;
    }

    public void run() {
        minimumCost = 0;
        maxFlow = 0;
        Queue<Integer> que = new LinkedList<>();
        boolean[] inQue = new boolean[SIZE];
        int[] dist = new int[SIZE];
        int[][] flow = new int[SIZE][SIZE];
        int[] par = new int[SIZE];
        int[] numVisit = new int[SIZE];
        while (true) {
            que.clear();
            Arrays.fill(inQue, false);
            Arrays.fill(dist, INF);
            Arrays.fill(costs, 0);
            Arrays.fill(par, -1);
            Arrays.fill(numVisit, 0);

            que.add(SOURCE);
            inQue[SOURCE] = true;
            dist[SOURCE] = 0;
            par[SOURCE] = -2;
            numVisit[SOURCE] = 1;

            // SPFA
            while (!que.isEmpty()) {
                int crnt = que.poll();
                inQue[crnt] = false;
                for (int next : graph.get(crnt)) {
                    if (dist[next] <= dist[crnt] + costs[crnt][next]) continue;
                    if (capacity[crnt][next] - flow[crnt][next] <= 0) continue;
                    dist[next] = dist[crnt] + costs[crnt][next];
                    par[next] = crnt;
                    if (!inQue[next]) {
                        inQue[next] = true;
                        numVisit[next]++;
                        que.add(next);
                        if (numVisit[next] >= SIZE) return;
                    }
                    if (next == SINK) break;
                }
            }

            if (par[SINK] == -1) break;
            int flowOfPath = INF;
            for (int node = SINK; node != SOURCE; node = par[node]) {
                int f = capacity[par[node]][node] - flow[par[node]][node];
                if (f < flowOfPath) flowOfPath = f;
            }
            for (int node = SINK; node != SOURCE; node = par[node]) {
                flow[par[node]][node] += flowOfPath;
                flow[node][par[node]] -= flowOfPath;
            }
            minimumCost += dist[SINK]*flowOfPath;
            maxFlow += flowOfPath;
        }
    }

    public int getMinimumCost() {
        return minimumCost;
    }

    public int getMaxFlow() {
        return maxFlow;
    }
}
