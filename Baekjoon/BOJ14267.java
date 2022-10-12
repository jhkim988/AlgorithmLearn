import java.io.*;
import java.util.*;

public class BOJ14267 {
    private static int id = 1;
    private static int[] start, end;
    private static class SegmentTree {
        private int n;
        private int[] tree;
        SegmentTree(int n) {
            this.n = n;
            int height = 1;
            while (height < n) height <<= 1;
            height <<= 1;
            tree = new int[height];
        }
        void update(int node, int start, int end, int left, int right, int val) {
            if (right < start || end < left) return;
            if (left <= start && end <= right) {
                tree[node] += val;
                return;
            }
            int mid = (start + end) >> 1;
            update(node<<1, start, mid, left, right, val);
            update(node<<1|1, mid+1, end, left, right, val);
        }
        int get(int node, int start, int end, int idx) {
            if (idx < start || end < idx) return 0;
            if (start == end) return tree[node];
            int mid = (start + end) >> 1;
            return tree[node] + get(node<<1, start, mid, idx) + get(node<<1|1, mid+1, end, idx); 
        }
        int get(int idx) {
            return get(1, 0, n-1, idx);
        }
        void update(int left, int right, int val) {
            update(1, 0, n-1, left, right, val);
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        
        List<Queue<Integer>> graph = new ArrayList<>();
        for (int i = 0; i <= n; i++) {
            graph.add(new LinkedList<>());
        }
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= n; i++) {
            int parent = Integer.parseInt(st.nextToken());
            if (parent < 0) continue;
            graph.get(i).add(parent);
            graph.get(parent).add(i);
        }

        start = new int[n+1];
        end = new int[n+1];
        init(graph);
        SegmentTree sg = new SegmentTree(n+1);
        while (m-- > 0) {
            st = new StringTokenizer(br.readLine());
            int id = Integer.parseInt(st.nextToken());
            int val = Integer.parseInt(st.nextToken());
            sg.update(start[id], end[id], val);
        }
        bw.write(answerString(sg));
        bw.flush();
    }
    private static void init(List<Queue<Integer>> graph) {
        start[1] = id;
        dfs(graph, 1);
    }
    private static void dfs(List<Queue<Integer>> graph, int x) {
        for (int adj : graph.get(x)) {
            if (start[adj] != 0) continue;
            start[adj] = ++id;
            dfs(graph, adj);
        }
        end[x] = id;
    }
    private static String answerString(SegmentTree sg) {
        StringBuilder sb = new StringBuilder();
        sb.append(sg.get(1));
        for (int i = 2; i <= id; i++) {
            sb.append(' ');
            sb.append(sg.get(start[i]));
        }
        return sb.toString();
    }
}
