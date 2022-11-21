import java.io.*;
import java.util.*;

public class BOJ1420 {
    private static int nRow, nCol;
    private static final int INF = 5;
    private static int[] rowDi = {-1, 0, 1, 0}, colDi = {0, -1, 0, 1};
    private static char[][] input;
    private static class MaxFlow {
        private final int size, source, sink;
        private Map<Integer, Integer> flow;
        MaxFlow(int size, int source, int sink) {
            this.size = size;
            this.source = source;
            this.sink = sink;
        }

        public int run() {
            int answer = 0;
            flow = new HashMap<>();
            Queue<Integer> que = new LinkedList<>();
            int[] par = new int[size];
            while (true) {
                que.clear();
                Arrays.fill(par, -1);
                que.add(source);
                par[source] = -2;
                while (!que.isEmpty()) {
                    int crnt = que.poll();
                    int nodeNum = crnt >> 1;
                    if (crnt % 2 == 0) { // IN
                        int next = crnt+1;
                        if (par[next] == -1 && capacity(crnt, next) - flowGet(crnt, next) > 0) {
                            par[next] = crnt;
                            que.add(next);
                            if (next == sink) break;
                        }
                    }
                    for (int k = 0; k < 4; k++) {
                        int nextRow = nodeNum/nCol + rowDi[k];
                        int nextCol = nodeNum%nCol + colDi[k];
                        if (!isInRange(nextRow, nextCol)) continue;
                        int next = ptr(nextRow, nextCol, crnt % 2 != 0);
                        if (par[next] != -1 || capacity(crnt, next) - flowGet(crnt, next) <= 0) continue;
                        par[next] = crnt;
                        que.add(next);
                        if (next == sink) break;
                    }
                }
                if (par[sink] == -1) break;
                int minFlow = INF;
                for (int node = sink; node != source; node = par[node]) {
                    int f = capacity(par[node], node) - flowGet(par[node], node);
                    if (f < minFlow) minFlow = f;
                }
                for (int node = sink; node != source; node = par[node]) {
                    flowPut(par[node], node, flowGet(par[node], node) + minFlow);
                    flowPut(node, par[node], flowGet(node, par[node]) - minFlow);
                }
                answer += minFlow;
            }
            return answer;
        }

        private int capacity(int from, int to) {
            if (input[from/2/nCol][from/2%nCol] == '#' || input[to/2/nCol][to/2%nCol] == '#') return 0;
            if (from % 2 == 0) {
                // IN
                return from + 1 == to ? 1 : 0;
            } else {
                // OUT
                return INF; 
            }
        }

        private int flowGet(int from, int to) {
            if (flow.containsKey(from*40_001+to)) {
                return flow.get(from*40_001+to);
            } else {
                flow.put(from*40_001+to, 0);
                return 0;
            }
        }

        private void flowPut(int from, int to, int f) {
            flow.put(from*40_001+to, f);
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        nRow = Integer.parseInt(st.nextToken());
        nCol = Integer.parseInt(st.nextToken());

        input = new char[nRow][nCol];
        for (int i = 0; i < nRow; i++) {
            input[i] = br.readLine().toCharArray();
        }

        int source = -1, sink = -1;
        for (int i = 0; i < nRow; i++) {
            for (int j = 0; j < nCol; j++) {
                if (input[i][j] == 'K') source = ptr(i, j, false);
                else if (input[i][j] == 'H') sink = ptr(i, j, true); 
            }
        }

        int size = nRow*nCol*2;
        MaxFlow mf = new MaxFlow(size, source, sink);
        int answer = mf.run();
        if (answer >= 5) answer = -1;
        bw.write(Integer.toString(answer));
        bw.flush();
    }
    private static int ptr(int row, int col, boolean isIn) {
        return (row*nCol+col) * 2 + (isIn ? 0 : 1);
    }
    private static boolean isInRange(int row, int col) {
        return 0 <= row && row < nRow && 0 <= col && col < nCol;
    }
}
