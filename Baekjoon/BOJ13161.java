import java.io.*;
import java.util.*;

public class BOJ13161 {
    private static final int INF = Integer.MAX_VALUE >> 4;
    private static class MaxFlow {
        private final int size, source, sink;
        private final int[][] capacity, flow;
        private final int[] level, work;
        private final ArrayList<Integer>[] graph;
        private int maxFlow = 0;

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

        void run() {
            maxFlow = 0;
            while (bfs()) {
                while (true) {
                    Arrays.fill(work, 0);
                    int flowVal = dfs(source, INF);
                    if (flowVal == 0) break;
                    maxFlow += flowVal;
                }
            }
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

        int dfs(int crnt, int flowVal) {
            if (crnt == sink) return flowVal;
            for (; work[crnt] < graph[crnt].size(); work[crnt]++) {
                int next = graph[crnt].get(work[crnt]);
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

        int getMaxFlow() {
            return maxFlow;
        }

        int[] getLevel() {
            Arrays.fill(level, 0);
            Queue<Integer> que = new LinkedList<>();
            que.add(source);
            level[source] = 1;
            while (!que.isEmpty()) {
                int crnt = que.poll();
                for (int next : graph[crnt]) {
                    if (level[next] != 0 || capacity[crnt][next] - flow[crnt][next] <= 0) continue;
                    level[next] = 1;
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
        final int size = n+2;
        final int source = n;
        final int sink = n+1;
        MaxFlow maxFlow = new MaxFlow(size, source, sink);
        
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                int val = Integer.parseInt(st.nextToken());
                if (i == j || val == 0) continue;
                maxFlow.add(i, j, Integer.parseInt(st.nextToken()));
            }
        }

        for (int i = 0; i < n; i++) {
            if (team[i] == 1) maxFlow.add(source, i, INF);
            else if (team[i] == 2) maxFlow.add(i, sink, INF);
        }

        maxFlow.run();

        int minCost = maxFlow.getMaxFlow();
        int[] level = maxFlow.getLevel();
        bw.write(Integer.toString(minCost));
        bw.newLine();
        printLevel(level, 0, n, bw);
        bw.newLine();
        printLevel(level, 1, n, bw);
        bw.newLine();
        bw.flush();
    }

    private static void printLevel(int[] level, int x, int n, BufferedWriter bw) throws IOException {
        for (int i = 0; i < n; i++) {
            if (level[i] == x) continue;
            bw.write(Integer.toString(i+1));
            bw.write(' ');
        }
    }
}
