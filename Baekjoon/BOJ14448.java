import java.io.*;

public class BOJ14448 {
    private static int[] copy;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int n = Integer.parseInt(br.readLine());
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }

        int answer = simulatedAnnealing(n, arr);
        bw.write(Integer.toString(answer));
        bw.flush();
    }

    private static int simulatedAnnealing(int n, int[] arr) {
        int ret = 0;
        copy = new int[n];
        boolean[] flip = new boolean[n];
        for (int i = 0; i < 10; i++) {
            double t = 1, lim = 0.001, d = 0.9999, k = 10;
            while (t > lim) {
                int e1 = scoring(n, arr, flip);
                int idx = (int) (Math.random() * n);
                flip[idx] = !flip[idx];
                int e2 = scoring(n, arr, flip);
    
                double p = Math.exp((e2 - e1) / (k * t));
                if (p < Math.random()) {
                    flip[idx] = !flip[idx];
                }
                t *= d;
                ret = Integer.max(ret, Integer.max(e1, e2));
            }
        }
        return ret;
    }

    private static int scoring(int n, int[] arr, boolean[] flip) {
        System.arraycopy(arr, 0, copy, 0, n);
        int lo = 0, hi = n - 1;
        while (lo < hi) {
            while (lo < n && !flip[lo])
                lo++;
            while (hi >= 0 && !flip[hi])
                hi--;
            if (lo < hi && flip[lo] && flip[hi]) {
                swap(copy, lo++, hi--);
            }
        }
        int ret = lis(copy);
        System.arraycopy(arr, 0, copy, 0, n);
        lo = 0;
        hi = n - 1;
        while (lo < hi) {
            while (lo < n && flip[lo])
                lo++;
            while (hi >= 0 && flip[hi])
                hi--;
            if (lo < hi && !flip[lo] && !flip[hi]) {
                swap(copy, lo++, hi--);
            }
        }
        return Integer.max(ret, lis(copy));
    }

    private static int lis(int[] arr) {
        int len = arr.length;
        int[] dp = new int[len + 1];
        int ptr = 1;
        for (int i = 0; i < len; i++) {
            int find = binSearch(dp, 0, ptr, arr[i]);
            dp[find] = arr[i];
            if (find == ptr)
                ptr++;
        }
        return ptr - 1;
    }

    private static int binSearch(int[] arr, int lo, int hi, int key) {
        while (lo + 1 < hi) {
            int mid = (lo + hi) >> 1;
            if (arr[mid] <= key) {
                lo = mid;
            } else {
                hi = mid;
            }
        }
        return hi;
    }

    private static void swap(int[] arr, int idx1, int idx2) {
        int val = arr[idx1];
        arr[idx1] = arr[idx2];
        arr[idx2] = val;
    }
}
