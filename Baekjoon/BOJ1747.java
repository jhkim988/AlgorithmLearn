import java.io.*;
import java.util.*;

public class BOJ1747 {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    int x = Integer.parseInt(br.readLine());
    boolean[] isPrime = eratosthenes(10_000_000);
    boolean[] isPalindrome = palindrome(10_000_000);
    for (int i = x; i <= 10_000_000; i++) {
      if (isPrime[i] && isPalindrome[i]) {
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
  static boolean[] palindrome(int n) {
    boolean[] result = new boolean[n+1];
    for (int i = 0; i <= 9; i++) {
      result[i] = result[i*11] = true;
    }
    for (int digit = 100; digit < n; digit *= 10) {
      for (int i = 1; i <= 9; i++) result[i*digit+i] = true;
      for (int z = 1; z <= digit/100; z *= 100) {
        int pos = 1;
        while (pos*pos*10 < digit/z) pos*=10;
        for (int j = z; j < z*10; j++) {
          if (result[j]) {
            for (int i = 1; i <= 9; i++) if (i*digit+i + j*pos < n) result[i*digit+i + j*pos] = true;
          }
        }
      }
    }
    return result;
  }
}
