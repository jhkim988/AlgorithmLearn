import java.io.*;
import java.util.*;

public class BOJ2585 {
  private static class Node {
    int x, y;
    Node(int x, int y) {
      this.x = x;
      this.y = y;
    }
    double dist(Node other) {
      return Math.sqrt((this.x - other.x) * (this.x - other.x) + (this.y - other.y) * (this.y - other.y));
    }
  }
  private static class Edge {
    double dist;
    int id;
    Edge(int id, double dist) {
      this.id = id;
      this.dist = dist;
    }
  }
  private static class Pair {
    int id, level;
    Pair(int id, int level) {
      this.id = id;
      this.level = level;
    }
  }
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    StringTokenizer st = new StringTokenizer(br.readLine());
    int n = Integer.parseInt(st.nextToken());
    int k = Integer.parseInt(st.nextToken());
    Node[] nodeList = new Node[n + 2];
    nodeList[0] = new Node(0, 0);
    nodeList[n + 1] = new Node(10_000, 10_000);
    for (int i = 1; i <= n; i++) {
      st = new StringTokenizer(br.readLine());
      int x = Integer.parseInt(st.nextToken());
      int y = Integer.parseInt(st.nextToken());
      nodeList[i] = new Node(x, y);
    }

    ArrayList<Queue<Edge>> graph = new ArrayList<>();
    for (int i = 0; i < n + 2; i++) {
      graph.add(new LinkedList<>());
    }
    for (int i = 0; i < n + 2; i++) {
      for (int j = 0; j < n + 2; j++) {
        if (i == j) continue;
        double dist = nodeList[i].dist(nodeList[j]);
        graph.get(i).add(new Edge(j, dist));
        graph.get(j).add(new Edge(i, dist));
      }
    }
  
    int lo = 0;
    int hi = (int) Math.ceil(nodeList[0].dist(nodeList[n + 1]));
    while (lo + 1 < hi) {
      int mid = (lo + hi) / 2;
      if (!check(mid, k, graph, nodeList)) {
        lo = mid;
      } else {
        hi = mid;
      }
    }

    bw.write(Integer.toString(hi));
    bw.newLine();
    bw.flush();
  }
  static boolean check(int key, int times, ArrayList<Queue<Edge>> graph, Node[] nodeList) {
    boolean[] visit = new boolean[nodeList.length];
    Queue<Pair> que = new LinkedList<>();
    que.add(new Pair(0, 0));
    visit[0] = true;
    while (!que.isEmpty()) {
      Pair crnt = que.poll();
      if (crnt.level > times) return false;
      for (Edge next : graph.get(crnt.id)) {
        if (visit[next.id]) continue;
        if (next.dist > 10 * key) continue;
        if (next.id == nodeList.length - 1) return true;
        visit[next.id] = true;
        que.add(new Pair(next.id, crnt.level + 1));
      }
    }
    return false;
  }
}
