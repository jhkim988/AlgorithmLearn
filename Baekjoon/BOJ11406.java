import java.io.*;
import java.util.*;

public class BOJ11406 {
    private static final int INF = Integer.MAX_VALUE >> 4;
    private static class MaxFlow {
        private final int source, sink;
        private final int[][] capacity, flow;
        private final int[] level;
        private final int[] work;
        // private final Queue<Integer>[] work;
        private final List<List<Integer>> graph;

        MaxFlow(int size, int source, int sink) {
            this.source = source;
            this.sink = sink;
            capacity = new int[size][size];
            flow = new int[size][size];
            level = new int[size];
            work = new int[size];
            // work = new Queue[size];
            // for (int i = 0; i < size; i++) {
            //     work[i] = new ArrayDeque<>();
            // }

            graph = new ArrayList<>();
            for (int i = 0; i <= size; i++) {
                graph.add(new ArrayList<>());
            }
        }

        void add(int from, int to, int cap) {
            graph.get(from).add(to);
            graph.get(to).add(from);
            capacity[from][to] = cap;
        }

        int run() {
            int totalMaxFlow = 0;
            while (bfs()) {
                while (true) {
                    Arrays.fill(work, 0);
                    int flowValue = dfs(source, INF);
                    if (flowValue == 0) break;
                    totalMaxFlow += flowValue;
                }
            }
            return totalMaxFlow;
        }

        boolean bfs() {
            Queue<Integer> que = new ArrayDeque<>();
            Arrays.fill(level, -1);
            que.add(source);
            level[source] = 0;
            while (!que.isEmpty()) {
                int crnt = que.poll();
                for (int next : graph.get(crnt)) {
                    if (level[next] != -1 || capacity[crnt][next] - flow[crnt][next] <= 0) continue;
                    level[next] = level[crnt]+1;
                    // work[crnt].add(next);
                    que.add(next);
                    if (next == sink) return true;
                }
            }
            return false;
        }

        int dfs(int crnt, int flowValue) {
            if (crnt == sink) return flowValue;
            for (; work[crnt] < graph.get(crnt).size(); work[crnt]++) {
                int next = graph.get(crnt).get(work[crnt]);
            // while (!work[crnt].isEmpty()) {
                // int next = work[crnt].poll();
                if (level[next] != level[crnt]+1 || capacity[crnt][next] - flow[crnt][next] <= 0) continue;
                int ret = dfs(next, Integer.min(flowValue, capacity[crnt][next] - flow[crnt][next]));
                if (ret > 0) {
                    flow[crnt][next] += ret;
                    flow[next][crnt] -= ret;
                    return ret;
                }
            }
            return 0;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        final int n = Integer.parseInt(st.nextToken());
        final int m = Integer.parseInt(st.nextToken());

        final int size = n+m+2;
        final int source = n+m;
        final int sink = n+m+1;

        MaxFlow maxFlow = new MaxFlow(size, source, sink);
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            int cap = Integer.parseInt(st.nextToken());
            if (cap == 0) continue;
            maxFlow.add(i, sink, cap);
        }
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < m; i++) {
            int cap = Integer.parseInt(st.nextToken());
            if (cap == 0) continue;
            maxFlow.add(source, i+n, cap);
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                int cap = Integer.parseInt(st.nextToken());
                if (cap == 0) continue;
                maxFlow.add(i+n, j, cap);
            }
        }

        bw.write(Integer.toString(maxFlow.run()));
        bw.flush();
    }
}
