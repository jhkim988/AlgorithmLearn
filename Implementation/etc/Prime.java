package etc;

public class Prime {
  static boolean linearSearch(int num) {
    // O(N^2)
    for (int i = 2; i < num; i++) {
      if (num % 2 == 0) return false;
    }
    return true;
  }
  static boolean sqrtSerach(int num) {
    // O(sqrt(N))
    for (int i = 2; i * i <= num; i++) {
      if (num % 2 == 0) return false;
    }
    return true;
  }
  static boolean eratosthenes(int num) {
    // find all prime with <= num.
    // DP
    // space: O(N) (can be optimization)
    // time: O(N log(log(N))), sum 1/p ~= log(logN)
    boolean[] dp = new boolean[num + 1]; // dp[i] = true -> i is prime.
    for (int i = 0; i <= num; i++) {
      dp[i] = true;
    }
    int ptr = 2;
    while (ptr * ptr <= num) {
      for (int i = 2 * ptr; i <= num; i += ptr) { // multiple of ptr is not prime.
        dp[i] = false;
      }
      while (!dp[ptr++]); // find next prime.
    }
    return dp[num];
  }
  static boolean millerRabin(int num) {
    // Suppose num is a odd prime.
    // n - 1 = 2^s * d, where d is odd number.
    // for some a in (Z/num Z)*,
    // 1. a ^ d % num = 1. or
    // 2. a ^ (2^r * d) % num = -1 for some (0 <= r <= s - 1).
    // (1) (2) are not satisfied for all r, num is probably composit number

    // proof)
    // By Fermat's little thm, a ^ (num - 1) % num = 1
    // square root of a ^ (num - 1): 1 or -1
    // If sqrt of a^(num - 1) % num = -1, (2) is satisfied.
    // If (2) is not satisfied for all r, a ^ d = 1. done.

    // Algorithm:
    if (num < 1) return false;
    if (num == 2) return true;

    int d = num - 1;
    int s = 0;
    while (d % 2 == 0) {
      s++;
      d /= 2;
    }    
    double log = Math.log(num);
    int bound = Math.min(num - 2, (int) (2 * log * log));
    WitnessLoop: for (int a = 2; a <= bound; a++) {
      int x = pow(a, d, num);
      if (x == 1 || x == num - 1) continue WitnessLoop;
      for (int i = 1; i < s; i++) {
        x = (x * x) % num;
        if (x == num - 1) continue WitnessLoop;
      }
      return false;
    } 
    return true;
  }
  static int pow(int base, int exp, int mod) {
    if (exp == 0) {
      return 1;
    }
    if (exp == 1) {
      return base % mod;
    }
    int prev = pow(base, exp / 2, mod);
    if (exp % 2 == 0) {
      return (prev * prev) % mod;
    } else {
      return (((prev * prev) % mod) * base) % mod;
    }
  }
  public static void main(String[] args) {

  }
}