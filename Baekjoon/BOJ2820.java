import java.io.*;
import java.util.*;

public class BOJ2820 {

    private static class EulerTour {
        private int id = 0;
        int[] in, out;
        List<Queue<Integer>> graph;

        EulerTour(int n, List<Queue<Integer>> graph) {
            in = new int[n];
            out = new int[n];
            this.graph = graph;
            dfs(1);
        }

        void dfs(int node) {
            in[node] = ++id;
            for (int adj : graph.get(node)) {
                if (in[adj] == 0) dfs(adj); 
            }
            out[node] = id;
        }
    }

    private static class SegTree {
        int[] tree;
        SegTree(int n) {
            int treeSize = 1;
            while (treeSize < n) treeSize <<= 1;
            treeSize <<= 1;
            tree = new int[treeSize];
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
            if (end < idx || idx < start) return 0;
            if (start == end) return tree[node];
            int mid = (start + end) >> 1;
            return get(node<<1, start, mid, idx) + get(node<<1|1, mid+1, end, idx)+ tree[node];
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        List<Queue<Integer>> graph = new ArrayList<>();
        int[] salaryArr = new int[n+1];
        for (int i = 0; i <= n; i++) {
            graph.add(new ArrayDeque<>());
        }
        salaryArr[1] = Integer.parseInt(br.readLine());
        for (int i = 2; i <= n; i++) {
            st = new StringTokenizer(br.readLine());
            int salary = Integer.parseInt(st.nextToken());
            int parent = Integer.parseInt(st.nextToken());
            graph.get(parent).add(i);
            salaryArr[i] = salary;
        }

        EulerTour euler = new EulerTour(n+1, graph);
        int[] in = euler.in;
        int[] out = euler.out;
        SegTree seg = new SegTree(n+1);
        while (m-- > 0) {
            st = new StringTokenizer(br.readLine());
            char type = st.nextToken().charAt(0);
            if (type == 'p') {
                int node = Integer.parseInt(st.nextToken());
                int salary = Integer.parseInt(st.nextToken());
                seg.update(1, 0, n, in[node], out[node], salary);
                salaryArr[node] -= salary;
            } else {
                int node = Integer.parseInt(st.nextToken());
                bw.write(Integer.toString(salaryArr[node] + seg.get(1, 0, n, in[node])));
                bw.newLine();
            }
        }
        bw.flush();

        
    }
}
