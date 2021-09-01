import java.io.*;
import java.util.*;

public class BOJ6588 {
  // String.format(): SLOW!
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    ArrayList<Integer> primes = new ArrayList<>();
    int limit = 10;
    addPrime(primes, limit);
    int num = Integer.parseInt(br.readLine());
    StringBuilder sb = new StringBuilder();
    while (num != 0) {
      int ptr1 = 0;
      int ptr2 = primes.size() - 1;
      while (limit < num) {
        limit *= 10;
        addPrime(primes, limit);
        ptr2 = primes.size() - 1;
      }
      while (ptr1 < ptr2) {
        int ptr1Val = primes.get(ptr1);
        int ptr2Val = primes.get(ptr2);
        int val = ptr1Val + ptr2Val;
        if (val < num) {
          ptr1++;
        } else if (val > num) {
          ptr2--;
        } else {
          sb.append(num).append(" = ").append(ptr1Val).append(" + ").append(ptr2Val).append("\n");
          break;
        }
      }
      num = Integer.parseInt(br.readLine());
    } 
    bw.write(sb.toString());
    bw.flush();
  }  
  static void addPrime(ArrayList<Integer> primes, int limit) {
    if (primes.size() == 0) {
      primes.add(3);
    }
    int last = primes.get(primes.size() - 1);
    if (last >= limit) return;
    for (int i = last; i <= limit; i++) {
      if (isPrime(i)) {
        primes.add(i);
      }
    }
  }
  static boolean isPrime(int x) {
    for (int i = 2; i * i <= x; i++) {
      if (x % i == 0) return false;
    }
    return true;
  }
}
