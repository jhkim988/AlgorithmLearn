import java.io.*;
import java.util.*;

public class BOJ1219 {
    private static final long NEGATIVE_INF = Long.MIN_VALUE/2;
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
        int start = Integer.parseInt(st.nextToken());
        int end = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        List<Queue<Pair>> graph = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            graph.add(new LinkedList<>());
        }
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            graph.get(a).add(new Pair(b, w));
        }

        int[] earn = new int[n];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            earn[i] = Integer.parseInt(st.nextToken());
        }
        bw.write(bellmanFord(graph, earn, start, end));
        bw.flush();
    }
    private static String bellmanFord(List<Queue<Pair>> graph, int[] earn, int start, int end) {
        int n = graph.size();
        long[] ret = new long[n];
        Arrays.fill(ret, NEGATIVE_INF);
        ret[start] = earn[start];

        for (int k = 1; k < n; k++) {
            for (int i = 0; i < n; i++) {
                for (Pair adj : graph.get(i)) {
                    if (ret[i] == NEGATIVE_INF || ret[adj.end] >= ret[i] - adj.weight + earn[adj.end]) continue;
                    ret[adj.end] = ret[i] - adj.weight + earn[adj.end];
                }
            }
        }
        if (ret[end] == NEGATIVE_INF) return "gg";
        Set<Integer> infiniteCycleMembers = new HashSet<>();
        for (int i = 0; i < n; i++) {
            for (Pair adj : graph.get(i)) {
                if (ret[i] == NEGATIVE_INF || ret[adj.end] >= ret[i] - adj.weight + earn[adj.end]) continue;
                infiniteCycleMembers.add(i);
                infiniteCycleMembers.add(adj.end);
            }
        }
        for (int x : infiniteCycleMembers) {
            if (simplyReachable(graph, x, end) && simplyReachable(graph, x, end)) return "Gee"; 
        }
        return Long.toString(ret[end]);
    }

    private static boolean simplyReachable(List<Queue<Pair>> graph, int start, int end) {
        if (start == end) return true;
        int n = graph.size();
        boolean[] visit = new boolean[n];
        Queue<Integer> que = new LinkedList<>();
        visit[start] = true;
        que.add(start);

        while (!que.isEmpty()) {
            int crnt = que.poll();
            for (Pair adj : graph.get(crnt)) {
                if (adj.end == end) return true;
                if (visit[adj.end]) continue;
                visit[adj.end] = true;
                que.add(adj.end);
            }
        }
        return false;
    }
}