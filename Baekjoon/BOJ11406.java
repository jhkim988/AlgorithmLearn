import java.io.*;
import java.util.*;

public class BOJ11406 {
    private static final int INF = Integer.MAX_VALUE >> 4;
    private static class MaxFlow {
        private final int source, sink;
        private final int[][] capacity, flow;
        private final int[] level, work;
        private final List<List<Integer>> graph;

        MaxFlow(int size, int source, int sink) {
            this.source = source;
            this.sink = sink;
            capacity = new int[size][size];
            flow = new int[size][size];
            level = new int[size];
            work = new int[size];
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
            Queue<Integer> que = new LinkedList<>();
            Arrays.fill(level, -1);
            que.add(source);
            level[source] = 0;
            while (!que.isEmpty()) {
                int crnt = que.poll();
                for (int next : graph.get(crnt)) {
                    if (level[next] != -1 || capacity[crnt][next] - flow[crnt][next] <= 0) continue;
                    level[next] = level[crnt]+1;
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
            maxFlow.add(i, sink, Integer.parseInt(st.nextToken()));
        }
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < m; i++) {
            maxFlow.add(source, i+n, Integer.parseInt(st.nextToken()));
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                maxFlow.add(i+n, j, Integer.parseInt(st.nextToken()));
            }
        }

        bw.write(Integer.toString(maxFlow.run()));
        bw.flush();
    }
}
