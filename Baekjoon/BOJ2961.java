import java.io.*;
import java.util.*;

public class BOJ2961 {
  static int n;
  static Pair[] input;
  private static class Pair {
    long val1, val2;
    Pair(long val1, long val2) {
      this.val1 = val1;
      this.val2 = val2;
    }
  }
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    n = Integer.parseInt(br.readLine());
    input = new Pair[n];
    for (int i = 0; i < n; i++) {
      StringTokenizer st = new StringTokenizer(br.readLine());
      long val1 = Long.parseLong(st.nextToken());
      long val2 = Long.parseLong(st.nextToken());
      input[i] = new Pair(val1, val2);
    }
    bw.write(Long.toString(recur(0, 0, 1, 0)));
    bw.flush();
  }
  static long recur(int depth, int num, long val1, long val2) {
    if (depth >= n) {
      if (num == 0) return Long.MAX_VALUE/2;
      return Math.abs(val1-val2);
    }
    return Long.min(recur(depth+1, num, val1, val2), recur(depth+1, num+1, val1*input[depth].val1, val2+input[depth].val2));
  } 
}
