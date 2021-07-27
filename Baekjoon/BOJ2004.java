import java.io.*;

public class BOJ2004 {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    String[] tmp = br.readLine().split(" ");
    long N = Long.parseLong(tmp[0]);
    long K = Long.parseLong(tmp[1]);

    bw.write(Math.min(numZero(N, 2) - numZero(N - K, 2) - numZero(K, 2),
    numZero(N, 5) - numZero(N - K, 5) - numZero(K, 5)) + "\n");
    bw.flush();
  } 
  static long numZero(long n, int pow) {
    long count = 0;
    long tmp = pow;
    for (; tmp <= n; tmp *= pow) {
      count += n / tmp;
    }
    return count;
  }
}
