import java.io.*;
import java.util.*;

public class BOJ24444 {
  static int[] visit;
  static ArrayList<TreeSet<Integer>> graph;
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    StringTokenizer st = new StringTokenizer(br.readLine());
    int v = Integer.parseInt(st.nextToken());
    int e = Integer.parseInt(st.nextToken());
    int start = Integer.parseInt(st.nextToken());
    graph = new ArrayList<>();
    visit = new int[v+1];
    for (int i = 0; i <= v; i++) graph.add(new TreeSet<>());
    while (e-- > 0) {
      st = new StringTokenizer(br.readLine());
      int a = Integer.parseInt(st.nextToken());
      int b = Integer.parseInt(st.nextToken());
      graph.get(a).add(b);
      graph.get(b).add(a);
    }
    bfs(start);
    for (int i = 1; i <= v; i++) {
      bw.write(Integer.toString(visit[i]));
      bw.newLine();
    }
    bw.flush();
  }
  static void bfs(int start) {
    int order = 1;
    visit[start] = order++;
    Queue<Integer> que = new LinkedList<>();
    que.add(start);
    while (!que.isEmpty()) {
      int crnt = que.poll();
      for (int adj : graph.get(crnt)) {
        if (visit[adj] != 0) continue;
        visit[adj] = order++;
        que.add(adj);
      }
    }
  }
}
