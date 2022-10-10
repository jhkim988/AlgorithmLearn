import java.io.*;
import java.util.*;

public class BOJ11568 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int n = Integer.parseInt(br.readLine());
        
        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        int ptr = 1;
        int[] dp = new int[n+1];
        for (int i = 0; i < n; i++) {
            int idx = binSearch(dp, 0, ptr, arr[i]); // find [0, ptr)
            if (idx == ptr) {
                dp[ptr++] = arr[i];
            } else {
                dp[idx] = arr[i];
            }
        }

        bw.write(Integer.toString(ptr-1));
        bw.flush();
    }
    private static int binSearch(int[] dp, int lo, int hi, int key) {
        while (lo + 1 < hi) {
            int mid = (lo + hi) >> 1;
            if (dp[mid] < key) {
                lo = mid;
            } else {
                hi = mid;
            }
        }
        return hi;
    }
}
