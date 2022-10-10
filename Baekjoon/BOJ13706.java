import java.io.*;
import java.math.BigInteger;

public class BOJ13706 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        BigInteger n = new BigInteger(br.readLine());
        
        bw.write(sqrt(n).toString());
        bw.flush();
    }
    private static BigInteger sqrt(BigInteger x) {
        BigInteger lo = BigInteger.valueOf(0);
        BigInteger hi = x;
        while (lo.add(BigInteger.ONE).compareTo(hi) < 0) {
            BigInteger mid = lo.add(hi).divide(BigInteger.TWO);
            if (mid.multiply(mid).compareTo(x) < 0) {
                lo = mid;
            } else {
                hi = mid;
            }
        }
        return hi;
    }
}
