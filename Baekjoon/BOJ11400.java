import java.io.*;
import java.util.*;

public class BOJ11400 {
  static int id = 1;
  static int[] dfsId;
  static ArrayList<Queue<Integer>> graph;
  static TreeSet<Edge> articulationEdge;
  private static class Edge implements Comparable<Edge> {
    int start, end;
    Edge(int start, int end) {
      if (start < end) {
        this.start = start;
        this.end = end;
      } else {
        this.start = end;
        this.end = start;
      }
    }
    @Override
    public int compareTo(Edge other) {
      if (this.start != other.start) return this.start-other.start;
      return this.end-other.end;
    }
  }
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    StringTokenizer st = new StringTokenizer(br.readLine());
    int v = Integer.parseInt(st.nextToken());
    int e = Integer.parseInt(st.nextToken());
    graph = new ArrayList<>();
    dfsId = new int[v+1];
    for (int i = 0; i <= v; i++) {
      graph.add(new LinkedList<>());
    }
    for (int i = 0; i < e; i++) {
      st = new StringTokenizer(br.readLine());
      int a = Integer.parseInt(st.nextToken());
      int b = Integer.parseInt(st.nextToken());
      graph.get(a).add(b);
      graph.get(b).add(a);
    }
    articulationEdge = new TreeSet<>();

    for (int i = 1; i <= v; i++) {
      if (dfsId[i] != 0) continue;
      dfs(i, 0);
    }
    bw.write(Integer.toString(articulationEdge.size()));
    bw.newLine();
    for (Edge ae : articulationEdge) {
      bw.write(Integer.toString(ae.start));
      bw.write(' ');
      bw.write(Integer.toString(ae.end));
      bw.newLine();
    }
    bw.flush();
  } 
  static int dfs(int node, int parent) {
    dfsId[node] = id++;
    int min = dfsId[node];
    for (int adj : graph.get(node)) {
      if (adj == parent) continue;
      if (dfsId[adj] == 0) {
        int low = dfs(adj, node);
        if (low > dfsId[node]) {
          articulationEdge.add(new Edge(adj, node));
        }
        min = Integer.min(min, low);
      } else {
        min = Integer.min(min, dfsId[adj]);
      }
    }
    return min;
  }
}
