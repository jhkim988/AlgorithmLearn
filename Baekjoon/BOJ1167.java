import java.io.*;
import java.util.*;

public class BOJ1167 {
  static int V;
  static ArrayList<Queue<Pair>> graph;
  static int max;
  static boolean[] marked;
  private static class Pair {
    int end;
    int weight;
    Pair(int end, int weight) {
      this.end = end;
      this.weight = weight;
    }
  }
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    V = Integer.parseInt(br.readLine());
    graph = new ArrayList<>();
    for (int i = 0; i <= V; i++) {
      graph.add(new LinkedList<>());
    }
    for (int i = 0; i < V; i++) {
      StringTokenizer st = new StringTokenizer(br.readLine());
      int start = Integer.parseInt(st.nextToken());
      int end = Integer.parseInt(st.nextToken());
      while (end != -1) {
        int weight = Integer.parseInt(st.nextToken());
        graph.get(start).add(new Pair(end, weight));
        end = Integer.parseInt(st.nextToken()); 
      }
    }
    if (V == 2) {
      bw.write(graph.get(1).peek().weight + "\n");
      bw.flush();
      return;
    }
    for (int i = 1; i <= V; i++) {
      marked = new boolean[V + 1];
      longestToLeaf(i);
      if (graph.get(i).size() != 1) {
        break;
      }
    }
    
    bw.write(max + "\n");
    bw.flush();
  }
  static int longestToLeaf(int start) {
    marked[start] = true;
    if (graph.get(start).size() == 1) { // leaf node
      return 0;
    }
    int longest = 0;
    int secondLongest = 0;
    for (Pair next : graph.get(start)) {
      if (marked[next.end]) {
        continue;
      }
      int nextLong = longestToLeaf(next.end);
      if (longest < nextLong + next.weight) {
        secondLongest = longest;
        longest = nextLong + next.weight;
      } else if (secondLongest < nextLong + next.weight) {
        secondLongest = nextLong + next.weight;
      }
    }
    if (max < longest + secondLongest) {
      max = longest + secondLongest;
    }
    return longest;
  }
}
