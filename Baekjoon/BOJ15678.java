import java.io.*;
import java.util.*;

public class BOJ15678 {
    private static class SegTree {
        private int treeSize;
        private long[] tree;

        SegTree(int n) {
            treeSize = 1;
            while (treeSize < n) treeSize <<= 1;
            treeSize <<= 1;
            tree = new long[treeSize];
        }

        void update(int node, int left, int right, int idx, long val) {
            if (idx < left || right < idx) return;
            if (left == right) {
                tree[node] = val;
                return;
            }
            int mid = (left + right) >> 1;
            update(node<<1, left, mid, idx, val);
            update(node<<1|1, mid+1, right, idx, val);
            tree[node] = Long.max(tree[node<<1], tree[node<<1|1]);
        }

        long get(int node, int left, int right, int start, int end) {
            if (end < left || right < start) return 0;
            if (start <= left && right <= end) return tree[node];
            int mid = (left + right) >> 1;
            return Long.max(get(node<<1, left, mid, start, end), get(node<<1|1, mid+1, right, start, end));
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int d = Integer.parseInt(st.nextToken());
        int[] arr = new int[n];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        bw.write(Long.toString(useDeque(arr, d)));
        bw.flush();
    }

    private static long useSegTree(int[] arr, int d) {
        int n = arr.length;
        SegTree seg = new SegTree(n);
        seg.update(1, 0, n-1, 0, arr[0]);
        long max = arr[0];
        for (int i = 1; i < n; i++) {
            int start = Integer.max(0, i-d);
            long val = arr[i] + seg.get(1, 0, n-1, start, i-1);
            seg.update(1, 0, n-1, i, val);
            if (max < val) max = val;
        }
        return max;
    }

    private static long useDeque(int[] arr, int d) {
        int n = arr.length;
        Deque<Integer> deq = new ArrayDeque<>();
        deq.addFirst(0);
        long[] dp = new long[n];
        dp[0] = arr[0];

        long max = arr[0];
        for (int i = 1; i < n; i++) {
            while (!deq.isEmpty() && i - deq.peekLast() > d) deq.removeLast();
            dp[i] = Long.max(dp[deq.getLast()], 0) + arr[i];
            if (max < dp[i]) max = dp[i];
            while (!deq.isEmpty() && dp[deq.peekFirst()] < dp[i]) deq.removeFirst();
            deq.addFirst(i);
        }
        return max;
    }
}