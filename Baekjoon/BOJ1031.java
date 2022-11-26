import java.io.*;
import java.util.*;

public class BOJ1031 {
    private static final int INF = Integer.MAX_VALUE >> 4;
    private static class MaxFlow {
        private final int size, source, sink;
        private final int[][] capacity, flow;
        private final List<Queue<Integer>> graph;

        MaxFlow(int size, int source, int sink) {
            this.size = size;
            this.source = source;
            this.sink = sink;
            capacity = new int[size][size];
            flow = new int[size][size];
            graph = new ArrayList<>();
            for (int i = 0; i <= size; i++) {
                graph.add(new LinkedList<>());
            }
        }

        void add(int from, int to, int cap) {
            graph.get(from).add(to);
            graph.get(to).add(from);
            capacity[from][to] = cap;
        }

        int getMaxFlow() {
            int answer = 0;
            int[] par = new int[size];
            for (int i = 0; i < size; i++) {
                Arrays.fill(flow[i], 0);
            }
            while (true) {
                Arrays.fill(par, -1);
                par[source] = -2;
                if (!bfs(par)) break;
                int min = INF;
                for (int node = sink; node != source; node = par[node]) {
                    int f = capacity[par[node]][node] - flow[par[node]][node];
                    if (f < min) min = f;
                }
                for (int node = sink; node != source; node = par[node]) {
                    flow[par[node]][node] += min;
                    flow[node][par[node]] -= min;
                }
                answer += min;
            }
            return answer;
        }

        boolean bfs(int[] par) {
            Queue<Integer> que = new LinkedList<>();
            que.add(source);
            while (!que.isEmpty()) {
                int crnt = que.poll();
                for (int next : graph.get(crnt)) {
                    if (par[next] != -1 || capacity[crnt][next] - flow[crnt][next] <= 0) continue;
                    par[next] = crnt;
                    que.add(next);
                    if (next == sink) return true;
                }
            }
            return false;
        }

        int[][] getAnswer(int needMatch, int row, int col) {
            if (needMatch != getMaxFlow()) {
                return null;
            }
            int[][] ret = new int[size][size];
            for (int i = 0; i < size; i++) {
                System.arraycopy(flow[i], 0, ret[i], 0, size);
            } 
            for (int i = 1; i <= row; i++) {
                for (int j = 1; j <= col; j++) {
                    capacity[i][row+j] = 0;
                    if (needMatch != getMaxFlow()) {
                        capacity[i][row+j] = 1;
                    } else {
                        for (int x = 0; x < size; x++) {
                            System.arraycopy(flow[x], 0, ret[x], 0, size);
                        } 
                    }
                }
            }
            return ret;
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
        if (sumA == sumB && (answer = maxFlow.getAnswer(sumA, n, m)) != null) {
            bw.write(answerToString(answer, n, m));
        } else {
            bw.write("-1");
        }
        bw.flush();
    }
    private static String answerToString(int[][] flow, int n, int m) {
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                sb.append(flow[i][n+j]);
            }
            sb.append('\n');
        }
        return sb.toString();
    }
}
