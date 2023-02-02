import java.io.*;
import java.util.*;

public class BOJ9463 {
    private static class SegTree {
        private int n;
        private long[] tree;
        SegTree(int n) {
            this.n = n;
            int treeSize = 1;
            while (treeSize < n) treeSize <<= 1;
            treeSize <<= 1;
            tree = new long[treeSize];
        }

        private void update(int node, int start, int end, int idx) {
            if (idx < start || end < idx) return;
            if (start == end) {
                tree[node]++;
                return;
            }
            int mid = (start+end) >> 1;
            update(node<<1, start, mid, idx);
            update(node<<1|1, mid+1, end, idx);
            tree[node] = tree[node<<1] + tree[node<<1|1];
        }

        private long get(int node, int start, int end, int left, int right) {
            if (right < start || end < left) return 0;
            if (left <= start && end <= end) return tree[node];
            int mid = (start+end) >> 1;
            return get(node<<1, start, mid, left, right) + get(node<<1|1, mid+1, end, left, right);
        }

        void update(int idx) {
            update(1, 0, n-1, idx);
        }

        long get(int left, int right) {
            return get(1, 0, n-1, left, right);
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int numTest = Integer.parseInt(br.readLine());
        while (numTest-- > 0) {
            int n = Integer.parseInt(br.readLine());
            StringTokenizer st=  new StringTokenizer(br.readLine());
            int[] per1 = new int[n+1];
            for (int i = 1; i <= n; i++) {
                per1[Integer.parseInt(st.nextToken())] = i;
            }

            st = new StringTokenizer(br.readLine());
            int[] per2 = new int[n+1];
            for (int i = 1; i <= n; i++) {
                per2[i] = per1[Integer.parseInt(st.nextToken())];
            }

            long answer = 0;
            SegTree seg = new SegTree(n+1);
            for (int i = 1; i <= n; i++) {
                answer += seg.get(per2[i], n+1);
                seg.update(per2[i]);
            }

            bw.write(Long.toString(answer));
            bw.newLine();
        }
        bw.flush();
    }
}