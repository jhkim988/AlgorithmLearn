import java.io.*;
import java.util.*;

public class BOJ1240 {
  private static int n, m, h;
  private static ArrayList<Queue<Pair>> graph;
  private static int[] level;
  private static int[][] sparse, dist;
  private static class Pair {
    int node, weight;
    Pair(int node, int weight) {
      this.node = node;
      this.weight = weight;
    }
  }
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    StringTokenizer st = new StringTokenizer(br.readLine());
    n = Integer.parseInt(st.nextToken());
    m = Integer.parseInt(st.nextToken());
    graph = new ArrayList<>();
    for (int i = 0; i <= n; i++) {
      graph.add(new LinkedList<>());
    }
    for (int i = 1; i < n; i++) {
      st = new StringTokenizer(br.readLine());
      int start = Integer.parseInt(st.nextToken());
      int end = Integer.parseInt(st.nextToken());
      int weight= Integer.parseInt(st.nextToken());
      graph.get(start).add(new Pair(end, weight));
      graph.get(end).add(new Pair(start, weight));
    }
    h = height();

    level = new int[n+1];
    sparse = new int[h][n+1];
    dist = new int[h][n+1];

    int root = 1;
    initBfs(root);
    for (int x = 0; x < h; x++) {
      sparse[x][root] = root;
      dist[x][root] = 0;
    }
    for (int x = 1; x < h; x++) {
      for (int i = 1; i <= n; i++) {
        sparse[x][i] = sparse[x-1][sparse[x-1][i]];
        dist[x][i] = dist[x-1][i] + dist[x-1][sparse[x-1][i]];
      }
    }
    while (m-- > 0) {
      st = new StringTokenizer(br.readLine());
      int start = Integer.parseInt(st.nextToken());
      int end = Integer.parseInt(st.nextToken());
      bw.write(Integer.toString(query(start, end)));
      bw.newLine();
    }
    bw.flush();
  }
  static int height() {
    int h = 0;
    while (1 << h < n) {
      h++;
    }
    return h;
  }
  static void initBfs(int root) {
    Queue<Integer> que = new LinkedList<>();
    que.add(root);
    level[root] = 1;
    while (!que.isEmpty()) {
      int crnt = que.poll();
      for (Pair adj : graph.get(crnt)) {
        if (level[adj.node] != 0) continue;
        level[adj.node] = level[crnt]+1;
        sparse[0][adj.node] = crnt;
        dist[0][adj.node] = adj.weight;
        que.add(adj.node);
      }
    }
  }
  static int query(int a, int b) {
    if (level[a] < level[b]) {
      int tmp = a;
      a = b;
      b = tmp;
    }
    int sum = 0;
    int diff = level[a] - level[b];
    for (int j = h-1; j >= 0; j--) {
      if ((diff & (1<<j)) == 0) continue;
      sum += dist[j][a];
      a = sparse[j][a];
    }
    if (a == b) return sum;

    for (int j = h-1; j >= 0; j--) {
      if (sparse[j][a] == sparse[j][b]) continue;
      sum += dist[j][a] + dist[j][b];
      a = sparse[j][a];
      b = sparse[j][b];
    }
    return sum + dist[0][a] + dist[0][b];
  }
}