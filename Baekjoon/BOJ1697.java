import java.io.*;
import java.util.*;

public class BOJ1697 {
  private static class Pair {
    int pos;
    int count;
    Pair(int pos, int count) {
      this.pos = pos;
      this.count = count;
    }
  }
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    String[] tmp = br.readLine().split(" ");
    int subin = Integer.parseInt(tmp[0]);
    int dongsang = Integer.parseInt(tmp[1]);

    bw.write(hideSeek(subin, dongsang) + "\n");
    bw.flush();
  }
  static int hideSeek(int subin, int dongsang) {
    Queue<Pair> que = new LinkedList<>();
    HashSet<Integer> marked = new HashSet<>();

    marked.add(subin);
    que.add(new Pair(subin, 0));

    Pair crnt = new Pair(0, -1);
    while (!que.isEmpty()) {
      crnt = que.poll();
      if (crnt.pos == dongsang) {
        return crnt.count;
      }
      if (!marked.contains(crnt.pos + 1) && 0 <= crnt.pos + 1 && crnt.pos + 1 <= 100_000) {
        marked.add(crnt.pos + 1);
        que.add(new Pair(crnt.pos + 1, crnt. count + 1));
      }
      if (!marked.contains(crnt.pos - 1) && 0 <= crnt.pos - 1 && crnt.pos - 1 <= 100_000) {
        marked.add(crnt.pos - 1);
        que.add(new Pair(crnt.pos - 1, crnt. count + 1));
      }
      if (!marked.contains(crnt.pos * 2) && 0 <= crnt.pos * 2 && crnt.pos * 2 <= 100_000) {
        marked.add(crnt.pos * 2);
        que.add(new Pair(crnt.pos * 2, crnt. count + 1));
      }
    }
    return - 1;
  }
}
