public class InclusionExclusion {
  public static void main(String[] args) {
    int bound = 100;
    int[] primes = {2, 3, 7, 13}   ; 
    System.out.println(recur(primes, 0, 1, bound));
  }
  static int recur(int[] primes, int idx, int soFar, int bound) {
    // Inclusion-Exclusion principle
    if (idx >= primes.length || soFar * primes[idx] > bound) return 0;
    int ret = bound / (primes[idx] * soFar); // current value
    ret += recur(primes, idx + 1, soFar, bound); // current exclude
    ret -= recur(primes, idx + 1, soFar * primes[idx], bound); // current include
    return ret;
  }
}