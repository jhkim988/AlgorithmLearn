import java.io.*;
import java.util.*;

public class BOJ2312 {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    boolean[] isPrime = new boolean[100_001];
    Arrays.fill(isPrime, true);
    for (int i = 2; i < isPrime.length; i++) {
      if (!isPrime[i]) continue;
      for (int j = i+i; j < isPrime.length; j += i) isPrime[j] = false;
    }
    int numTest = Integer.parseInt(br.readLine());
    while (numTest--> 0) {
      int n = Integer.parseInt(br.readLine());
      for (int i = 2; i <= n; i++) {
        if (!isPrime[i] || n % i != 0) continue;
        int copy = n;
        int num = 0;
        while (copy%i == 0) {
          copy /= i;
          num++;
        }
        bw.write(Integer.toString(i));
        bw.write(' ');
        bw.write(Integer.toString(num));
        bw.newLine();
      }
    }
    bw.flush();
  }
}