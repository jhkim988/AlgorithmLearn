import java.io.*;

public class BOJ15624 {
    private static final int d = 1_000_000_007;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int n = Integer.parseInt(br.readLine());
        bw.write(Integer.toString(fibo(n)));
        bw.flush();
    }

    private static int fibo(int n) {
        if (n <= 0) return 0;
        if (n == 1) return 1;
        int pprev = 0, prev = 1, cur = 1;
        for (int ptr = 2; ptr < n; ptr++) {
            pprev = prev;
            prev = cur;
            cur = (pprev + prev) % d;
        }
        return cur;
    }
}
