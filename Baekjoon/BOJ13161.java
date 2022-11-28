import java.io.*;
import java.util.*;

public class BOJ13161 {
    private static final int INF = Integer.MAX_VALUE >> 4;
    private static class MCMF {
        private final int size, source, sink;
        private final int[][] capacity, cost, flow;
        private final List<Queue<Integer>> graph;
        private int maxFlow = 0, minCost = 0;
        MCMF(int size, int source, int sink, int[][] cost) {
            this.size = size;
            this.source = source;
            this.sink = sink;
            this.capacity = new int[size][size];
            this.cost = cost;
            this.flow = new int[size][size];
            this.graph = new ArrayList<>();
            for (int i = 0; i <= size; i++) {
                graph.add(new LinkedList<>());
            }
        }
        void add(int from, int to, int cap) {
            graph.get(from).add(to);
            graph.get(to).add(from);
            capacity[from][to] = cap;
        }

        void run() {
            maxFlow = 0;
            minCost = 0;
            int[] par = new int[size];
            while (true) {
                Arrays.fill(par, -1);
                Integer val = spfa(par);
                if (val == null) return;
                maxFlow += flowUpdate(par);
                minCost += val;
            }
        }
        
        Integer spfa(int[] par) {
            Queue<Integer> que = new LinkedList<>();
            boolean[] inQue = new boolean[size];
            int[] dist = new int[size];
            Arrays.fill(dist, INF);
            que.add(source);
            par[source] = -2;
            dist[source] = 0;
            inQue[source] = true;
            while (!que.isEmpty()) {
                int crnt = que.poll();
                for (int next : graph.get(crnt)) {
                    if (capacity[crnt][next] - flow[crnt][next] <= 0) continue;
                    if (dist[next] <= dist[crnt] + cost[crnt][next]) continue;
                    dist[next] = dist[crnt] + cost[crnt][next];
                    par[next] = crnt;
                    if (!inQue[next]) {
                        inQue[next] = true;
                        que.add(next);
                        // if (numVisit[next] >= size) return false;
                    }
                }
            }
            if (par[sink] == -1) return null;
            return dist[sink];
        }

        int flowUpdate(int[] par) {
            int min = INF;
            for (int node = sink; node != source; node = par[node]) {
                int f = capacity[par[node]][node] - flow[par[node]][node];
                if (f < min) min = f;
            }
            for (int node = sink; node != source; node = par[node]) {
                flow[par[node]][node] += min;
                flow[node][par[node]] -= min;
            }
            return min;
        }

        int getMinCost() {
            System.out.println("maxFlow: " + maxFlow);
            return minCost;
        }

        int[][] getFlow() {
            return flow;
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
        
        int[][] cost = new int[n*2+2][n*2+2];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                cost[i][j+n] = Integer.parseInt(st.nextToken());
            }
        }

        final int size = n*2+2;
        final int source = n*2;
        final int sink = n*2+1;
        MCMF mcmf = new MCMF(size, source, sink, cost);
        for (int i = 0; i < n; i++) {
            if (team[i] == 2) continue;
            mcmf.add(source, i, INF);
            for (int j = 0; j < n; j++) {
                if (team[j] == 1 || i == j) continue;
                mcmf.add(i, j+n, 1);
            }
        }
        for (int i = 0; i < n; i++) {
            if (team[i] == 1) continue;
            mcmf.add(i+n, sink, INF);
        }
        mcmf.run();

        int minCost = mcmf.getMinCost();
        int[][] flow = mcmf.getFlow();
        List<Integer> sourceNeighbors = new ArrayList<>();
        List<Integer> sinkNeighbors = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            if (flow[source][i] == 0) continue;
            sourceNeighbors.add(i);
        }
        bw.write(Integer.toString(minCost));
        bw.newLine();
        bw.write(printList(sourceNeighbors));
        bw.newLine();
        bw.write(printList(sinkNeighbors));
        bw.flush();
    }
    private static String printList(List<Integer> list) {
        if (list.size() == 0) return "";
        StringBuilder sb = new StringBuilder();
        sb.append(list.get(0));
        for (int i = 1; i < list.size(); i++) {
            sb.append(' ').append(list.get(i));
        }
        return sb.toString();
    }
}
