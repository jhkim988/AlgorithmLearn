import java.io.*;
import java.util.*;

public class BOJ2548 {
    private static int[] copy;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int n = Integer.parseInt(br.readLine());
        int[] arr = new int[n+1];
        int[] psum = new int[n+1];
        
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        copy = new int[n];
        // Arrays.sort(arr);
        mergeSort(arr, 0, n);
        for (int i = 1; i <= n; i++) {
            psum[i] = psum[i-1] + arr[i];
        }

        int minVal = Integer.MAX_VALUE;
        int minIdx = 1;
        for (int i = 1; i <= n; i++) {
            int val = (i-1)*arr[i]-psum[i-1] + (psum[n]-psum[i])-(n-i)*arr[i];
            if (val < minVal) {
                minIdx = i;
                minVal = val;
            }
        }
        bw.write(Integer.toString(arr[minIdx]));
        bw.flush();
    }
    private static void mergeSort(int[] arr, int lo, int hi) {
        if (hi <= lo) return;
        int mid = (lo+hi) >> 1;
        mergeSort(arr, lo, mid);
        mergeSort(arr, mid+1, hi);
        
        System.arraycopy(arr, lo, copy, 0, mid-lo+1);

        int ptr = lo;
        int rptr = mid+1;
        for (int lptr = 0; lptr <= mid-lo; lptr++) {
            while (rptr <= hi && arr[rptr] <= copy[lptr]) {
                arr[ptr] = arr[rptr];
                ptr++; rptr++;
            }
            arr[ptr] = copy[lptr];
            ptr++;
        }
    }
}