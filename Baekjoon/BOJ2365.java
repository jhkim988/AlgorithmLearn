import java.io.*;
import java.util.*;

public class BOJ2365 {
    private static int n;
    private static final int INF = Integer.MAX_VALUE >> 4;
    private static final int[] rowDi = {-1, 0}, colDi = {0, 1};
    private static class MaxFlow {
        private final int size, source, sink;
        private final int[][] capacity, flow;
        private final int[] level, work;
        private final ArrayList<Integer>[] graph;
        MaxFlow(int size, int source, int sink) {
            this.size = size;
            this.source = source;
            this.sink = sink;

            capacity = new int[size][size];
            flow = new int[size][size];
            level = new int[size];
            work = new int[size];

            graph = new ArrayList[size];
            for (int i = 0; i < size; i++) {
                graph[i] = new ArrayList<>();
            }
        }

        void add(int from, int to, int cap) {
            graph[from].add(to);
            graph[to].add(from);
            capacity[from][to] = cap;
        }

        int run() {
            int totalMaxFlow = 0;
            while (bfs()) {
                Arrays.fill(work, 0);
                int flowVal = dfs(source, INF);
                if (flowVal == 0) break;
                totalMaxFlow += flowVal;
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
                for (int next : graph[crnt]) {
                    if (level[next] != -1 || capacity[crnt][next] - flow[crnt][next] <= 0) continue;
                    level[next] = level[crnt] + 1;
                    que.add(next);
                    if (next == sink) return true;
                }
            }
            return false;
        }

        int dfs(int crnt, int flowValue) {
            if (crnt == sink) return flowValue;
            for (; work[crnt] < graph[crnt].size(); work[crnt]++) {
                int next = graph[crnt].get(work[crnt]);
                if (level[next] != level[crnt]+1 || capacity[crnt][next] - flow[crnt][next] <= 0) continue;
                int ret = dfs(next, Integer.min(flowValue, capacity[crnt][next] - flow[crnt][next]));
                if (ret == 0) continue;
                flow[crnt][next] += ret;
                flow[next][crnt] -= ret;
                return ret;
            }
            return 0;
        }

        int[][] getFlow() {
            return flow;
        }

        void initFlow() {
            for (int i = 0; i < size; i++) {
                Arrays.fill(flow[i], 0);
            }
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        n = Integer.parseInt(br.readLine());
        int[] rowVal = new int[n];
        int[] colVal = new int[n];

        int sum = 0;
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            rowVal[i] = Integer.parseInt(st.nextToken());
            sum += rowVal[i];
        }
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            colVal[i] = Integer.parseInt(st.nextToken());
        }

        final int size = 2*n*n+2*n+2;
        final int source = 2*n*n+2*n;
        final int sink = 2*n*n+2*n+1;

        MaxFlow maxFlow = new MaxFlow(size, source, sink);
        for (int i = 0; i < n; i++) {
            maxFlow.add(source, 2*n*n+i, rowVal[i]);
        }
        for (int i = 0; i < n; i++) {
            maxFlow.add(2*n*n+n+i, sink, colVal[i]);
        }
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                maxFlow.add(2*n*n+i, i*n+j, INF);
                // maxFlow.add(i*n+j, i*n+j+n*n, 100);
                maxFlow.add(i*n+j+n*n, 2*n*n+n+j, INF);
            }
        }
        int lo = 0, hi = 10_000;
        while (lo+1 < hi) {
            int mid = (lo+hi) >> 1;
            if (possible(maxFlow, mid, sum)) {
                hi = mid;                
            } else {
                lo = mid;
            }
        }
        int max = hi;
        possible(maxFlow, max, sum);
        int[][] flow = maxFlow.getFlow();
        bw.write(Integer.toString(max));
        bw.newLine();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                bw.write(Integer.toString(flow[i*n+j][i*n+j+n*n]));
                bw.write(' ');
            }
            bw.newLine();
        }
        bw.flush();
    }
    private static boolean possible(MaxFlow maxFlow, int maxVal, int fullFlow) {
        maxFlow.initFlow();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                maxFlow.add(i*n+j, i*n+j+n*n, maxVal);
            }
        }
        return maxFlow.run() == fullFlow;
    }
}
