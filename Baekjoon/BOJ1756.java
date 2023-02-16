import java.io.*;
import java.util.*;

public class BOJ1756 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int d = Integer.parseInt(st.nextToken());
        int n = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        int[] min = new int[d];
        for (int i = 0; i < d; i++) {
            min[i] = Integer.parseInt(st.nextToken());
        }
        for (int i = 1; i < d; i++) {
            min[i] = Integer.min(min[i-1], min[i]);
        }

        st = new StringTokenizer(br.readLine());
        int[] pizza = new int[n];
        for (int i = 0; i < n; i++) {
            pizza[i] = Integer.parseInt(st.nextToken());
        }

        bw.write(Integer.toString(useLinear(min, pizza)));
        bw.flush();
    }
    private static int useLinear(int[] min, int[] pizza) {
        int ptr = 0;
        int height = 0;
        for (int i = min.length-1; i >= 0; i--) {
            if (ptr >= pizza.length) return height+1;
            if (pizza[ptr] > min[i]) continue;
            ptr++;
            height = i;
        }
        if (ptr != pizza.length) return 0;
        return height+1;
    }

    private static int useBinSearch(int[] min, int[] pizza) {
        int lo = -1, hi = min.length;
        for (int val : pizza) {
            hi = find(min, lo, hi, val) - 1;
            if (hi < 0) return 0;
        }
        return hi+1;
    }

    private static int find(int[] arr, int lo, int hi, int key) {
        while (lo + 1 < hi) {
            int mid = (lo + hi) >> 1;
            if (arr[mid] < key) {
                hi = mid;
            } else {
                lo = mid;
            }
        }
        return hi;
    }
}
