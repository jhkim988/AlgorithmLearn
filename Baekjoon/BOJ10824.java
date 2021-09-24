import java.io.*;
import java.util.*;

public class BOJ10824 {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    StringTokenizer st = new StringTokenizer(br.readLine());
    String[] input = new String[4];
    for (int i = 0; i < 4; i++) {
      input[i] = st.nextToken();
    }
    long sum1 = Long.parseLong(input[0]) * pow(10L, (long) input[1].length()) + Long.parseLong(input[1]);
    long sum2 = Long.parseLong(input[2]) * pow(10L, (long) input[3].length()) + Long.parseLong(input[3]);
    bw.write((sum1 + sum2) + "\n");
    bw.flush();
  }
  static long pow(long base, long exp) {
    if (exp == 0) {
      return 1L;
    }
    if (exp == 1) {
      return base;
    }
    long half = pow(base, exp / 2);
    if (exp % 2 == 0) {
      return half * half;
    } else {
      return half * half * base;
    }
  }
}
