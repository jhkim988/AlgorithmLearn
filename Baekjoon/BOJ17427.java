import java.io.*;

public class BOJ17427 {
  static boolean[] isPrime;
  static int N;
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    N = Integer.parseInt(br.readLine());
    init();
    long sum = 0L;
    for (int i = 1; i <= N; i++) {
      sum += sumDivisors(i);
    }
    bw.write(sum + "\n");
    bw.flush();
  } 
  static long sumDivisors(int n) {
    long sum = 1L;
    mainLoop: while (n != 1) {
      // System.out.println("sum: " + sum + ", n: " + n);
      if (isPrime[n]) {
        sum *= 1 + n;
        break;
      }
      for (int i = 2; i < n; i++) {
        if (n % i == 0 && isPrime[i]) {
          int tmp = 0;
          int div = 1;
          while (n % div == 0) {
            tmp += div;
            div *= i;
          }
          sum *= tmp;
          div /= i;
          n /= div;
          continue mainLoop;
        }
      }
    }
    return sum;
  }
  static void init() {
    isPrime = new boolean[N + 1];
    for (int i = 2; i <= N; i++) {
      isPrime[i] = true;
    }
    int ptr = 2;
    while (ptr * ptr <= N) {
      for (int i = 2 * ptr; i <= N; i += ptr) {
        isPrime[i] = false;
      }
      while (ptr <= N && !isPrime[++ptr]);
    }
  }
}
