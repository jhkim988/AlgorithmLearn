import java.io.*;

public class BOJ14650 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int n = Integer.parseInt(br.readLine());
        bw.write(Long.toString(2*pow(3, n-2)));
        bw.flush();
    }
    private static long pow(long base, int exp) {
        long ret = 1;
        while (exp != 0) {
            if ((exp&1) != 0) ret *= base;
            base *= base;
            exp >>= 1;
        }
        return ret;
    }
}