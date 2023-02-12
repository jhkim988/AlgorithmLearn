import java.io.*;
import java.util.*;

public class BOJ11509 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int n = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        int numArrow = 0;
        int[] arr = new int[1_000_001];
        for (int i = 0; i < n; i++) {
            int height = Integer.parseInt(st.nextToken());
            if (arr[height] > 0) {
                arr[height]--;
                if (height > 0) arr[height-1]++;
            } else {
                numArrow++;
                if (height > 0) arr[height-1]++;
            }
        }

        bw.write(Integer.toString(numArrow));
        bw.flush();
    }
}
