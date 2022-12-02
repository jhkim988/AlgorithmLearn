import java.io.*;
import java.util.*;

public class BOJ11111 {
    private static final int INF = Integer.MAX_VALUE >> 4;
    private static final int[][] score = {
        {10, 8, 7, 5, 0, 1}
        , {8, 6, 4, 3, 0, 1}
        , {7, 4, 3, 2, 0, 1}
        , {5, 3, 2, 2, 0, 1}
        , {0, 0, 0, 0, 0, 0}
        , {1, 1, 1, 1, 0, 0}
    };
    private static final int[] rowDi = {-1, 0, 1, 0}, colDi = {0, -1, 0, 1};
    private static class Edge {
        int to, cap, flow, cost;
        Edge reverse;
        Edge (int to, int cap, int cost) {
            this.to = to;
            this.cap = cap;
            this.flow = 0;
            this.cost = cost;
        }
        
        int residual() {
            return cap-flow;
        }

        void setReverse(Edge reverse) {
            this.reverse = reverse;
        }
    }
    private static class MaxFlow {
        private final int size, source, sink;
        private final int[] dist, work, level;
        private final ArrayList<Edge>[] graph;
        private final Queue<Integer> que = new ArrayDeque<>();
        private final boolean[] inQue;
        MaxFlow(int size, int source, int sink) {
            this.size = size;
            this.source = source;
            this.sink = sink;
            
            dist = new int[size];
            work = new int[size];
            level = new int[size];
            inQue = new boolean[size];
            graph = new ArrayList[size];
            for (int i = 0; i < size; i++) {
                graph[i] = new ArrayList<>();
            }
        }

        void add(int from, int to, int cap, int cst) {
            Edge edge = new Edge(to, cap, cst);
            Edge reverse = new Edge(from, 0, -cst);
            graph[from].add(edge);
            graph[to].add(reverse);
            edge.setReverse(reverse);
            reverse.setReverse(edge);
        }

        int run() {
            int minCost = 0;
            while (spfa()) {
                int sumFlow = 0;
                Arrays.fill(work, 0);
                while (true) {
                    int flowVal = dfs(source, INF);
                    if (flowVal == 0) break;
                    sumFlow += flowVal;
                }
                minCost += dist[sink] * sumFlow;
            }
            return minCost;
        }

        boolean spfa() {
            que.clear();
            Arrays.fill(inQue, false);
            Arrays.fill(dist, INF);
            Arrays.fill(level, 0);
            que.add(source);
            dist[source] = 0;
            inQue[source] = true;
            level[source] = 1;
            while (!que.isEmpty()) {
                int crnt = que.poll();
                inQue[crnt] = false;
                for (Edge next : graph[crnt]) {
                    if (dist[next.to] <= dist[crnt] + next.cost || next.residual() <= 0) continue;
                    dist[next.to] = dist[crnt] + next.cost;
                    level[next.to] = level[crnt] + 1;
                    if (!inQue[next.to]) {
                        inQue[next.to] = true;
                        que.add(next.to);
                    }
                }
            }
            return dist[sink] != INF;
        }

        int dfs(int crnt, int flowVal) {
            if (crnt == sink) return flowVal;
            for (; work[crnt] < graph[crnt].size(); work[crnt]++) {
                Edge next = graph[crnt].get(work[crnt]);
                if (level[next.to] != level[crnt]+1 || dist[next.to] != dist[crnt]+next.cost|| next.residual() <= 0) continue;
                int ret = dfs(next.to, Integer.min(flowVal, next.residual()));
                if (ret > 0) {
                    next.flow += ret;
                    next.reverse.flow -= ret;
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
        int numRow = Integer.parseInt(st.nextToken());
        int numCol = Integer.parseInt(st.nextToken());
        char[][] board = new char[numRow][numCol];
        for (int i = 0; i < numRow; i++) {
            board[i] = br.readLine().toCharArray();
        }
        final int size = numRow*numCol+2;
        final int source = numRow*numCol;
        final int sink = numRow*numCol+1;
        MaxFlow maxFlow = new MaxFlow(size, source, sink);
        
        for (int i = 0; i < numRow; i++) {
            for (int j = 0; j < numCol; j++) {
                if (((i+j) & 1) == 0) {
                    maxFlow.add(source, i*numCol+j, 1, 0);
                    for (int k = 0; k < 4; k++) {
                        int adjRow = i + rowDi[k];
                        int adjCol = j + colDi[k];
                        if (adjRow < 0 || adjRow >= numRow || adjCol < 0 || adjCol >= numCol) continue;
                        maxFlow.add(i*numCol+j, adjRow*numCol+adjCol, 1, -score[board[i][j]-'A'][board[adjRow][adjCol]-'A']);
                    }
                }
                maxFlow.add(i*numCol+j, sink, 1, 0);
            }
        }
        bw.write(Integer.toString(-maxFlow.run()));
        bw.flush();
    }
}