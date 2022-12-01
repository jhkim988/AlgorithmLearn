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
    private static class MaxFlow {
        private final int size, source, sink;
        private final int[][] capacity, flow, cost;
        private final int[] dist, work;
        private final ArrayList<Integer>[] graph;
        MaxFlow(int size, int source, int sink) {
            this.size = size;
            this.source = source;
            this.sink = sink;
            
            capacity = new int[size][size];
            flow = new int[size][size];
            cost = new int[size][size];

            dist = new int[size];
            work = new int[size];

            graph = new ArrayList[size];
            for (int i = 0; i < size; i++) {
                graph[i] = new ArrayList<>();
            }
        }

        void add(int from, int to, int cap, int cst) {
            graph[from].add(to);
            graph[to].add(from);
            capacity[from][to] = cap;
            cost[from][to] = cst;
            cost[to][from] = -cst;
        }

        int run() {
            int minCost = 0;
            while (spfa()) {
                Arrays.fill(work, 0);
                while (true) {
                    int flowVal = dfs(source, INF);
                    if (flowVal == 0) break;
                    minCost += dist[sink];                    
                }
            }
            return minCost;
        }

        boolean spfa() {
            Queue<Integer> que = new ArrayDeque<>();
            boolean[] inQue = new boolean[size];
            Arrays.fill(dist, INF);
            que.add(source);
            dist[source] = 0;
            while (!que.isEmpty()) {
                int crnt = que.poll();
                inQue[crnt] = false;
                for (int next : graph[crnt]) {
                    if (dist[next] <= dist[crnt] + cost[crnt][next]) continue;
                    if (capacity[crnt][next] <= flow[crnt][next]) continue;
                    dist[next] = dist[crnt] + cost[crnt][next];
                    if (!inQue[next]) {
                        inQue[next] = true;
                        que.add(next);
                    }
                }
            }
            return dist[sink] != INF;
        }

        int dfs(int crnt, int flowVal) {
            if (crnt == sink) return flowVal;
            for (; work[crnt] < graph[crnt].size(); work[crnt]++) {
                int next = graph[crnt].get(work[crnt]);
                if (dist[next] != dist[crnt]+cost[crnt][next] || capacity[crnt][next] <= flow[crnt][next]) continue;
                int ret = dfs(next, Integer.min(flowVal, capacity[crnt][next] - flow[crnt][next]));
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
        int numRow = Integer.parseInt(st.nextToken());
        int numCol = Integer.parseInt(st.nextToken());
        int[][] board = new int[numRow][numCol];
        for (int i = 0; i < numRow; i++) {
            char[] input = br.readLine().toCharArray();
            for (int j = 0; j < numCol; j++) {
                board[i][j] = input[j] - 'A';
            }
        }
        final int size = numRow*numCol+2;
        final int source = numRow*numCol;
        final int sink = numRow*numCol+1;
        MaxFlow maxFlow = new MaxFlow(size, source, sink);
        
        for (int i = 0; i < numRow; i++) {
            for (int j = 0; j < numCol; j++) {
                if (((i+j) & 1) == 0) {
                    maxFlow.add(source, i*numCol + j, 1, 0);
                    maxFlow.add(i*numCol+j, sink, 1, 0);
                    for (int k = 0; k < 4; k++) {
                        int adjRow = i + rowDi[k];
                        int adjCol = j + colDi[k];
                        if (adjRow < 0 || adjRow >= numRow || adjCol < 0 || adjCol >= numCol) continue;
                        maxFlow.add(i*numCol + j, adjRow * numCol + adjCol, 1, -score[board[i][j]][board[adjRow][adjCol]]);
                    }
                } else {
                    maxFlow.add(i*numCol + j, sink, 1, 0);
                }
            }
        }
        bw.write(Integer.toString(-maxFlow.run()));
        bw.flush();
    }
}