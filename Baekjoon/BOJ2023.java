import java.io.*;
import java.util.*;

public class BOJ2023 {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    int n = Integer.parseInt(br.readLine());
    int digit = 1;
    while (n-- > 0) digit *= 10;
    boolean[] isPrime = new boolean[digit];
    Arrays.fill(isPrime, true);
    isPrime[0] = isPrime[1] = false;
    for (int i = 2; i*i <= digit; i++) {
      if (!isPrime[i]) continue;
      for (int j = i+i; j < digit; j += i) {
        isPrime[j] = false;
      }
    }
    next: for (int i = digit/10; i < digit; i++) {
      int test = i;
      while (test > 0) {
        if (!isPrime[test]) continue next;
        test /= 10;
      }
      bw.write(Integer.toString(i));
      bw.newLine();
    }
    bw.flush();
  }
}
