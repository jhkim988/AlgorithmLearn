import java.io.*;
import java.util.*;

public class BOJ17281 {
  static int n;
  static int[][] info;
  static int[] order;
  static boolean[] use;
  static boolean[] stat;
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    n = Integer.parseInt(br.readLine());
    info = new int[n+1][10];
    for (int inning = 1; inning <= n; inning++) {
      StringTokenizer st = new StringTokenizer(br.readLine());
      for (int i = 1; i <= 9; i++) {
        info[inning][i] = Integer.parseInt(st.nextToken());
      }
    }
    order = new int[10];
    use = new boolean[10];
    stat = new boolean[4];
    bw.write(Integer.toString(recur(1)));
    bw.flush();
  }
  static int recur(int depth) {
    if (depth > 9) {
      return game();
    }
    int max = 0;
    for (int i = 1; i <= 9; i++) {
      if (use[i]) continue;
      if (depth == 4 && i != 1) continue;
      if (depth != 4 && i == 1) continue;
      order[depth] = i;
      use[i] = true;
      max = Integer.max(max, recur(depth+1));
      use[i] = false;
    }
    return max;
  }
  static int game() {
    int score = 0;
    int player = 1;
    for (int inning = 1; inning <= n; inning++) {
      stat[0] = stat[1] = stat[2] = stat[3] = false;
      int out = 0;
      while (out < 3) {
        stat[0] = true;
        if (info[inning][order[player]] == 0) out++;
        else {
          for (int base = 3; base >= 0; base--) {
            if (!stat[base]) continue;
            stat[base] = false;
            if (base + info[inning][order[player]] > 3) {score++;}
            else stat[base + info[inning][order[player]]] = true;
          } 
        }
        player = player % 9 + 1;
      }
    }
    return score;
  }
}
