import java.io.*;
import java.util.*;

public class BOJ11502 {
  static boolean[] isPrime;
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    isPrime = new boolean[1_001];
    Arrays.fill(isPrime, true);
    isPrime[0] = isPrime[1] = false;
    for (int i = 2; i < isPrime.length; i++) {
      if (!isPrime[i]) continue;
      for (int j = i*2; j < isPrime.length; j += i) {
        isPrime[j] = false;
      }
    }

    int numTest = Integer.parseInt(br.readLine());
    while(numTest-- > 0) {
      int k = Integer.parseInt(br.readLine());
      for (int i = 0; i < isPrime.length; i++) {
        if (!isPrime[i]) continue;
        int x = twoSum(k-i);
        if (x < 0) continue;
        bw.write(Integer.toString(i));
        bw.write(' ');
        bw.write(Integer.toString(x));
        bw.write(' ');
        bw.write(Integer.toString(k-i-x));
        bw.newLine();
        break;
      }
    }
    bw.flush();
  }
  static int twoSum(int x) {
    for (int i = 0; i < isPrime.length && x-i >= 0; i++) {
      if (!isPrime[i]) continue;
      if (!isPrime[x-i]) continue;
      return i;
    } 
    return -1;
  }
}