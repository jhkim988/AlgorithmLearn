import java.io.*;
import java.math.BigInteger;
import java.util.*;

public class BOJ1914 {
  static int tot;
  static long move = 0;
  private static Queue<Pair> que = new LinkedList<>();
  private static class Pair {
    int crnt, next;
    Pair(int crnt, int next) {
      this.crnt = crnt;
      this.next = next;
    }
  }
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    int n = Integer.parseInt(br.readLine());
    tot = n;
    if (tot <= 20) {
      recur(n, 1, 3);
      bw.write(Long.toString(move));
      bw.newLine();
      while (!que.isEmpty()) {
        Pair p = que.poll();
        bw.write(Integer.toString(p.crnt));
        bw.write(' ');
        bw.write(Integer.toString(p.next));
        bw.newLine();
      }
    } else {
      BigInteger[] dp = new BigInteger[n+1];
      dp[1] = BigInteger.ONE;
      for (int i = 2; i <= n; i++) {
        dp[i] = dp[i-1].multiply(BigInteger.TWO).add(BigInteger.ONE);
      }
      bw.write(dp[n].toString());
      bw.newLine();
    }
    bw.flush();
  }
  static void recur(int num, int crnt, int next) {
    if (num == 0) return;
    recur(num-1, crnt, 6-crnt-next);
    if (tot <= 20) que.add(new Pair(crnt, next));
    move++;
    recur(num-1, 6-crnt-next, next);
  }
}
