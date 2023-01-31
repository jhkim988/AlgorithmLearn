import java.io.*;
import java.util.*;

public class BOJ18227 {
    private static class SegTree {
        private long[] tree;
        private int[] level;
        SegTree(int n, int[] level) {
            int treeSize = 1;
            while (treeSize < n) treeSize <<= 1;
            treeSize <<= 1;
            tree = new long[treeSize];
            this.level = level;
        }

        void update(int node, int start, int end, int idx, int depth) {
            if (idx < start || end < idx) return;
            if (start == end) {
                tree[node] += level[idx];
                return;
            }
            int mid = (start + end) >> 1;
            update(node<<1, start, mid, idx, depth+1);
            update(node<<1|1, mid+1, end, idx, depth+1);
            tree[node] = tree[node<<1] + tree[node<<1|1] + level[idx] - depth;
        }
        
        long get(int node, int start, int end, int idx) {
            if (idx < start || end < idx) return 0;
            if (start == end) return tree[node];
            int mid = (start + end) >> 1;
            if (idx <= mid) return get(node<<1, start, mid, idx) + tree[node];
            else return get(node<<1|1, mid+1, end, idx) + tree[node];
        }
    }

    private static class EulerTour {
        private int tour = 0;
        private int[] in, out, level;
        private List<Queue<Integer>> graph;
        
        EulerTour(List<Queue<Integer>> graph, int root) {
            int n = graph.size();
            this.graph = graph;
            in = new int[n];
            out = new int[n];
            level = new int[n];
            dfs(root, 1);
        }

        private void dfs(int node, int depth) {
            in[node] = ++tour;
            level[node] = depth;
            for (int adj : graph.get(node)) {
                if (in[adj] == 0) dfs(adj, depth+1);
            }
            out[node] = tour;
        }

        private int[] getIn() {
            return in;
        }

        private int[] getOut() {
            return out;
        }

        private int[] getLevel() {
            return level;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int c = Integer.parseInt(st.nextToken());

        List<Queue<Integer>> graph = new ArrayList<>();
        for (int i = 0; i <= n; i++) {
            graph.add(new ArrayDeque<>());
        }
        for (int i = 1; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            graph.get(start).add(end);
            graph.get(end).add(start);
        }

        EulerTour eulerTour = new EulerTour(graph, c);
        int[] in = eulerTour.getIn();
        int[] out = eulerTour.getOut();
        int[] level = eulerTour.getLevel();

        SegTree sg = new SegTree(n+1, level);
        int nQuery = Integer.parseInt(br.readLine());
        while (nQuery-- > 0) {
            st = new StringTokenizer(br.readLine());
            int type = Integer.parseInt(st.nextToken());
            int id = Integer.parseInt(st.nextToken());
            if (type == 1) {
                sg.update(1, 0, n, in[id], 1);
            } else {
                bw.write(Long.toString(sg.get(1, 0, n, in[id])));
                bw.newLine();
            }
        }
        bw.flush();
    }
}
