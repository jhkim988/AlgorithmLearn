import java.io.*;
import java.util.*;

public class BOJ5214 {
    private static class Pair {
        int end, count;
        Pair(int end, int count) {
            this.end = end;
            this.count = count;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        List<Queue<Integer>> graph = new ArrayList<>();
        for (int i = 0; i <= n+m; i++) {
            graph.add(new LinkedList<>());
        }
        for (int i = n+1; i <= n+m; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < k; j++) {
                int idx = Integer.parseInt(st.nextToken());
                graph.get(idx).add(i);
                graph.get(i).add(idx);
            }
        }

        int count = bfs(graph, n, m);
        bw.write(Integer.toString(count));
        bw.flush();
    }
    private static int bfs(List<Queue<Integer>> graph, int n, int m) {
        if (n == 1) return 1;
        boolean[] visit = new boolean[n+m+1];
        Queue<Pair> que = new LinkedList<>();
        que.add(new Pair(1, 1));
        visit[1] = true;
        while (!que.isEmpty()) {
            Pair crnt = que.poll();
            for (int adj : graph.get(crnt.end)) {
                if (visit[adj]) continue;
                if (adj == n) return crnt.count+1;
                visit[adj] = true;
                que.add(new Pair(adj, crnt.count + (adj <= n ? 1 : 0)));
            }
        }
        return -1;
    }
}
