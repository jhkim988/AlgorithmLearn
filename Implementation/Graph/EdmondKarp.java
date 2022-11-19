import java.util.*;

public class EdmondKarp {
    private static final int INF = Integer.MAX_VALUE >> 1;
    private final int SOURCE, SINK;
    private int size;
    private int[][] cap;
    private List<Queue<Integer>> graph;

    EdmondKarp(int size, int source, int sink) {
        this.size = size;
        this.SOURCE = source;
        this.SINK = sink;

        this.graph = new ArrayList<>();
        for (int i = 0; i <= size; i++) {
            graph.add(new LinkedList<>());
        }
    }

    public void add(int node1, int node2, int capacity, boolean directed) {
        graph.get(node1).add(node2);
        graph.get(node2).add(node1);
        cap[node1][node2] += capacity;
        if (!directed) cap[node2][node1] += capacity;
    }

    public int maxflow() {
        int answer = 0;
        Queue<Integer> que = new LinkedList<>();
        int[] par = new int[size+1];
        int[][] flow = new int[size+1][size+1];
        while (true) {
            que.clear();
            Arrays.fill(par, -1);

            que.add(SOURCE);
            par[SOURCE] = 0;
            while (!que.isEmpty()) {
                int crnt = que.poll();
                for (int next : graph.get(crnt)) {
                    if (par[next] != -1 || cap[crnt][next] - flow[crnt][next] <= 0) continue;
                    par[next] = crnt;
                    que.add(next);
                    if (next == SINK) break;
                }
            }
            if (par[SINK] == -1) break;

            int cost = INF;            
            for (int i = SINK; i != SOURCE; i = par[i]) {
                cost = Integer.min(cost, cap[par[i]][i] - flow[par[i]][i]);
            }
            answer += cost;

            for (int i = SINK; i != SOURCE; i = par[i]) {
                flow[par[i]][i] += cost;
                flow[i][par[i]] -= cost;
            }
        }
        return answer;
    }
}
