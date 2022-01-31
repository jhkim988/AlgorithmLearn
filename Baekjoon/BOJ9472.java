import java.io.*;
import java.util.*;

public class BOJ9472 {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    long[] factorial = new long[18];
    factorial[0] = factorial[1] = 1;
    for (int i = 2; i < 18; i++) {
      factorial[i] = factorial[i - 1] * i;
    }
    int numTest = Integer.parseInt(br.readLine());
    while (numTest-- > 0) {
      StringTokenizer st = new StringTokenizer(br.readLine());
      int number = Integer.parseInt(st.nextToken());
      int n = Integer.parseInt(st.nextToken());
      int k = Integer.parseInt(st.nextToken());
      long incExc = 0;
      boolean positive = true;
      for (int count = 1; count <= k; count++) {
        long add = (factorial[k] / factorial[k-count] / factorial[count]) * factorial[n - count];
        incExc += positive ? add : -add;
        positive = !positive;
      }
      bw.write(Integer.toString(number));
      bw.write(' ');
      bw.write(Long.toString(factorial[n] - incExc));
      bw.newLine();
      bw.flush();
    }
  }
}
