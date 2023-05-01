import java.io.*;
import java.util.*;

public class BOJ14912 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        int sum = 0;
        for (int i = 1; i <= n; i++) {
            sum += String.valueOf(i).chars().filter(c -> c == k + '0').count();
        }
        bw.write(Integer.toString(sum));
        bw.flush();
    }
}