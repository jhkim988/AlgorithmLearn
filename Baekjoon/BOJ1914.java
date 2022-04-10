import java.io.*;
import java.util.*;

public class BOJ1914 {
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
    recur(n, 1, 3);
    bw.write(Integer.toString(que.size()));
    bw.newLine();
    while (!que.isEmpty()) {
      Pair p = que.poll();
      bw.write(Integer.toString(p.crnt));
      bw.write(' ');
      bw.write(Integer.toString(p.next));
      bw.newLine();
    }
    bw.flush();
  }
  static void recur(int num, int crnt, int next) {
    if (num == 0) return;
    recur(num-1, crnt, 6-crnt-next);
    que.add(new Pair(crnt, next));
    recur(num-1, 6-crnt-next, next);
  }
}
