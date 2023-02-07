import java.io.*;
import java.util.*;

public class BOJ1312 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int a = Integer.parseInt(st.nextToken());
        int b = Integer.parseInt(st.nextToken());
        int n = Integer.parseInt(st.nextToken());
        int x = a%b;
        for (int i = 1; i < n; i++) {
            x *= 10;
            x %= b;
        }
        double val = ((double) x) / b;
        bw.write(Integer.toString((int) (val * 10)));
        bw.flush();
    }
}
