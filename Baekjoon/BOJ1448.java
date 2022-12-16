import java.io.*;
import java.util.*;

public class BOJ1448 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int n = Integer.parseInt(br.readLine());
        int[] arr = new int[n];
        int[] bucket = new int[1_000_001]; // use Count sort
        for (int i = 0; i < n; i++) {
            bucket[Integer.parseInt(br.readLine())]++;
        }

        int ptr = 0;
        for (int i = 0; i < bucket.length; i++) {
            if (bucket[i] <= 0) continue;
            for (int j = 0; j < bucket[i]; j++) {
                arr[ptr] = i;
                ++ptr;
            }
        }

        int answer = -1;
        for (int i = n-1; i > 1; i--) {
            if (arr[i] < arr[i-1] + arr[i-2]) {
                answer = arr[i] + arr[i-1] + arr[i-2];
                break;
            }
        }

        bw.write(Integer.toString(answer));
        bw.flush();
    }
    
}
