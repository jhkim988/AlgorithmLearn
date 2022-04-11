import java.io.*;
import java.util.*;

public class BOJ1747 {
  static Deque<Integer> deq = new LinkedList<>();
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    int x = Integer.parseInt(br.readLine());
    boolean[] isPrime = eratosthenes(10_000_000);
    for (int i = x; i <= 10_000_000; i++) {
      if (isPrime[i] && isPalindrome(i)) {
        bw.write(Integer.toString(i));
        bw.newLine();
        bw.flush();
        return;
      }
    }
  }  
  static boolean[] eratosthenes(int n) {
    boolean[] result = new boolean[n+1];
    Arrays.fill(result, true);
    result[0] = result[1] = false;
    for (int i = 2; i*i <= n; i++) {
      if (!result[i]) continue;
      for (int j = i+i; j <= n; j += i) {
        result[j] = false;
      }
    }
    return result;
  }
  static boolean isPalindrome(int x) {
    deq.clear();
    while (x > 0) {
      deq.add(x%10);
      x /= 10;
    }
    while (deq.size() >= 2) {
      if (deq.removeLast() != deq.removeFirst()) return false;
    }
    return true;
  }
}
