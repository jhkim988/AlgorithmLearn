import java.io.*;
import java.util.*;

public class BOJ13511 {
  static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
  static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
  static int log;
  static int[][] sparse;
  static int[] level;
  static long[][] sum;
  private static class Edge {
    int end, weight;
    Edge(int end, int weight) {
      this.end = end;
      this.weight = weight;
    }
  }
  private static class PairBFS {
    int id, level;
    PairBFS(int id, int level) {
      this.id = id;
      this.level = level;
    }
  }
  public static void main(String[] args) throws IOException {
    int n = Integer.parseInt(br.readLine());
    init(n);

    int q = Integer.parseInt(br.readLine());
    while (q-- > 0) {
      StringTokenizer st = new StringTokenizer(br.readLine());
      int type = Integer.parseInt(st.nextToken());
      int node1 = Integer.parseInt(st.nextToken());
      int node2 = Integer.parseInt(st.nextToken());
      int lca = findLCA(node1, node2);
      if (type == 1) {
        long cost = getCost(node1, lca) + getCost(node2, lca);
        bw.write(Long.toString(cost));
        bw.newLine();
      } else {
        int k = Integer.parseInt(st.nextToken());
        int len1 = level[node1] - level[lca];
        int len2 = level[node2] - level[lca];
        int answer = (k <= (len1 + 1)) ? up(node1, k - 1) : up(node2, len1 + len2 + 1 - k);
        bw.write(Integer.toString(answer));
        bw.newLine();
      }
    }
    bw.flush();
  }
  @SuppressWarnings("unchecked")
  static void init(int n) throws IOException {
    Queue<Edge>[] graph = new Queue[n + 1];
    for (int i = 0; i <= n; i++) {
      graph[i] = new LinkedList<>();
    }
    for (int i = 1; i < n; i++) {
      StringTokenizer st = new StringTokenizer(br.readLine());
      int node1 = Integer.parseInt(st.nextToken());
      int node2 = Integer.parseInt(st.nextToken());
      int weight = Integer.parseInt(st.nextToken());
      graph[node1].add(new Edge(node2, weight));
      graph[node2].add(new Edge(node1, weight));
    }

    log = 0;
    while (1 << log < n) log++;
    sparse = new int[log][n + 1];
    sparse[0][1] = 1;
    sum = new long[log][n + 1];
    level = new int[n + 1];
    boolean[] visit = new boolean[n + 1];
    Queue<PairBFS> que = new LinkedList<>();
    visit[1] = true;
    level[1] = 1;
    que.add(new PairBFS(1, 1));
    while (!que.isEmpty()) {
      PairBFS crnt = que.poll();
      for (Edge next : graph[crnt.id]) {
        if (visit[next.end]) continue;
        sparse[0][next.end] = crnt.id;
        sum[0][next.end] = next.weight;
        level[next.end] = crnt.level + 1;
        visit[next.end] = true;
        que.add(new PairBFS(next.end, level[next.end]));
      }
    }

    for (int k = 1; k < log; k++) {
      for (int x = 1; x <= n; x++) {
        sparse[k][x] = sparse[k - 1][sparse[k - 1][x]];
        sum[k][x] = sum[k - 1][x] + sum[k - 1][sparse[k - 1][x]];
      }
    }
  }
  static int findLCA(int a, int b) {
    if (a == b) return a;
    if (level[a] != level[b]) {
      if (level[a] > level[b]) {
        int tmp = a;
        a = b;
        b = tmp;
      }
      int diff = level[b] - level[a];
      for (int k = 0; k < log; k++) {
        if ((diff & (1 << k)) == 0) continue;
        b = sparse[k][b];
      }
      if (a == b) return b;
    }

    for (int k = log - 1; k >= 0; k--) {
      if (sparse[k][a] == sparse[k][b]) continue;
      a = sparse[k][a];
      b = sparse[k][b];
    }
    return sparse[0][a];
  }
  static long getCost(int a, int lca) {
    if (level[a] == level[lca]) return 0L;
    long cost = 0;
    int diff = level[a] - level[lca];
    for (int k = 0; k < log; k++) {
      if ((diff & (1 << k)) == 0) continue;
      cost += sum[k][a];
      a = sparse[k][a];
    }
    return cost;
  }
  static int up(int a, int add) {
    for (int b = 0; b < log; b++) {
      if ((add & (1 << b)) == 0) continue;
      a = sparse[b][a];
    }
    return a;
  }
}
