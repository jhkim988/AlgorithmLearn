import java.io.*;
import java.util.*;

public class BOJ13913 {
  private static class Pair implements Comparable<Pair> {
    // Dijkstra Algorithm
    int node;
    int cost;
    Pair prev;
    Pair(int node, int cost) {
      this.node = node;
      this.cost = cost;
    }
    Pair(int node, int cost, Pair prev) {
      this.node = node;
      this.cost = cost;
      this.prev = prev;
    }
    @Override
    public int compareTo(Pair other) {
      return this.cost - other.cost;
    }
  }
  private static class PairBFS {
    int end;
    PairBFS prev;
    PairBFS(int end, PairBFS prev) {
      this.end = end;
      this.prev = prev;
    }
  }
  public static void main(String[] args) throws IOException {
    // Dijkstra();
    BFS();
  }
  static void BFS() throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    StringTokenizer st = new StringTokenizer(br.readLine());
    int N = Integer.parseInt(st.nextToken());
    int K = Integer.parseInt(st.nextToken());
    int INF = 100_001;

    Queue<PairBFS> que = new LinkedList<>();
    boolean[] marked = new boolean[INF];
    que.add(new PairBFS(N, null));
    PairBFS crnt = new PairBFS(-1, null); // dummy
    while (!que.isEmpty()) {
      crnt = que.poll();
      if (crnt.end == K) {
        break;
      }
      if (2 * crnt.end <= INF && !marked[2 * crnt.end]) {
        que.add(new PairBFS(2 * crnt.end, crnt));
        marked[2 * crnt.end] = true;
      }
      if (crnt.end + 1 <= K && !marked[crnt.end + 1]) {
        que.add(new PairBFS(crnt.end + 1, crnt));
        marked[crnt.end + 1] = true;
      }
      if (crnt.end - 1 >= 0 && !marked[crnt.end - 1]) {
        que.add(new PairBFS(crnt.end - 1, crnt));
        marked[crnt.end - 1] = true;
      }
    }

    Stack<Integer> path = new Stack<>();
    PairBFS tour = crnt;
    while (tour != null) {
      path.push(tour.end);
      tour = tour.prev;
    }
    bw.write((path.size() - 1) + "\n");
    while (!path.isEmpty()) {
      bw.write(path.pop() + " ");
    }
    bw.write("\n");
    bw.flush();
  }
  static void Dijkstra() throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    StringTokenizer st = new StringTokenizer(br.readLine());
    int N = Integer.parseInt(st.nextToken());
    int K = Integer.parseInt(st.nextToken());
    int INF = 100_001;
    int dist[] = new int[INF];
    for (int i = 0; i < INF; i++) {
      dist[i] = INF;
    }
    dist[N] = 0;
    PriorityQueue<Pair> que = new PriorityQueue<>();
    que.add(new Pair(N, 0));
    Pair crnt = new Pair(-1, 0); // dummy
    while (!que.isEmpty()) {
      crnt = que.poll();
      if (crnt.node == K) {
        break;
      }
      if (2 * crnt.node < INF && dist[2*crnt.node] > dist[crnt.node] + 1) {
        dist[2 * crnt.node] = dist[crnt.node] + 1;
        que.add(new Pair(2 * crnt.node, crnt.cost + 1, crnt));
      }
      if (crnt.node - 1 >= 0 && dist[crnt.node - 1] > dist[crnt.node] + 1) {
        dist[crnt.node - 1] = dist[crnt.node] + 1;
        que.add(new Pair(crnt.node - 1, crnt.cost + 1, crnt));
      }
      if (crnt.node + 1 < INF && dist[crnt.node + 1] > dist[crnt.node] + 1) {
        dist[crnt.node + 1] = dist[crnt.node] + 1;
        que.add(new Pair(crnt.node + 1, crnt.cost + 1, crnt));
      }
    }

    Stack<Integer> path = new Stack<>();
    Pair tour = crnt;
    path.push(K);
    while (tour.prev != null) {
      path.push(tour.prev.node);
      tour = tour.prev;
    }
    bw.write(dist[K] + "\n");
    while (!path.isEmpty()) {
      bw.write(path.pop() + " ");
    }
    bw.write("\n");
    bw.flush();
  }
}
