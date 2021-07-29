import java.util.*;

public class BOJ11401 {
  static long p = 1_000_000_007;
  static HashMap<Pair, Long> powerHM = new HashMap<>();
  static HashMap<Long, Long> reverseHM = new HashMap<>();
  private static class Pair {
    long base;
    long expo;
    Pair(long base, long expo) {
      this.base = base;
      this.expo = expo;
    }
    public boolean equals(Object o) {
      if (this == o) {
        return true;
      }
      if (getClass() != o.getClass()) {
        return false;
      }
      Pair other = (Pair) o;
      if (base != other.base || expo != other.expo) {
        return false;
      }
      return true;
    }
    public int hashCode() {
      int result = (int) Long.hashCode(base) ^ (Long.hashCode(base) >>> 32);
      result = result * 31 + (int) Long.hashCode(expo) ^ (Long.hashCode(expo) >>> 32);
      return result;
    }
  }
  public static void main(String[] args) {
    Scanner scn = new Scanner(System.in);
    int N = scn.nextInt();
    int K = scn.nextInt();
    scn.close();
    System.out.println((((factorial(N) * reverse(factorial(K))) % p) * reverse(factorial(N - K))) % p);
  }
  static long factorial(int N) {
    long result = 1;
    for (int i = 1; i <= N; i++) {
      result *= i % p;
      result %= p;
    }
    return result;
  }
  static long power(long base, long expo) {
    if (base == 0) {
      return 0;
    }
    if (base == 1 || expo == 0) {
      return 1;
    }
    if (expo == 1) {
      return base;
    }

    Pair key = new Pair(base, expo);
    if (powerHM.containsKey(key)) {
      return powerHM.get(key);
    }
    long result = (power(base, expo / 2) * power(base, expo - expo / 2)) % p;
    powerHM.put(key, result);
    return result;
  }
  static long reverse(long N) {
    if (reverseHM.containsKey(N)) {
      return reverseHM.get(N);
    }
    long result = power(N, p - 2);
    reverseHM.put(N, result);
    return result;
  } 
}
