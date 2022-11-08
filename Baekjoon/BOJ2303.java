import java.io.*;
import java.util.*;

public class BOJ2303 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int n = Integer.parseInt(br.readLine());
        
        int max = 0;
        int idx = 1;
        for (int i = 1; i <= n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int[] arr = new int[5];
            for (int j = 0; j < 5; j++) {
                arr[j] = Integer.parseInt(st.nextToken());
            }
            int val = maxFirstDigit(0, 0, 0, arr);
            if (max <= val) {
                max = val;
                idx = i;
            }
        }

        bw.write(Integer.toString(idx));
        bw.flush();
    }
    private static int maxFirstDigit(int depth, int pos, int acc, int[] arr) {
        if (depth >= 3) return acc % 10;

        int max = -1;
        for (int i = pos; i < arr.length; i++) {
            max = Integer.max(max, maxFirstDigit(depth+1, i+1, acc + arr[i], arr));
        }
        return max;
    }
}