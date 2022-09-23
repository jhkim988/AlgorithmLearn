import java.io.*;
import java.util.*;

public class BOJ15971 {
    private static class Pair {
        int end, len, max;
        Pair(int end, int len) {
            this.end = end;
            this.len = len;
        }
        Pair(int end, int len, int max) {
            this(end, len);
            this.max = max;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int robot1 = Integer.parseInt(st.nextToken());
        int robot2 = Integer.parseInt(st.nextToken());
        
        // graph initialize:
        ArrayList<Queue<Pair>> graph = new ArrayList<>();
        for (int i = 0; i <= n; i++) {
            graph.add(new LinkedList<>());
        }
        for (int i = 1; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int len = Integer.parseInt(st.nextToken());
            graph.get(a).add(new Pair(b, len));
            graph.get(b).add(new Pair(a, len));
        }

        bw.write(Integer.toString(bfs(n, robot1, robot2, graph)));
        bw.flush();
    }
    static int bfs(int n, int robot1, int robot2, ArrayList<Queue<Pair>> graph) {
        boolean[] visit = new boolean[n+1];
        Queue<Pair> que = new LinkedList<>();
        visit[robot1] = true;
        que.add(new Pair(robot1, 0));
        while (!que.isEmpty()) {
            Pair crnt = que.poll();
            if (crnt.end == robot2) {
                return crnt.len - crnt.max;
            }
            for (Pair adj : graph.get(crnt.end)) {
                if (visit[adj.end]) continue;
                int max = Integer.max(adj.len, crnt.max);
                visit[adj.end] = true;
                que.add(new Pair(adj.end, crnt.len + adj.len, max));
            }
        }
        return -1;
    }
}
