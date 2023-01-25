import java.io.*;

public class BOJ14651 {
    private static int d = 1_000_000_009;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int n = Integer.parseInt(br.readLine());
        long answer = 2* pow(3, n-2) % d;
        bw.write(Long.toString(answer));
        bw.flush();
    }  
    
    private static long pow(long base, int exp) {
        if (exp < 0) return 0;
        long ret = 1;
        while (exp > 0) {
            if ((exp & 1) != 0) ret = ret * base % d;
            base = base * base % d;
            exp >>= 1;
        }
        return ret;
    }
}
