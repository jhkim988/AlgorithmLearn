import java.io.*;
import java.util.*;

public class BOJ1321 {
    private static class SegTree {
        int n;
        int[] tree, arr;
        SegTree(int n, int[] arr) {
            this.n = n;
            this.arr = arr;
            int treeSize = 1;
            while (treeSize < n) treeSize <<= 1;
            treeSize <<= 1;
            tree = new int[treeSize];
            init(1, 0, n-1);
        }

        void init(int node, int start, int end) {
            if (start == end) {
                tree[node] = arr[start];
                return;
            }
            int mid = (start+end) >> 1;
            init(node<<1, start, mid);
            init(node<<1|1, mid+1, end);
            tree[node] = tree[node<<1] + tree[node<<1|1];
        }

        void update(int node, int start, int end, int idx, int val) {
            if (idx < start || end < idx) return;
            if (start == end) {
                tree[node] += val;
                return;
            }
            int mid = (start + end) >> 1;
            update(node<<1, start, mid, idx, val);
            update(node<<1|1, mid+1, end, idx, val);
            tree[node] = tree[node<<1] + tree[node<<1|1];
        }

        int get(int node, int start, int end, int val, int acc) {
            if (start == end) {
                return start;
            }
            int mid = (start + end) >> 1;
            if (val <= acc + tree[node<<1]) return get(node<<1, start, mid, val, acc);
            return get(node<<1|1, mid+1, end, val, acc + tree[node<<1]);
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int n = Integer.parseInt(br.readLine());
        
        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] arr = new int[n+1];
        for (int i = 1; i <= n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        int nQuery = Integer.parseInt(br.readLine());
        SegTree seg = new SegTree(n+1, arr);
        while (nQuery-- > 0) {
            st = new StringTokenizer(br.readLine());
            int type = Integer.parseInt(st.nextToken());
            int army = Integer.parseInt(st.nextToken());
            if (type == 1) {
                int add = Integer.parseInt(st.nextToken());
                seg.update(1, 0, n, army, add);
            } else { 
                bw.write(Integer.toString(seg.get(1, 0, n, army, 0)));
                bw.newLine();
            }
        }
        bw.flush();
    }
}
