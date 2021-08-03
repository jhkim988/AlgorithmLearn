import java.io.*;
import java.util.*;

public class BOJ1707 {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    int numTest = Integer.parseInt(br.readLine());
    String[] tmp;
    ArrayList<Queue<Integer>> graph;
    for (int i = 0; i < numTest; i++) {
      tmp = br.readLine().split(" ");
      int numV = Integer.parseInt(tmp[0]);
      int numE = Integer.parseInt(tmp[1]);
      graph = new ArrayList<>();

      // graph initialize
      for (int j = 0; j < numV; j++) {
        graph.add(new LinkedList<Integer>());
      }
      for (int j = 0; j < numE; j++) {
        tmp = br.readLine().split(" ");
        int node1 = Integer.parseInt(tmp[0]) - 1;
        int node2 = Integer.parseInt(tmp[1]) - 1;
        graph.get(node1).add(node2);
        graph.get(node2).add(node1);
      }

      if (isBipartite(graph, numV, numE)) {
        bw.write("YES\n");
      } else {
        bw.write("NO\n");
      }
    }
    bw.flush();
  }
  static boolean isBipartite(ArrayList<Queue<Integer>> graph, int numV, int numE) {
    int[] marked = new int[numV];
    Queue<Integer> que = new LinkedList<>();

    int start = 0;
    while (start < numV) {
      if (marked[start] != 0) {
        start++;
        continue;
      } 
      marked[start] = 1;
      que.add(start);
      while (!que.isEmpty()) {
        int crnt = que.poll();
        for (int adj : graph.get(crnt)) {
          if (marked[adj] != 0 && marked[adj] == marked[crnt]) {
            return false;
          }
          if (marked[adj] == 0) {
            marked[adj] = -marked[crnt];
            que.add(adj);
          }
        }
      }
    }
    return true;
  }  
}
