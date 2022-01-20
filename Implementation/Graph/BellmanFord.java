import java.util.*;

public class BellmanFord {
  static final int INF = Integer.MAX_VALUE;
  private static class Pair {
    int destination;
    int weight;
    Pair(int destination, int weight) {
      this.destination = destination;
      this.weight = weight;
    }
  }
  public static void main(String[] args) {
    int numVertex = (int) (Math.random() * 5) + 5;
    int numEdge = (int) (Math.random() * 5) + numVertex * numVertex + 5;
    int rangeWeight = 5;
    ArrayList<ArrayList<Pair>> graph = new ArrayList<>();
    for (int i = 0; i < numVertex; i++) graph.add(new ArrayList<>());   
    for (int i = 0; i < numEdge; i++) {
      int start = (int) (Math.random() * numVertex);
      int end = (int) (Math.random() * numVertex);
      int weigth = (int) (Math.random() * 2 * rangeWeight) - rangeWeight/2;
      graph.get(start).add(new Pair(end, weigth));
    }
    printGraph(graph);
    int startNode = (int) (Math.random() * numVertex);
    int[] dist = bellmanFord(startNode, graph);
    if (dist == null) {
      System.out.println("Graph has negative cycle.");
    } else {
      System.out.println("start: " + startNode + " / dist[]: " + Arrays.toString(dist));
    }
  }
  static int[] bellmanFord(int start, ArrayList<ArrayList<Pair>> graph) {
    // time complexity: O(VE)
    int numV = graph.size();
    boolean hasNegativeCycle = false;
    int[] dist = new int[numV]; // dist[i]: start -> i mininum cost
    Arrays.fill(dist, INF); // step 1: initilize
    dist[start] = 0;
    for (int i = 0; i < numV - 1; i++) { // step 2: |V| - 1 repeat
      for (int j = 0; j < numV; j++) {
        for (Pair edge : graph.get(j)) { // Renew dist[]
          if (dist[j] != INF && dist[edge.destination] > dist[j] + edge.weight) {
            dist[edge.destination] = dist[j] + edge.weight;
          }
        }
      }
    }
    // step 3: check NegativeCycle
    checkHasNegativeCycle: for (int j = 0; j < numV; j++) {
      for (Pair edge : graph.get(j)) {
        if (dist[j] != INF && dist[edge.destination] > dist[j] + edge.weight) {
          hasNegativeCycle = true;
          break checkHasNegativeCycle;
        }
      }
    }
    return hasNegativeCycle ? null : dist;
  }
  static void printGraph(ArrayList<ArrayList<Pair>> graph) {
    for (int i = 0; i < graph.size(); i++) {
      System.out.print(i + ": ");
      for (Pair edge : graph.get(i)) {
        System.out.print("(" + edge.destination + "/" + edge.weight + ") ");
      }
      System.out.println();
    }
  }
}
