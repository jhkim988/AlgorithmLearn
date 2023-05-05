import java.io.*;
import java.util.*;

public class BOJ3135 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int start = Integer.parseInt(st.nextToken());
        int target = Integer.parseInt(st.nextToken());
        
        final int N = Integer.parseInt(br.readLine());
        int min = Math.abs(start - target);
        for (int i = 0; i < N; i++) {
            int val = Integer.parseInt(br.readLine());
            min = Math.min(min, Math.abs(target - val) + 1);
        }

        bw.write(Integer.toString(min));
        bw.flush();
    }
}