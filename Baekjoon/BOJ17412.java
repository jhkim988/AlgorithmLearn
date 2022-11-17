import java.io.*;
import java.util.*;

public class BOJ17412 {
    private static final int START = 1, END = 2;
    private static final int INF = Integer.MAX_VALUE >> 1;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st  = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int p = Integer.parseInt(st.nextToken());
        List<Queue<Integer>> graph = new ArrayList<>();
        for (int i = 0; i <= n; i++) {
            graph.add(new LinkedList<>());
        }
        int[][] cap = new int[n+1][n+1];
        while (p-- > 0) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            cap[start][end]++;
            graph.get(start).add(end);
            graph.get(end).add(start);
        }

        int answer = flow(graph, cap, n);
        bw.write(Integer.toString(answer));
        bw.flush();
    }
    private static int flow(List<Queue<Integer>> graph, int[][] cap, int n) {
        int answer = 0;
        int[][] flow = new int[n+1][n+1];
        int[] par = new int[n+1];
        for (int iter=1;;iter++) {
            Arrays.fill(par, -1);
            Queue<Integer> que = new LinkedList<>();
            que.add(START);
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
