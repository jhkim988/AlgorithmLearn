import java.io.*;
import java.util.*;

public class BOJ2981 {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    int numData = Integer.parseInt(br.readLine());
    int[] data = new int[numData];
    for (int i = 0; i < numData; i++) {
      data[i] = Integer.parseInt(br.readLine());
    }
    Arrays.sort(data);
    int[] table = new int[numData - 1];
    for (int i = 0; i < numData - 1; i++) {
      table[i] = data[i + 1] - data[0];
    }

    // Find All common divisors of table[]
    int gcd = table[0];
    for (int i = 0; i < table.length; i++) {
      gcd = gcd(table[i], gcd);
    }

    TreeSet<Integer> ts = new TreeSet<>();
    for (int i = 1; i * i <= gcd; i++) {
      if (gcd % i == 0) {
        if (i != 1) {
          ts.add(i);
        }
        if (gcd / i != i) {
          ts.add(gcd / i);
        }        
      }
    }
    
    for (int el : ts) {
      bw.write(el + "\n");
    }
    bw.flush();
  }
  static int gcd (int a, int b) {
    if (a == 0) {
      return b;
    } else {
      return gcd(b % a, a);
    }
  }
}
