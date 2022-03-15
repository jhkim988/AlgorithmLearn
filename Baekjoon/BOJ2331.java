import java.io.*;
import java.util.*;

public class BOJ2331 {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    StringTokenizer st = new StringTokenizer(br.readLine());
    int a = Integer.parseInt(st.nextToken());
    int p = Integer.parseInt(st.nextToken());
    int[] dp = new int[500_000];
    int i = 1;
    while (dp[a] == 0) {
      dp[a] = i++;
      a = f(a, p);
    }
    bw.write(Integer.toString(dp[a] - 1));
    bw.newLine();
    bw.flush();
  }
  static int f(int x, int p) {
    int val = 0;
    while (x > 0) {
      int product = 1;
      int tmp = x % 10;
      for (int i = 0; i < p; i++) product *= tmp;
      val += product;
      x /= 10;
    }
    return val;
  }
}
