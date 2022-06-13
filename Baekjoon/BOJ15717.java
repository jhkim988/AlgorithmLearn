import java.io.*;

public class BOJ15717 {
  static final long D = 1_000_000_007;
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    long n = Long.parseLong(br.readLine());
    if (n == 0) {
      bw.write("1");
    } else {
      bw.write(Long.toString(pow(2, n-1)));
    }
    bw.newLine();
    bw.flush();
  }
  static long pow(long base, long exp) {
    if (exp == 0) return 1;
    if (exp == 1) return base%D;
    long half = pow(base, exp/2);
    if (exp % 2 == 0) return half*half%D;
    return half*half%D*base%D;
  }
}
