import java.io.*;
import java.util.*;

public class BOJ12912 {
    private static class Pair {
        int end;
        long cost;
        Pair(int end, long cost) {
            this.end= end;
            this.cost = cost;
        }
    }
    private static class Edge {
        int from, to;
        long cost;
        Edge(int from, int to, long cost) {
            this.from = from;
            this.to = to;
            this.cost = cost;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int n = Integer.parseInt(br.readLine());
        ArrayList<Queue<Pair>> tree = new ArrayList<>();
        ArrayList<Edge> edges = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            tree.add(new LinkedList<>());
        } 
        for (int i = 0; i < n-1; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            long cost = Long.parseLong(st.nextToken());
            tree.get(from).add(new Pair(to, cost));
            tree.get(to).add(new Pair(from, cost));
            edges.add(new Edge(from, to, cost));
        }

        long max = 0;
        for (Edge edge : edges) {
            long val = diameter(tree, edge.from, edge.to, false) + diameter(tree, edge.to, edge.from, false) + edge.cost;
            if (max < val) max = val;
        }

        bw.write(Long.toString(max));
        bw.flush();
    }

    private static long diameter(ArrayList<Queue<Pair>> tree, int start, int except, boolean flag) {
        boolean[] visit = new boolean[tree.size()];
        Queue<Pair> que = new LinkedList<>();
        que.add(new Pair(start, 0));
        visit[start] = true;

        long maxCost = 0;
        int maxIdx = start;
        while (!que.isEmpty()) {
            Pair crnt = que.poll();
            for (Pair adj : tree.get(crnt.end)) {
                if (visit[adj.end] || adj.end == except) continue;
                visit[adj.end] = true;
                que.add(new Pair(adj.end, crnt.cost + adj.cost));
                if (maxCost < crnt.cost + adj.cost) {
                    maxCost = crnt.cost + adj.cost;
                    maxIdx = adj.end;
                }
            }
        }
        return flag ? maxCost : diameter(tree, maxIdx, except, true);
    }
}
