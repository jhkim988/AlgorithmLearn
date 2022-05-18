import java.io.*;
import java.util.*;

public class BOJ24479 {
  static int order = 1;
  static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
  static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
  static ArrayList<TreeSet<Integer>> graph;
  static int[] visit;
  public static void main(String[] args) throws IOException {
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
    visit[start] = order++;
    dfs(start);
    for (int i = 1; i <= v; i++) {
      bw.write(Integer.toString(visit[i]));
      bw.newLine();
    }
    bw.flush();
  }
  static void dfs(int node) {
    for (int adj : graph.get(node)) {
      if (visit[adj] != 0) continue;
      visit[adj] = order++;
      dfs(adj);
    }
  }
}
