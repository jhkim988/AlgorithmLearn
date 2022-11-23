import java.io.*;
import java.util.*;

public class BOJ3640 {
    private static int INF = Integer.MAX_VALUE >> 4;
    private static class Pair {
        int to, cap, cost, flow;
        Pair(int to, int cap, int cost, int flow) {
            this.to = to;
            this.cap = cap;
            this.cost = cost;
            this.flow = flow;
        }

        public int residual() {
            return cap - flow;
        }
    }
    private static class MCMF {
        private final int size, source, sink;
        private List<Map<Integer, Pair>> graph;
        private int minCost = 0;
        // private int maxFlow = 0;

        MCMF(int size, int source, int sink) {
            this.size = size;
            this.source = source;
            this.sink = sink;

            graph = new ArrayList<>();
            for (int i = 0; i <= size; i++) {
                graph.add(new HashMap<>());
            }
        }

        public void add(int from, int to, int cap, int cost, boolean isDirected) {
            graph.get(from).put(to, new Pair(to, cap, cost, 0));
            graph.get(to).put(from, new Pair(from, 0, -cost, 0));
        }

        public void run() {
            minCost = 0;
            // maxFlow = 0;
            Queue<Integer> que = new LinkedList<>();
            int[] par = new int[size];
            int[] dist = new int[size];
            boolean[] inQue = new boolean[size];
            int[] numVisit = new int[size];
            while (true) {
                que.clear();
                Arrays.fill(par, -1);
                Arrays.fill(dist, INF);
                Arrays.fill(inQue, false);
                Arrays.fill(numVisit, 0);
                que.add(source);
                par[source] = -2;
                dist[source] = 0;
                inQue[source] = true;
                numVisit[source] = 1;
                while (!que.isEmpty()) {
                    int crnt = que.poll();
                    inQue[crnt] = false;
                    for (Pair nextPair : graph.get(crnt).values()) {
                        int next = nextPair.to;
                        if (nextPair.residual() <= 0) continue;
                        if (dist[next] <= dist[crnt] + nextPair.cost) continue;
                        par[next] = crnt;
                        dist[next] = dist[crnt] + nextPair.cost; 
                        if (!inQue[next]) {
                            inQue[next] = true;
                            numVisit[next]++;
                            que.add(next);
                            if (numVisit[next] >= size) return;
                        }
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
                minCost += dist[sink];
                // maxFlow += min;
            }
        }

        public int getMinCost() {
            return minCost;
        }

        // public int getMaxFlow() {
        //     return maxFlow;
        // }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        
        int answer = -1;
        while ((answer = testCase(br)) != -1) {
            bw.write(Integer.toString(answer));
            bw.newLine();
        }
        bw.flush();
    }

    public static int testCase(BufferedReader br) throws IOException {
        String input = br.readLine();
        if (input == null) return -1;

        StringTokenizer st = new StringTokenizer(input);
        int v = Integer.parseInt(st.nextToken());
        int e = Integer.parseInt(st.nextToken());
        int source = 1, sink = 2*v-1;
        MCMF mcmf = new MCMF(2*v+1, source, sink);
        // IN: ODD, OUT: EVEN
        mcmf.add( source, 2, 2, 0, true);
        for (int i = 2; i <= v; i++) {
            mcmf.add(2*i-1, 2*i, 1, 0, true);
        }
        while (e-- > 0) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());
            mcmf.add(from*2, to*2-1, 1, cost, true);

        }
        mcmf.run();
        return mcmf.getMinCost();
    }
}
