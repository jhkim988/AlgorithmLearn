import java.io.*;
import java.util.*;

public class BOJ2316 {
    private static int START = 1, END = 2;
    private static final int INF = Integer.MAX_VALUE >> 1;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st  = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int p = Integer.parseInt(st.nextToken());
        int n2 = n<<1;
        START = n+1;
        END = 2;
        // in: 1 ~ n
        // out: n+1 ~ 2*n
        List<Queue<Integer>> graph = new ArrayList<>();
        for (int i = 0; i <= n2; i++) {
            graph.add(new LinkedList<>());
        }
        int[][] cap = new int[n2+1][n2+1];
        for (int i = 1; i <= n; i++) {
            // same node: in -> out
            graph.get(i).add(i+n);
            graph.get(i+n).add(i);
            cap[i][i+n] = 1;
        }
        while (p-- > 0) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            // diff node: out -> in
            cap[start+n][end] = INF;
            cap[end+n][start] = INF;
            graph.get(start+n).add(end);
            graph.get(end+n).add(start);
            graph.get(end).add(start+n);
            graph.get(start).add(end+n);
        }

        int answer = flow(graph, cap, n2);
        bw.write(Integer.toString(answer));
        bw.flush();
    }
    private static int flow(List<Queue<Integer>> graph, int[][] cap, int n) {
        int answer = 0;
        int[][] flow = new int[n+1][n+1];
        int[] par = new int[n+1];
        while (true) {
            Arrays.fill(par, -1);
            Queue<Integer> que = new LinkedList<>();
            que.add(START);
            par[START] = 0;
            while (!que.isEmpty()) {
                int crnt = que.poll();
                for (int next : graph.get(crnt)) {
                    if (par[next] == -1 && cap[crnt][next] - flow[crnt][next] > 0) {
                        que.add(next);
                        par[next] = crnt;
                        if (next == END) break;
                    }
                }
            }
            if (par[END] == -1) break;
            int cost = INF;
            for (int i = END; i != START; i = par[i]) {
                cost = Integer.min(cost, cap[par[i]][i] - flow[par[i]][i]);
            }

            for (int i = END; i != START; i = par[i]) {
                flow[par[i]][i] += cost;
                flow[i][par[i]] -= cost;
            }
            answer += cost;
        }
        return answer;
    }
}
