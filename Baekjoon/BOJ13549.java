import java.io.*;
import java.util.*;

public class BOJ13549 {
  private static class Pair implements Comparable<Pair> {
    int node;
    int cost;
    Pair(int node, int cost) {
      this.node = node;
      this.cost = cost;
    }
    @Override
    public int compareTo(Pair other) {
      return this.cost - other.cost;
    }
  }
  public static void main(String[] args) throws IOException {
    // zeroOneBFS();
    Dijkstra();
  }
  static void zeroOneBFS() throws IOException {
    // 0-1 BFS
    int max = 100_000;
    int[] move = {-1, 1};
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    StringTokenizer st = new StringTokenizer(br.readLine());
    int N = Integer.parseInt(st.nextToken());
    int K = Integer.parseInt(st.nextToken());

    int[] dist = new int[max + 1];
    for (int i = 0; i <= max; i++) {
      dist[i] = max + 1;
    }
    dist[N] = 0;
    Deque<Integer> deq = new LinkedList<>();
    deq.addFirst(N);
    while (!deq.isEmpty()) {
      int crnt = deq.removeFirst();
      if (crnt == K) {
        break;
      }
      if (crnt * 2 <= max && dist[crnt * 2] > dist[crnt]) {
        dist[crnt * 2] = dist[crnt]; 
        deq.addFirst(crnt * 2);
      }
      for (int m : move) {
        if (0 <= m + crnt && m + crnt <= max) {
          if (dist[m + crnt] > dist[crnt] + 1) {
            dist[m + crnt] = dist[crnt] + 1;
            deq.addLast(m + crnt);
          } 
        }
      }
    }
    bw.write(dist[K] + "\n");
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

    while (!que.isEmpty()) {
      Pair crnt = que.poll();
      if (crnt.node == K) {
        break;
      }
      if (2 * crnt.node < INF && dist[2*crnt.node] > dist[crnt.node]) {
        dist[2 * crnt.node] = dist[crnt.node];
        que.add(new Pair(2 * crnt.node, crnt.cost));
      }
      if (crnt.node - 1 >= 0 && dist[crnt.node - 1] > dist[crnt.node] + 1) {
        dist[crnt.node - 1] = dist[crnt.node] + 1;
        que.add(new Pair(crnt.node - 1, crnt.cost + 1));
      }
      if (crnt.node + 1 < INF && dist[crnt.node + 1] > dist[crnt.node] + 1) {
        dist[crnt.node + 1] = dist[crnt.node] + 1;
        que.add(new Pair(crnt.node + 1, crnt.cost + 1));
      }
    }
    bw.write(dist[K] + "\n");
    bw.flush();
  }
}
