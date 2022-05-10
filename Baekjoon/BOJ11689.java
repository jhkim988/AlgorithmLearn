import java.io.*;
import java.util.*;

public class BOJ11689 {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    long n = Long.parseLong(br.readLine());
    boolean[] isPrime = new boolean[1_000_000];
    Arrays.fill(isPrime, true);
    isPrime[1] = false;
    for (int i = 2; i*i <= 1_000_000; i++) {
      if (!isPrime[i]) continue;
      for (int j = i*2; j < 1_000_000; j += i) {
        isPrime[j] = false;
      }
    }

    long answer = 1;
    for (int i = 2; i < 1_000_000; i++) {
      if (n % i == 0 && isPrime[i]) {
        long divisor = i;
        while (n%(divisor*i) == 0) {
          divisor *= i;
        }
        n /= divisor;
        answer *= divisor/i*(i-1);
      }
    }
    if (n != 1) answer *= n-1;
    bw.write(Long.toString(answer));
    bw.flush();
  }
}
