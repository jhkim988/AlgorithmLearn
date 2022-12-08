import java.io.*;
import java.util.*;

public class BOJ2501 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        
        int count = 0;
        int answer= 0;
        for (int q = 1; q <= n && count != k; q++) {
            if (n%q == 0) {
                count++;
                answer = q;
            }
        }
        if (count == k) {
            bw.write(Integer.toString(answer));
        } else {
            bw.write("0");
        }
        bw.flush();
    }
}
