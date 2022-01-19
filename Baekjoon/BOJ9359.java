import java.io.*;
import java.util.*;

public class BOJ9359 {
  static boolean[] isPrime = new boolean[100_000];
  public static void main(String[] args) throws IOException {
    init();
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    final String form = "Case #";
    int numTest = Integer.parseInt(br.readLine());
    for (int i = 1; i <= numTest; i++) {
      StringTokenizer st = new StringTokenizer(br.readLine());
      long lo = Long.parseLong(st.nextToken());
      long hi = Long.parseLong(st.nextToken());
      int N = Integer.parseInt(st.nextToken());
      ArrayList<Integer> primeDecomp = primeDecomposition(N);
      bw.write(form);
      bw.write(Integer.toString(i));
      bw.write(": ");
      bw.write(Long.toString((hi - recur(primeDecomp, 0, 1, hi)) - (lo - 1 - recur(primeDecomp, 0, 1, lo - 1))));
      bw.newLine();
    }
    bw.flush();
  }
  static void init() {
    // Eratosthenes
    Arrays.fill(isPrime, true);
    isPrime[0] = isPrime[1] = false;
    for (int i = 2; i < isPrime.length; i++) {
      if (!isPrime[i]) continue;
      for (int j = 2; j*i < isPrime.length; j++) {
        isPrime[j*i] = false;
      }
    }
  }
  static ArrayList<Integer> primeDecomposition(int N) {
    ArrayList<Integer> result = new ArrayList<>();
    for (int i = 2; i < isPrime.length; i++) {
      if (isPrime[i] && N % i == 0) {
        result.add(i);
        while (N % i == 0) N /= i;
      }
    }
    if (N != 1) result.add(N);
    return result;
  }
  static long recur(ArrayList<Integer> primes, int idx, long soFar, long bound) {
    // Inclusion-Exclusion principle
    if (idx >= primes.size() || soFar * primes.get(idx) > bound) return 0;
    long ret = (bound / primes.get(idx)) / soFar;
    ret += recur(primes, idx + 1, soFar, bound);
    ret -= recur(primes, idx + 1, soFar * primes.get(idx), bound);
    return ret;
  }
}
