import java.io.*;

public class BOJ14495 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int n = Integer.parseInt(br.readLine());
        bw.write(Long.toString(likeFibo(n)));
        bw.flush();
    }

    private static long likeFibo(int n) {
        if (n <= 3) return 1;
        long ppprev = 1, pprev = 1, prev = 1, crnt = 2;
        for (int cursor = 4; cursor < n; cursor++) {
            ppprev = pprev;
            pprev = prev;
            prev = crnt;
            crnt = prev + ppprev;
        }
        return crnt;
    } 
}
