import java.io.*;
import java.util.*;

public class BOJ2606 {
  static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
  static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
  static int counter = 0;
  public static void main(String[] args) throws IOException {
    int numV = Integer.parseInt(br.readLine());
    int numE = Integer.parseInt(br.readLine());

    ArrayList<Queue<Integer>> graph = new ArrayList<Queue<Integer>>(numV + 1);
    boolean[] marked = new boolean[numV + 1];
    StringTokenizer st;

    // initialize
    for (int i = 0; i <= numV; i++) {
      graph.add(i, new LinkedList<Integer>());
    }

    // input
    for (int i = 0; i < numE; i++) {
      st = new StringTokenizer(br.readLine());
      int node1 = Integer.parseInt(st.nextToken());
      int node2 = Integer.parseInt(st.nextToken());
      graph.get(node1).add(node2);
      graph.get(node2).add(node1);
    }

    bfs(graph, 1, marked);
    bw.write(counter + "\n");
    bw.flush();
  }

  static void bfs(ArrayList<Queue<Integer>> graph, int start, boolean[] marked) {
    Queue<Integer> queBFS = new LinkedList<Integer>();
    queBFS.add(start);
    marked[start] = true;
    while (!queBFS.isEmpty()) {
      int node = queBFS.poll();
      for (int adj : graph.get(node)) {
        if (!marked[adj]) {
          marked[adj] = true;
          queBFS.add(adj);
          counter++;
        }
      }
    }
  }
}
