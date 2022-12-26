import java.io.*;
import java.util.*;

public class BOJ1199 {
    private static boolean[] visit;
    private static Queue<Edge>[] adj;
    private static StringBuilder sb = new StringBuilder();

    private static class Edge {
        private final int v;
        private final int w;
        int count;

        public Edge(int v, int w, int count) {
            this.v = v;
            this.w = w;
            this.count = count;
        }

        // returns the other vertex of the edge
        public int other(int vertex) {
            if (vertex == v) return w;
            else if (vertex == w) return v;
            else throw new IllegalArgumentException("Illegal endpoint");
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int n = Integer.parseInt(br.readLine());
        adj = (Queue<Edge>[]) new Queue[n];
        for (int i = 0; i < n; i++) {
            adj[i] = new ArrayDeque<>();
        }
        int[] degree = new int[n];
        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < i; j++) {
                int val = Integer.parseInt(st.nextToken());
                if (val == 0) continue;
                degree[i] += val;
                degree[j] += val;
                Edge edge = new Edge(i, j, val);
                adj[i].add(edge);
                adj[j].add(edge);
            }
        }
        for (int i = 0; i < n; i++) {
            if (degree[i] % 2 == 0) continue;
            System.out.println("-1");
            return;
        }

        visit = new boolean[n];

        boolean flag = false;
        int start = -1;
        for (int i = 0; i < n; i++) {
            if (visit[i]) continue;
            int cSize = dfs(i);
            if (cSize > 1) {
                if (flag) {
                    System.out.println("-1");
                    return;
                } else {
                    flag = true;
                    start = i;
                }
            }
        }

        if (start == -1) start = 0;
        eulerCircuit(start);
        bw.write(sb.toString());
        bw.flush();
    }
    private static void eulerCircuit(int node) {
        Stack<Integer> stack = new Stack<>();
        stack.push(node);
        while (!stack.isEmpty()) {
            int v = stack.pop();
            while (!adj[v].isEmpty()) {
                Edge edge = adj[v].poll();
                if (edge.count <= 0) continue;
                edge.count--;
                if (edge.count > 0) {
                    adj[v].add(edge);
                }
                stack.push(v);
                v = edge.other(v);
            }
            // push vertex with no more leaving edges to path
            sb.append(v+1).append(' ');
        }
    }

    private static int dfs(int node) {
        int ret = 1;
        visit[node] = true;
        for (Edge edge : adj[node]) {
            if (visit[edge.other(node)]) continue;
            ret += dfs(edge.other(node));
        }
        return ret;
    }
}
