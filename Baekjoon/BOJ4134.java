import java.io.*;

public class BOJ4134 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int nTest = Integer.parseInt(br.readLine());
        while (nTest-- > 0) {
            bw.write(Long.toString(nextPrime(Long.parseLong(br.readLine()))));
            bw.newLine();    
        }
        bw.flush();
    }
    private static boolean isPrime(long x) {
        if (x <= 1) return false;
        for (long i = 2; i*i <= x; i++) {
            if (x % i == 0) return false;
        }
        return true;
    }
    private static long nextPrime(long x) {
        while (!isPrime(x++));
        return x-1;
    }
}
