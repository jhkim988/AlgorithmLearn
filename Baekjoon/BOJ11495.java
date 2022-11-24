import java.io.*;
import java.util.*;

public class BOJ11495 {
    private static final int INF = Integer.MAX_VALUE >> 4;
    private static int[] rowDi = {-1, 0, 1, 0}, colDi = {0, -1, 0, 1};
    private static class Edge {
        int to, cap, flow;
        Edge(int to, int cap, int flow) {
            this.to = to;
            this.cap = cap;
            this.flow = flow;
        }

        public int residual() {
            return cap - flow;
        }
    }
    private static class MaxFlow {
        private final int size, source, sink;
        private final List<Map<Integer, Edge>> graph;
        MaxFlow(int size, int source, int sink) {
            this.size = size;
            this.source = source;
            this.sink = sink;

            graph = new ArrayList<>();
            for (int i = 0; i <= size; i++) {
                graph.add(new HashMap<>());
            }
        }

        public void add(int from, int to, int cap) {
            graph.get(from).put(to, new Edge(to, cap, 0));
            graph.get(to).put(from, new Edge(from, 0, 0));
        }

        public int run() {
            int answer = 0;
            Queue<Integer> que = new LinkedList<>();
            int[] par = new int[size];
            while (true) {
                que.clear();
                Arrays.fill(par, -1);
                que.add(source);
                par[source] = -2;
                bfs: while (!que.isEmpty()) {
                    int crnt = que.poll();
                    for (Edge next : graph.get(crnt).values()) {
                        if (par[next.to] != -1 || next.residual() <= 0) continue;
                        par[next.to] = crnt;
                        que.add(next.to);
                        if (next.to == sink) break bfs;
                    }
                }
                if (par[sink] == -1) break;
                int min = INF;
                for (int node = sink; node != source; node = par[node]) {
                    int f = graph.get(par[node]).get(node).residual();
                    if (f < min) min = f;
                }
                for (int node = sink; node != source; node = par[node]) {
                    graph.get(par[node]).get(node).flow += min;
                    graph.get(node).get(par[node]).flow -= min;
                }
                answer += min;
            }
            return answer;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int numTest = Integer.parseInt(br.readLine());
        while (numTest-- > 0) {
            bw.write(Integer.toString(answer(br)));
            bw.newLine();
        }
        bw.flush();
    }

    private static int answer(BufferedReader br) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        final int nRow = Integer.parseInt(st.nextToken());
        final int nCol = Integer.parseInt(st.nextToken());
        final int size = nRow*nCol+2;
        final int source = nRow*nCol;
        final int sink = nRow*nCol+1;
        MaxFlow maxFlow = new MaxFlow(size, source, sink);
        
        int totalSum = 0;
        boolean rowFlag = true;
        boolean colFlag = true;
        for (int i = 0; i < nRow; i++) {
            st = new StringTokenizer(br.readLine());
            colFlag = rowFlag;
            for (int j = 0; j < nCol; j++) {
                int val = Integer.parseInt(st.nextToken());
                if (colFlag) {
                    maxFlow.add(source, i*nCol+j, val);
                    for (int k = 0; k < 4; k++) {
                        int adjRow = i + rowDi[k];
                        int adjCol = j + colDi[k];
                        if (adjRow < 0 || adjRow >= nRow || adjCol < 0 || adjCol >= nCol) continue;
                        maxFlow.add(i*nCol+j, adjRow*nCol+adjCol, INF); // ADJUST SENDER -> ADJUST RECEIVER
                    }
                } else {
                    maxFlow.add(i*nCol+j, sink, val);
                }
                totalSum += val;
                colFlag = !colFlag;
            }
            rowFlag = !rowFlag;
        }
        return totalSum - maxFlow.run();
    }
}
