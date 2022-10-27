import java.io.*;
import java.util.*;

public class BOJ21758 {
    private static long[] arr, psum;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int n = Integer.parseInt(br.readLine());
        arr = new long[n+1];
        psum = new long[n+1];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            arr[i+1] = Integer.parseInt(st.nextToken());
            psum[i+1] = psum[i] + arr[i+1];
        }

        long max = 0;
        // case 1: right
        for (int i = 2; i < n; i++) {
            long val = psum(2, i-1) + psum(i+1, n)*2;
            if (max < val) max = val;
        }

        // case 2: left
        for (int i = n-1; i > 1; i--) {
            long val = psum(1, i-1)*2 + psum(i+1, n-1);
            if (max < val) max = val;
        }

        // case 3: mid
        for (int i = 2; i < n; i++) {
            long val = psum(2, i) + psum(i, n-1);
            if (max < val) max = val;
        }

        bw.write(Long.toString(max));
        bw.flush();
    }
    private static long psum(int lo, int hi) {
        if (lo > hi) return 0;
        return psum[hi] - psum[lo-1];
    }
}