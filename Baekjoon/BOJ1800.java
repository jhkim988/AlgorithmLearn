import java.io.*;
import java.util.*;

public class BOJ1800 {
  private static class TraversalPair {
    int node;
    int numFree;
    TraversalPair(int node, int numFree) {
      this.node = node;
      this.numFree = numFree;
    }
  }
  private static class Pair {
    int end;
    int cost;
    Pair(int end, int cost) {
      this.end = end;
      this.cost = cost;
    }
  }
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    StringTokenizer st = new StringTokenizer(br.readLine());
    int N = Integer.parseInt(st.nextToken());
    int P = Integer.parseInt(st.nextToken());
    int K = Integer.parseInt(st.nextToken());

    ArrayList<Queue<Pair>> graph = new ArrayList<>();
    for (int i = 0; i <= N; i++) graph.add(new LinkedList<>());
    int max = 0;
    for (int i = 0; i < P; i++) {
      st = new StringTokenizer(br.readLine());
      int a = Integer.parseInt(st.nextToken());
      int b = Integer.parseInt(st.nextToken());
      int cost = Integer.parseInt(st.nextToken());
      graph.get(a).add(new Pair(b, cost));
      graph.get(b).add(new Pair(a, cost));
      max = Integer.max(max, cost);
    }

    int lo = -1;
    int hi = max;
    if (!check(hi, graph, K, max + 1)) {
      bw.write("-1\n");
      bw.flush();
      return;
    }
    while (lo + 1 < hi) {
      int mid = (lo + hi) / 2;
      if (!check(mid, graph, K, max + 1)) {
        lo = mid;
      } else {
        hi = mid;
      }
    }

    bw.write(Integer.toString(hi));
    bw.newLine();
    bw.flush();
  }
  static boolean check(int cost, ArrayList<Queue<Pair>> graph, int numFree, int fill) {
    int[] visit = new int[graph.size()];
    Arrays.fill(visit, fill);
    Deque<TraversalPair> deq = new LinkedList<>();
    deq.addFirst(new TraversalPair(1, 0));
    visit[1] = 0;
    while (!deq.isEmpty()) {
      TraversalPair crnt = deq.removeFirst();
      for (Pair next : graph.get(crnt.node)) {
        int nextNumFree = crnt.numFree + (next.cost > cost ? 1 : 0);
        if (nextNumFree > numFree) continue;
        if (visit[next.end] <= nextNumFree) continue;
        if (next.end == graph.size() - 1) return true;
        deq.addLast(new TraversalPair(next.end, nextNumFree));
        visit[next.end] = nextNumFree;
      }
    }
    return false;
  }
}