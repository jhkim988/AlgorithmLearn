import java.io.*;
import java.util.*;

public class BOJ1766 {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    StringTokenizer st = new StringTokenizer(br.readLine());
    int N = Integer.parseInt(st.nextToken());
    int M = Integer.parseInt(st.nextToken());
    ArrayList<ArrayList<Integer>> graph = new ArrayList<>();
    for (int i = 0; i <= N; i++) {
      graph.add(new ArrayList<>());
    }
    for (int i = 0; i < M; i++) {
      st = new StringTokenizer(br.readLine());
      int easy = Integer.parseInt(st.nextToken());
      int hard = Integer.parseInt(st.nextToken());
      graph.get(easy).add(hard);
    }

    Queue<Integer> iter = useIndegree(graph);
    while (!iter.isEmpty()) {
      bw.write(iter.poll() + " ");
    }
    bw.flush();
  }
  static Queue<Integer> useIndegree(ArrayList<ArrayList<Integer>>graph) {
    int V = graph.size() - 1;
    int[] indegree = new int[V + 1];
    for (int i = 1; i <= V; i++) {
      for(int adj : graph.get(i)) {
        indegree[adj]++;
      }
    }

    PriorityQueue<Integer> pq = new PriorityQueue<>();
    for (int i = 1; i <= V; i++) {
      if (indegree[i] == 0) {
        pq.add(i);
      }
    }

    Queue<Integer> result = new LinkedList<>();
    while (!pq.isEmpty()) {
      int crnt = pq.poll();
      result.add(crnt);
      for (int adj : graph.get(crnt)) {
        indegree[adj]--;
        if (indegree[adj] == 0) {
          pq.add(adj);
        }
      }
    }

    return result;
  }
}
