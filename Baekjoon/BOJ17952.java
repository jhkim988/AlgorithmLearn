import java.io.*;
import java.util.*;

public class BOJ17952 {
  private static class Pair {
    int score, lim;
    Pair (int score, int lim) {
      this.score = score;
      this.lim = lim;
    }
  }
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    int timeLim = Integer.parseInt(br.readLine());
    Stack<Pair> stk = new Stack<>();
    int totalScore = 0;
    while (timeLim-- > 0) {
      StringTokenizer st = new StringTokenizer(br.readLine());
      int hasAssaign = Integer.parseInt(st.nextToken());
      if (hasAssaign == 0) {
        if (!stk.isEmpty()) {
          stk.peek().lim--;
          if (stk.peek().lim == 0) {
            totalScore += stk.pop().score;
          }
        }
      } else {
        int score = Integer.parseInt(st.nextToken());
        int lim = Integer.parseInt(st.nextToken()) - 1;
        if (lim == 0) {
          totalScore += score;
        } else {
          stk.push(new Pair(score, lim));
        }
      }
    }
    bw.write(Integer.toString(totalScore));
    bw.newLine();
    bw.flush();
  }
}
