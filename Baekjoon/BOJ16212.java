import java.io.*;
import java.util.*;

public class BOJ16212 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int n = Integer.parseInt(br.readLine());
        // int[] arr = sort(br.readLine(), n);
        int[] arr = countSort(br.readLine(), n);
        for (int i = 0; i < n; i++) {
            bw.write(Integer.toString(arr[i]));
            bw.write(' ');
        }
        bw.flush();
    }
    private static int[] sort(String line, int n) {
        StringTokenizer st = new StringTokenizer(line);
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(arr);
        return arr;
    }
    private static int[] countSort(String line, int n) {
        StringTokenizer st = new StringTokenizer(line);
        int[] count = new int[4_000_001];
        for (int i = 0; i < n; i++) {
            count[Integer.parseInt(st.nextToken()) + 2_000_000]++;
        }
        int[] arr = new int[n];
        for (int i = 0, j = 0; i < count.length; i++) {
            while (count[i]-- > 0) {
                arr[j++] = i - 2_000_000;
            }
        }
        return arr;
    }
}
