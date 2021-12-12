import java.io.*;
import java.util.*;

public class BOJ5014 {
  private static class Pair {
    int idx;
    int move;
    Pair(int idx, int move) {
      this.idx = idx;
      this.move = move;
    }
  }
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    StringTokenizer st = new StringTokenizer(br.readLine());
    int F = Integer.parseInt(st.nextToken());
    int S = Integer.parseInt(st.nextToken());
    int G = Integer.parseInt(st.nextToken());
    int U = Integer.parseInt(st.nextToken());
    int D = Integer.parseInt(st.nextToken());

    Queue<Pair> que = new LinkedList<>();
    HashSet<Integer> hs = new HashSet<>();
    que.add(new Pair(S, 0));
    hs.add(S);

    while (!que.isEmpty()) {
      Pair crnt = que.poll();
      // System.out.println("crnt: " + crnt.idx);
      if (crnt.idx == G) {
        bw.write(crnt.move + "\n");
        bw.flush();
        return;
      }
      int up = crnt.idx + U;
      int down = crnt.idx - D;
      if (!hs.contains(up) && up <= F) {
        que.add(new Pair(up, crnt.move + 1));
        hs.add(up);
      }
      if (!hs.contains(down) && down >= 1) {
        que.add(new Pair(down, crnt.move + 1));
        hs.add(down);
      }
    }

    bw.write("use the stairs\n");
    bw.flush();
  }
}
