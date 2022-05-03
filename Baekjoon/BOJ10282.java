import java.io.*;
import java.util.*;

public class BOJ10282 {
  private static class Node implements Comparable<Node> {
    int end, weight;
    Node(int end, int weight) {
      this.end = end;
      this.weight = weight;
    }
    @Override
    public int compareTo(Node other) {
      return this.weight-other.weight;
    }
  }
  public static void main(String[] args) throws IOException {
    final int INF = Integer.MAX_VALUE/2;
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    int t = Integer.parseInt(br.readLine());
    while (t--> 0) {
      StringTokenizer st = new StringTokenizer(br.readLine());
      int v = Integer.parseInt(st.nextToken());
      int e = Integer.parseInt(st.nextToken());
      int startNode = Integer.parseInt(st.nextToken());
      ArrayList<Queue<Node>> graph = new ArrayList<>();
      for (int i = 0; i <= v; i++) {
        graph.add(new LinkedList<>());
      }
      while (e-- > 0) {
        st = new StringTokenizer(br.readLine());
        int end = Integer.parseInt(st.nextToken());
        int start = Integer.parseInt(st.nextToken());
        int second = Integer.parseInt(st.nextToken());
        graph.get(start).add(new Node(end, second));
      }
      int[] dist = new int[v+1];
      Arrays.fill(dist, INF);
      dist[startNode] = 0;
      PriorityQueue<Node> pq = new PriorityQueue<>();
      pq.add(new Node(startNode, 0));
      while (!pq.isEmpty()) {
        Node crnt = pq.poll();
        if (crnt.weight > dist[crnt.end]) continue;
        for (Node adj : graph.get(crnt.end)) {
          if (dist[adj.end] <= dist[crnt.end] + adj.weight) continue;
          dist[adj.end] = dist[crnt.end] + adj.weight;
          pq.add(new Node(adj.end, dist[adj.end]));
        }
      }
      int num = 0;
      int time = 0;
      for (int i = 0; i <= v; i++) {
        if (dist[i] == INF) continue;
        num++;
        if (time < dist[i]) time = dist[i];
      }
      bw.write(Integer.toString(num));
      bw.write(' ');
      bw.write(Integer.toString(time));
      bw.newLine();
    }
    bw.flush();
  }
}
