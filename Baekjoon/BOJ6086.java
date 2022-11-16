import java.io.*;
import java.util.*;

public class BOJ6086 {
    private static final int START = 0;
    private static final int END = 25;
    private static final int NUM = 60;
    private static final int INF = Integer.MAX_VALUE >> 1;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int n = Integer.parseInt(br.readLine());
        
        int[][] cap = new int[NUM][NUM];
        List<Queue<Integer>> graph = new ArrayList<>();
        for (int i = 0; i < NUM; i++) {
            graph.add(new LinkedList<>());
        }
        while (n-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int start = st.nextToken().charAt(0) - 'A';
            int end = st.nextToken().charAt(0) - 'A';
            int capacity = Integer.parseInt(st.nextToken());
            cap[start][end] += capacity;
            cap[end][start] += capacity;
            graph.get(start).add(end);
            graph.get(end).add(start);
        }

        int answer = flow(graph, cap);
        bw.write(Integer.toString(answer));
        bw.flush();
    }
    private static int flow(List<Queue<Integer>> graph, int[][] cap) {
        int answer = 0;
        int[][] flow = new int[NUM][NUM];
        for (int iter=1;;iter++) {
            int[] par = new int[NUM];
            Arrays.fill(par, -1);
            Queue<Integer> que = new LinkedList<>();
            que.add(0);
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
