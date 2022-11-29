import java.util.*;

public class Dinic {
    private static final int INF = Integer.MAX_VALUE >> 4;
    private final int size, source, sink;
    private final int[][] capacity, flow;
    private final int[] work, level;
    private final List<List<Integer>> graph;

    public Dinic(int size, int source, int sink) {
        this.size = size;
        this.source = source;
        this.sink = sink;
        capacity = new int[size][size];
        flow = new int[size][size];
        work = new int[size];
        level = new int[size];
        graph = new ArrayList<>();
        for (int i = 0; i <= size; i++) {
            graph.add(new ArrayList<>());
        }
    }

    public void add(int from, int to, int cap) {
        graph.get(from).add(to);
        graph.get(to).add(from);
        capacity[from][to] += cap;
    }

    public boolean bfs() {
        Queue<Integer> que = new LinkedList<>();
        Arrays.fill(level, -1);
        que.add(source);
        level[source] = 0;
        while (!que.isEmpty()) {
            int crnt = que.poll();
            for (int next : graph.get(crnt)) {
                if (level[next] != -1 || capacity[crnt][next] - flow[crnt][next] <= 0) continue;
                level[next] = level[crnt] + 1;
                que.add(next);
            }
        }
        return level[sink] != -1; 
    }

    public int dfs(int crnt, int flowVal) {
        if (crnt == sink) return flowVal;
        for (; work[crnt] < graph.get(crnt).size(); work[crnt]++) {
            int next = graph.get(crnt).get(work[crnt]);
            if (level[next] != level[crnt] + 1 || capacity[crnt][next] - flow[crnt][next] <= 0) continue;
            int ret = dfs(next, Integer.min(flowVal, capacity[crnt][next] - flow[crnt][next]));
            if (ret > 0) {
                flow[crnt][next] += ret;
                flow[next][crnt] -= ret;
                return ret;
            }
        }
        work[crnt] = graph.get(crnt).size();
        return 0;
    }

    public int maxFlow() {
        int totalFlow = 0;
        while (bfs()) {
            Arrays.fill(work, 0);
            while (true) {
                int flow = dfs(source, INF);
                if (flow == 0) break;
                totalFlow += flow;
            }
        }
        return totalFlow;
    }
}