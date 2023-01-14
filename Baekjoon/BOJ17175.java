import java.io.*;

public class BOJ17175 {
    private static final int d = 1_000_000_007;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int n = Integer.parseInt(br.readLine());
        bw.write(Integer.toString(answer(n)));
        bw.flush();
    }   
    private static int answer(int n) {
        if (n < 2) return 1;
        int pprev = 1, prev = 1, crnt = 3;
        for (int k = 3; k <= n; k++) {
            pprev = prev;
            prev = crnt;
            crnt = pprev + prev + 1;
            crnt %= d;
        }
        return crnt;
    } 
}
