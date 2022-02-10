import java.io.*;
import java.util.*;

public class BOJ2644 {
  private static class Pair {
    int node, level;
    Pair(int node, int level) {
      this.node = node;
      this.level = level;
    }
  }
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    int n = Integer.parseInt(br.readLine());
    StringTokenizer st = new StringTokenizer(br.readLine());
    int t1 = Integer.parseInt(st.nextToken());
    int t2 = Integer.parseInt(st.nextToken());
    
    ArrayList<Queue<Integer>> graph = new ArrayList<>();
    for (int i = 0; i <= n; i++) {
      graph.add(new LinkedList<>());
    }
    int e = Integer.parseInt(br.readLine());
    while (e-- > 0) {
      st = new StringTokenizer(br.readLine());
      int p = Integer.parseInt(st.nextToken());
      int c = Integer.parseInt(st.nextToken());
      graph.get(p).add(c);
      graph.get(c).add(p);
    }

    boolean[] visit = new boolean[n+1];
    Queue<Pair> que = new LinkedList<>();
    visit[t1] = true;
    que.add(new Pair(t1, 0));
    while (!que.isEmpty()) {
      Pair crnt = que.poll();
      for (int adj : graph.get(crnt.node)) {
        if (visit[adj]) continue;
        if (adj == t2) {
          bw.write(Integer.toString(crnt.level+1));
          bw.newLine();
          bw.flush();
          return;
        }
        visit[adj] = true;
        que.add(new Pair(adj, crnt.level + 1));
      }
    }

    bw.write("-1\n");
    bw.flush();
  }
}
