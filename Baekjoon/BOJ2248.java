import java.io.*;
import java.util.*;

public class BOJ2248 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int l = Integer.parseInt(st.nextToken());
        long i = Long.parseLong(st.nextToken());

        recur(n, l, i);
    }
    private static void recur(int n, int l, long i) {
        if (n == 0) return;
        long cnt = 0;
        for (int j = 0; j <= l; j++) {
            cnt += comb(n-1, j);
        }
        if (cnt < i) {
            System.out.print(1);
            recur(n-1, l-1, i-cnt);
        } else {
            System.out.print(0);
            recur(n-1, l, i);
        }
    }
    private static long comb(int n, int r) {
        long ret = 1;
        for (int i = 0; i < r; i++) {
            ret *= n-i;
            ret /= i+1;
        }
        return ret;
    }
}
