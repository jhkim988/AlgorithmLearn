import java.util.*;

public class SPFA {
  // Shortest Path Faster Algorithm:
  public static void main(String[] args) {
    GraphWeighted graph = new GraphWeighted(10);
    int start = 1;

    final int INF = Integer.MAX_VALUE/2;
    int[] dist = new int[graph.size()];
    Arrays.fill(dist, INF);

    int[] numVisit = new int[graph.size()]; // count the number of visit for each node
    boolean[] inQue = new boolean[graph.size()]; // is in que?
    Queue<Integer> que = new LinkedList<>();
    inQue[start] = true;
    dist[start] = 0;

    while (!que.isEmpty()) {
      int crnt = que.poll();
      inQue[crnt] = false;
      for (Edge next : graph.get(crnt)) {
        if (dist[crnt] + next.weight < dist[next.end]) {
          dist[next.end] = dist[crnt] + next.weight;
          if (!inQue[next.end]) { 
            numVisit[next.end]++; // check negative cycle
            if (numVisit[next.end] >= graph.size()) {
              return;
            }
            que.add(next.end);
            inQue[next.end] = true;
          }
        }
      }
    }
  }  
}
