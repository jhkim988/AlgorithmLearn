import java.io.*;
import java.util.*;

public class BOJ5567 {
  private static class Node {
    int id, level;
    Node(int id, int level) {
      this.id = id;
      this.level = level;
    }
  }
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    int n = Integer.parseInt(br.readLine());
    int m = Integer.parseInt(br.readLine());
    ArrayList<Queue<Integer>> graph = new ArrayList<>();
    for (int i = 0; i <= n; i++) graph.add(new LinkedList<>());
    for (int i = 0; i < m; i++) {
      StringTokenizer st = new StringTokenizer(br.readLine());
      int a = Integer.parseInt(st.nextToken());
      int b = Integer.parseInt(st.nextToken());
      graph.get(a).add(b);
      graph.get(b).add(a);
    }
    int num = 0;
    boolean[] visit = new boolean[n+1];
    Queue<Node> que = new LinkedList<>();
    visit[1] = true;
    que.add(new Node(1, 0));
    bfs: while (!que.isEmpty()) {
      Node crnt = que.poll();
      for (int adj : graph.get(crnt.id)) {
        if (visit[adj]) continue;
        if (crnt.level + 1 > 2) break bfs;
        que.add(new Node(adj, crnt.level + 1));
        visit[adj] = true;
        num++;
      }
    }
    bw.write(Integer.toString(num));
    bw.newLine();
    bw.flush();
  }
}