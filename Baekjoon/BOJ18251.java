import java.io.*;
import java.util.*;

public class BOJ18251 {
    private static int n, ptr=1;
    private static int[] tree;
    private static long[] psum;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        n = Integer.parseInt(br.readLine());
    
        StringTokenizer st = new StringTokenizer(br.readLine());
        tree = new int[n+1];
        for (int i = 1; i <= n; i++) {
            tree[i] = Integer.parseInt(st.nextToken());
        }
        int h = 0;
        for (int t = 1; t < n; t <<= 1) h++;
        psum = new long[n+1];
        long answer = tree[1];
        for (int lo = 0; lo < h; lo++) {
            for (int hi = lo; hi < h; hi++) {
                recur(1, 0, lo, hi);
                long value = getMaxInterval(1, ptr-1);
                answer = Long.max(answer, value);
                Arrays.fill(psum, 0L);
                ptr = 1;
            }
        }
        bw.write(Long.toString(answer));
        bw.flush();
    }
    private static void  recur(int node, int depth, int lo, int hi) {
        if (node > n) return;
        recur(node<<1, depth+1, lo, hi);
        if (lo <= depth && depth <= hi) {
            psum[ptr] = psum[ptr-1] + tree[node];
            ptr++;
        };
        recur(node<<1|1, depth+1, lo, hi);
    }
    private static long getMaxInterval(int lo, int hi) {
        if (lo == hi) return psum[lo] - psum[lo-1];
        int mid = (lo + hi) >> 1;
        long leftValue = getMaxInterval(lo, mid);
        long rightValue = getMaxInterval(mid+1, hi);
        long midValue = getMidValue(lo, mid, hi);
        long ret =  Long.max(leftValue, Long.max(midValue, rightValue));
        return ret;
    }
    private static long getMidValue(int lo, int mid, int hi) {
        long leftValue = psum[mid] - psum[lo-1];
        long rightValue = psum[hi] - psum[mid];
        for (int x = lo; x < mid; x++) {
            leftValue = Long.max(leftValue, psum[mid]-psum[x]);
        }
        for (int x = mid+1; x < hi; x++) {
            rightValue = Long.max(rightValue, psum[x]-psum[mid]);
        }
        return leftValue + rightValue;
    }
}
