import java.io.*;
import java.util.*;

public class BOJ2458 {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    StringTokenizer st = new StringTokenizer(br.readLine());
    int n = Integer.parseInt(st.nextToken());
    int m = Integer.parseInt(st.nextToken());
    ArrayList<Queue<Integer>> graph = new ArrayList<>();
    ArrayList<Queue<Integer>> reverse = new ArrayList<>();
    for (int i = 0; i <= n; i++) {
      graph.add(new LinkedList<>());
      reverse.add(new LinkedList<>());
    }
    for (int i = 0; i < m; i++) {
      st = new StringTokenizer(br.readLine());
      int small = Integer.parseInt(st.nextToken());
      int tall = Integer.parseInt(st.nextToken());
      graph.get(small).add(tall); // small -> tall
      reverse.get(tall).add(small);
    }

    int num = 0;
    for (int v = 1; v <= n; v++) {
      if (test(v, n, graph, reverse)) {
        num++;
      }
    }
    bw.write(Integer.toString(num));
    bw.newLine();
    bw.flush();
  }
  static boolean test(int v, int n, ArrayList<Queue<Integer>> graph, ArrayList<Queue<Integer>> reverse) {
    return bfs(v,graph) + bfs(v, reverse) == n-1;
  }
  static int bfs(int v, ArrayList<Queue<Integer>> graph) {
    Queue<Integer> que = new LinkedList<>();
    boolean[] visit = new boolean[graph.size()];
    que.add(v);
    visit[v] = true;
    int num = 0;
    while (!que.isEmpty()) {
      int crnt = que.poll();
      for (int adj : graph.get(crnt)) {
        if (visit[adj]) continue;
        visit[adj] = true;
        que.add(adj);
        num++;
      }
    }
    return num;
  }
}
