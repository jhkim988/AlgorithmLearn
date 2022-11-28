import java.io.*;
import java.util.*;

public class BOJ1031 {
    private static final int INF = Integer.MAX_VALUE >> 4;
    private static class MaxFlow {
        private final int size, source, sink;
        private final int[] par;
        private final int[][] capacity, flow;
        private final List<Queue<Integer>> graph;

        MaxFlow(int size, int source, int sink) {
            this.size = size;
            this.source = source;
            this.sink = sink;
            this.graph = new ArrayList<>();
            this.par = new int[size];
            this.capacity = new int[size][size];
            this.flow = new int[size][size];
            for (int i = 0; i <= size; i++) {
                graph.add(new LinkedList<>());
            }
        }

        void add(int from, int to, int cap) {
            graph.get(from).add(to);
            graph.get(to).add(from);
            capacity[from][to] = cap;
        }

        int getMaxFlow(int start, int end) {
            int answer = 0;
            while (true) {
                if (!bfs(par, start, end)) break;
                int min = INF;
                for (int node = end; node != start; node = par[node]) {
                    int f = capacity[par[node]][node] - flow[par[node]][node];
                    if (f < min) min = f;
                }
                for (int node = end; node != start; node = par[node]) {
                    flow[par[node]][node] += min;
                    flow[node][par[node]] -= min;
                }
                answer += min;
            }
            return answer;
        }

        boolean bfs(int[] par, int start, int end) {
            Queue<Integer> que = new LinkedList<>();
            Arrays.fill(par, -1);
            que.add(start);
            par[start] = -2;
            while (!que.isEmpty()) {
                int crnt = que.poll();
                for (int next : graph.get(crnt)) {
                    if (par[next] != -1 || capacity[crnt][next] - flow[crnt][next] <= 0) continue;
                    par[next] = crnt;
                    que.add(next);
                    if (next == end) return true;
                }
            }
            return false;
        }

        boolean ordering(int needMatch, int row, int col) {
            int maxFlow = getMaxFlow(source, sink);
            if (needMatch != maxFlow) return false;
            for (int i = 1; i <= row; i++) {
                for (int j = 1; j <= col; j++) {
                    if (flow[i][j+row] == 0) continue;
                    changeFlow(i, j+row);
                }
            }
            return true;
        }

        void changeFlow(int start, int end) {
            Queue<Integer> que = new LinkedList<>();
            Arrays.fill(par, -1);
            que.add(start);
            while (!que.isEmpty() && par[end] == -1) {
                int crnt = que.poll();
                for (int next : graph.get(crnt)) {
                    if (crnt < start || crnt == start && next < end) continue;
                    if (par[next] != -1 || capacity[crnt][next] - flow[crnt][next] <= 0) continue;
                    que.add(next);
                    par[next] = crnt;
                }
            }
            if (par[end] == -1) return;
            flow[start][end] = flow[end][start] = 0;
            for (int node = end; node != start; node = par[node]) {
                flow[par[node]][node]++;
                flow[node][par[node]]--;
            }
        }

        int[][] getFlow() {
            return flow;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        final int n = Integer.parseInt(st.nextToken());
        final int m = Integer.parseInt(st.nextToken());
        final int size = n+m+2;
        final int source = 0, sink = n+m+1;
        int sumA = 0;
        int sumB = 0;
        MaxFlow maxFlow = new MaxFlow(size, source, sink);
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= n; i++) {
            int cap = Integer.parseInt(st.nextToken());
            maxFlow.add(source, i, cap);
            sumA += cap;
        }
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= m; i++) {
            int cap = Integer.parseInt(st.nextToken());
            maxFlow.add(n+i, sink, cap);
            sumB += cap;
        }
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                maxFlow.add(i, n+j, 1);
            }
        }
        int[][] answer = null;
        if (sumA == sumB && maxFlow.ordering(sumA, n, m)) {
            answer = maxFlow.getFlow();
            printAnswer(answer, n, m, bw);
        } else {
            bw.write("-1");
        }
        bw.flush();
    }
    private static void printAnswer(int[][] flow, int n, int m, BufferedWriter bw) throws IOException {
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                bw.write(Integer.toString(flow[i][n+j]));
            }
            bw.newLine();
        }
    }
}
