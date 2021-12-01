import java.io.*;
import java.util.*;

public class BOJ1939 {
  static final int MAX_WEIGHT = 1_000_000_000;
  private static class Pair implements Comparable<Pair> {
    int node;
    int weight;
    Pair(int node, int weight) {
      this.node = node;
      this.weight = weight;
    }
    @Override
    public int compareTo(Pair other) {
      return other.weight - this.weight;
    }
  }
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    StringTokenizer st = new StringTokenizer(br.readLine());
    int N = Integer.parseInt(st.nextToken());
    int M = Integer.parseInt(st.nextToken());
    ArrayList<Queue<Pair>> graph = new ArrayList<>();
    graphInit(graph, N, M, br);
    st = new StringTokenizer(br.readLine());
    int target1 = Integer.parseInt(st.nextToken());
    int target2 = Integer.parseInt(st.nextToken());
    // int answer = getMaxLim_Dijkstra(graph, target1, target2);
    int answer = getMaxLim_BinSearch(graph, target1, target2);
    bw.write(answer + "\n");
    bw.flush();
  }
  static void graphInit(ArrayList<Queue<Pair>> graph, int N, int M, BufferedReader br) throws IOException {
    StringTokenizer st;
    for (int i = 0; i <= N; i++) {
      graph.add(new LinkedList<>());
    }
    for (int i = 0; i < M; i++) {
      st = new StringTokenizer(br.readLine());
      int a = Integer.parseInt(st.nextToken());
      int b = Integer.parseInt(st.nextToken());
      int c = Integer.parseInt(st.nextToken());
      graph.get(a).add(new Pair(b, c));
      graph.get(b).add(new Pair(a, c));
    }
  }
  static int getMaxLim_Dijkstra(ArrayList<Queue<Pair>> graph, int target1, int target2) {
    int size = graph.size();
    int[] weightLim = new int[size];
    weightLim[target1] = MAX_WEIGHT;
    PriorityQueue<Pair> pq = new PriorityQueue<>();
    pq.add(new Pair(target1, MAX_WEIGHT));
    while (!pq.isEmpty()) {
      Pair crnt = pq.poll();
      if (crnt.node == target2) {
        return crnt.weight;
      }
      Queue<Pair> edge = graph.get(crnt.node);
      for (Pair adj : edge) {
        int lim = Math.min(weightLim[crnt.node], adj.weight);
        if (weightLim[adj.node] >= lim) continue;
        weightLim[adj.node] = lim;
        pq.add(new Pair(adj.node, lim));
      }
    } 
    return -1; // NOT FOUND
  }
  static int getMaxLim_BinSearch(ArrayList<Queue<Pair>> graph, int target1, int target2) {
    int lo = 1;
    int hi = MAX_WEIGHT;
    int answer = lo;
    while (lo <= hi) {
      int mid = (lo + hi) / 2;
      if (possible(graph, target1, target2, mid)) {
        lo = mid + 1;
        answer = mid;
      } else {
        hi = mid - 1;
      }
    }
    return answer;
  }
  static boolean possible(ArrayList<Queue<Pair>> graph, int target1, int target2, int weight) {
    HashSet<Integer> visit = new HashSet<>();
    Queue<Integer> que = new LinkedList<>();
    que.add(target1);
    visit.add(target1);
    while (!que.isEmpty()) {
      int crnt = que.poll();
      Queue<Pair> edge = graph.get(crnt);
      for (Pair p : edge) {
        if (visit.contains(p.node)) continue;
        if (p.weight < weight) continue;
        if (p.node == target2) return true;
        que.add(p.node);
        visit.add(p.node);
      }
    }
    return false;
  }
}