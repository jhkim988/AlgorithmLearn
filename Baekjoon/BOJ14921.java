import java.io.*;
import java.util.*;

public class BOJ14921 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int n = Integer.parseInt(br.readLine());
        int[] arr = new int[n];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        int min = 200_000_001;
        int lo = 0, hi = arr.length-1;
        while (lo < hi) {
            if (Math.abs(arr[lo] + arr[hi]) < Math.abs(min)) min = arr[lo] + arr[hi];
            if (arr[lo] + arr[hi] < 0) lo++;
            else if (arr[lo] + arr[hi] > 0) hi--;
            else break;
        }

        bw.write(Integer.toString(min));
        bw.flush();
    }
}
