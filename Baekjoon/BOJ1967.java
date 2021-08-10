import java.io.*;
import java.util.*;

public class BOJ1967 {
  static ArrayList<Queue<Pair>> graph;
  static int max;
  private static class Pair {
    int node;
    int weight;
    Pair(int node, int weight) {
      this.node = node;
      this.weight = weight;
    }
  }
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    int N = Integer.parseInt(br.readLine());
    graph = new ArrayList<>();
    for (int i = 0; i <= N; i++) {
      graph.add(new LinkedList<>());
    }
    for (int i = 1; i < N; i++) {
      StringTokenizer st = new StringTokenizer(br.readLine());
      int start = Integer.parseInt(st.nextToken());
      int end = Integer.parseInt(st.nextToken());
      int weight = Integer.parseInt(st.nextToken());
      graph.get(start).add(new Pair(end, weight));
    }
    max = 0;
    longestToLeaf(1);
    bw.write(max + "\n");
    bw.flush();
  }
  static int longestToLeaf(int start) {
    // return longest distance from start to leaf
    if (graph.get(start).isEmpty()) {
      return 0;
    }
    int longest = 0;
    int secondLongest = 0;
    for (Pair child : graph.get(start)) {
      int childLongest = longestToLeaf(child.node);
      if (longest < childLongest + child.weight) {
        secondLongest = longest;
        longest = childLongest + child.weight;
      } else if (secondLongest < childLongest + child.weight) {
        secondLongest = childLongest + child.weight;
      }
    }
    if (max < longest + secondLongest) {
      max = longest + secondLongest;
    }
    return longest;
  }
}
