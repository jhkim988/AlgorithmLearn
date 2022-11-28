import java.io.*;
import java.util.*;

public class BOJ13161 {
    // TODO: USE DINIC ALGORITHM
    private static final int INF = Integer.MAX_VALUE >> 4;
    private static class MCMF {
        private final int size, source, sink;
        private final int[][] capacity, cost, flow;
        private final int[] level, work;
        private final List<List<Integer>> graph;
        private int minCost = 0;

        MCMF(int size, int source, int sink, int[][] cost) {
            this.size = size;
            this.source = source;
            this.sink = sink;
            this.capacity = new int[size][size];
            this.cost = cost;
            this.flow = new int[size][size];
            this.level = new int[size];
            this.work = new int[size];
            this.graph = new ArrayList<>();
            for (int i = 0; i <= size; i++) {
                graph.add(new ArrayList<>());
            }
        }

        void add(int from, int to, int cap) {
            graph.get(from).add(to);
            graph.get(to).add(from);
            capacity[from][to] = cap;
        }

        void run() {
            minCost = 0;
            Integer val = spfa();
            while (val != null) {
                while (true) {
                    Arrays.fill(work, 0);
                    int flowVal = dfs(source, INF);
                    if (flowVal == 0) break;
                    minCost += val;
                }
                val = spfa();
            }
        }
        
        Integer spfa() {
            Queue<Integer> que = new LinkedList<>();
            boolean[] inQue = new boolean[size];
            int[] dist = new int[size];
            Arrays.fill(level, -1);
            Arrays.fill(dist, INF);
            que.add(source);
            dist[source] = 0;
            inQue[source] = true;
            while (!que.isEmpty()) {
                int crnt = que.poll();
                for (int next : graph.get(crnt)) {
                    if (capacity[crnt][next] - flow[crnt][next] <= 0) continue;
                    if (dist[next] <= dist[crnt] + cost[crnt][next]) continue;
                    dist[next] = dist[crnt] + cost[crnt][next];
                    level[next] = level[crnt] + 1;
                    if (!inQue[next]) {
                        inQue[next] = true;
                        que.add(next);
                    }
                }
            }
            if (level[sink] == -1) return null;
            return dist[sink];
        }

        int dfs(int crnt, int flowVal) {
            if (crnt == sink) return flowVal;
            for (; work[crnt] < graph.get(crnt).size(); work[crnt]++) {
                int next = graph.get(crnt).get(work[crnt]);
                if (level[next] != level[crnt]+1 || capacity[crnt][next] - flow[crnt][next] <= 0) continue;
                int ret = dfs(next, Integer.min(flowVal, capacity[crnt][next] - flow[crnt][next]));
                if (ret > 0) {
                    flow[crnt][next] += ret;
                    flow[next][crnt] -= ret;
                    return ret;
                }
            }
            return 0;
        }

        int getMinCost() {
            return minCost;
        }

        int[] getLevel() {
            Arrays.fill(level, -1);
            Queue<Integer> que = new LinkedList<>();
            que.add(source);
            level[source] = 0;
            while (!que.isEmpty()) {
                int crnt = que.poll();
                for (int next : graph.get(crnt)) {
                    if (level[next] != -1) continue;
                    level[next] = level[crnt] + 1;
                    que.add(next);
                }
            }
            return level;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int n = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] team = new int[n];
        for (int i = 0; i < n; i++) {
            team[i] = Integer.parseInt(st.nextToken());
        }
        
        int[][] cost = new int[n*2+2][n*2+2];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                cost[i][j+n] = Integer.parseInt(st.nextToken());
            }
        }

        final int size = n+2;
        final int source = n;
        final int sink = n+1;
        MCMF mcmf = new MCMF(size, source, sink, cost);
        for (int i = 0; i < n; i++) {
            if (team[i] == 1) mcmf.add(source, i, INF);
            else if (team[i] == 2) mcmf.add(i, sink, INF);
        }
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i == j) continue;
                mcmf.add(i, j, 1);
            }
        }
        mcmf.run();

        int minCost = mcmf.getMinCost();
        int[] level = mcmf.getLevel();
        bw.write(Integer.toString(minCost));
        bw.newLine();
        bw.write(printLevel(level, 0, n));
        bw.newLine();
        bw.write(printLevel(level, 1, n));
        bw.flush();
    }
    private static String printLevel(int[] level, int x, int n) {
        boolean flag = false;
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            if (level[i] % 2 == x) continue;
            if (flag) sb.append(' ');
            sb.append(i+1);
            flag = true;            
        }
        return sb.toString();
    }
}
