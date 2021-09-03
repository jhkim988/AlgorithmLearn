import java.io.*;
import java.util.*;

public class BOJ9613 {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    int numTest = Integer.parseInt(br.readLine());
    while (numTest-- > 0) {
      StringTokenizer st = new StringTokenizer(br.readLine());
      int numData = Integer.parseInt(st.nextToken());
      int[] data = new int[numData];
      for (int i = 0; i < numData; i++) {
        data[i] = Integer.parseInt(st.nextToken());
      }

      long sum = 0;
      for (int i = 0; i < numData; i++) {
        for (int j = i + 1; j < numData; j++) {
          sum += gcd(data[i], data[j]);
        }
      }
      bw.write(sum + "\n");
    }
    bw.flush();
  } 
  static int gcd(int a, int b) {
    if (b == 0) {
      return a;
    }
    return gcd(b, a % b);
  }
}
