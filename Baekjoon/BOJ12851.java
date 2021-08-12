import java.io.*;
import java.util.*;

public class BOJ12851 {
  static final int INF = 100_000;
  private static class Pair {
    int num;
    int time;
    Pair(int num, int time) {
      this.num = num;
      this.time = time;
    }
  }
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    StringTokenizer st = new StringTokenizer(br.readLine());
    int start = Integer.parseInt(st.nextToken());
    int target = Integer.parseInt(st.nextToken());
  
    // use BFS
    Queue<Pair> que = new LinkedList<>();
    int[] marked = new int[INF + 1];
    Arrays.fill(marked, INF);
    marked[start] = 0;
    que.add(new Pair(start, 0));
    int minTime = INF;
    int numWay = 0;
    while (!que.isEmpty()) {
      Pair crnt = que.poll();
      int pos = crnt.num;
      int time = crnt.time;
      if (pos == target) {
        if (minTime > time) {
          minTime = time;
          numWay = 1;
        } else if (minTime < time) {
          break;
        } else {
          numWay++;
        }
      }
      if (pos + 1 <= target && marked[pos + 1] >= time + 1) {
        que.add(new Pair(pos + 1, time + 1));
        marked[pos + 1] = time + 1;
      }
      if (pos - 1 >= 0 && marked[pos - 1] >= time + 1) {
        que.add(new Pair(pos - 1, time + 1));
        marked[pos - 1] = time + 1;
      }
      if (pos * 2 <= INF && marked[pos * 2] >= time + 1) {
        que.add(new Pair(pos * 2, time + 1));
        marked[pos * 2] = time + 1;
      }
    }
    bw.write(minTime + "\n" + numWay + "\n");
    bw.flush();
  }
}
