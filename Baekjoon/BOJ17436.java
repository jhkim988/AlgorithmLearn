import java.io.*;
import java.util.*;

public class BOJ17436 {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    StringTokenizer st = new StringTokenizer(br.readLine());
    int numPrime = Integer.parseInt(st.nextToken());
    long range = Long.parseLong(st.nextToken());
    long[] primes = new long[numPrime];
    st = new StringTokenizer(br.readLine());
    for (int i = 0; i < numPrime; i++) primes[i] = Long.parseLong(st.nextToken());
    long answer = recur(primes, 0, 1L, range);
    bw.write(Long.toString(answer));
    bw.newLine();
    bw.flush(); 
  }
  static long recur(long[] primes, int idx, long soFar, long bound) {
    // Inclusion-Exclusion principle
    if (idx >= primes.length || soFar * primes[idx] > bound) return 0;
    long ret = (bound / primes[idx]) / soFar;
    ret += recur(primes, idx + 1, soFar, bound);
    ret -= recur(primes, idx + 1, soFar * primes[idx], bound);
    return ret;
  }
}
