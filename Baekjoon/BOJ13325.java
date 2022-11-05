import java.io.*;
import java.util.*;

public class BOJ13325 {
    private static int n;
    private static int[] weight, add;
    private static long[] psum;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int h = Integer.parseInt(br.readLine());
        n = 1<<(h+1);
        weight = new int[n];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 2; i < n; i++) {
            weight[i] = Integer.parseInt(st.nextToken());
        }

        add = new int[n];
        psum = new long[n];
        recur(1);
        bw.write(Long.toString(psum[1]));
        bw.flush();
    }
    private static long recur(int node) {
        if (node<<1 >= n) {
            return psum[node] = weight[node];
        }
        long left = recur(node<<1) + weight[node];
        long right = recur(node<<1|1) + weight[node];
        long max = Long.max(left, right);
        add[node<<1] += max - left;
        add[node<<1|1] += max - right;
        psum[node] = psum[node<<1] + psum[node<<1|1] + add[node<<1] + add[node<<1|1] + weight[node];
        return max;
    }
}