import java.io.*;
import java.util.*;

public class BOJ15591 {
    private static boolean[] visit;
    private static Queue<Integer> que = new LinkedList<>();
    private static class Pair {
        int end, weight;
        Pair(int end, int weight) {
            this.end = end;
            this.weight = weight;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int q = Integer.parseInt(st.nextToken());

        ArrayList<Queue<Pair>> graph = new ArrayList<>();
        for (int i = 0; i <= n; i++) {
            graph.add(new LinkedList<>());
        }

        for (int i = 1; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            graph.get(a).add(new Pair(b, w));
            graph.get(b).add(new Pair(a, w));
        }

        visit = new boolean[n+1];

        while (q-- > 0) {
            st = new StringTokenizer(br.readLine());
            int k = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            bw.write(Integer.toString(query(graph, k, v)));
            bw.newLine();
        }
        bw.flush();
    }
    private static int query(ArrayList<Queue<Pair>> graph, int k, int v) {
        return bfs(graph, v, k);
    }
    private static int bfs(ArrayList<Queue<Pair>> graph, int start, int limit) {
        Arrays.fill(visit, false);
        que.clear();
        int count = 0;

        visit[start] = true;
        que.add(start);

        while (!que.isEmpty()) {
            int crnt = que.poll();

            for (Pair adj : graph.get(crnt)) {
                if (visit[adj.end] || adj.weight < limit) continue;
                visit[adj.end] = true;
                que.add(adj.end);
                count++;
            }
        }
        return count;
    }
}