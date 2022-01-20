import java.util.*;

public class FylodWarshall {
  static final int INF = Integer.MAX_VALUE/2;
  private static class Pair {
    int destination;
    int weight;
    Pair (int destination, int weight) {
      this.destination = destination;
      this.weight = weight;
    }
  }
  public static void main(String[] args) {
    ArrayList<ArrayList<Pair>> graph = makeRandomGraph(10, 40);
    
  }
  static int[][] fyoldWarshall(ArrayList<ArrayList<Pair>> graph) {
    // time complexity: O(|V|^3)
    int numV = graph.size();
    int[][] dist = new int[numV][numV];
    for (int i = 0; i < numV; i++) Arrays.fill(dist[i], INF); // Initialize dist[][]
    for (int i = 0; i < numV; i++) {
      for (Pair edge : graph.get(i)) {
        dist[i][edge.destination] = edge.weight;
      }
    }
    for (int i = 0; i < numV; i++) dist[i][i] = 0;
    for (int k = 0; k < numV; k++) {
      for (int i = 0; i < numV; i++) {
        for (int j = 0; j < numV; j++) {
          // renew cost to i -> j
          if (dist[i][j] > dist[i][k] + dist[k][j]) dist[i][j] = dist[i][k] + dist[k][j];
        }
      }
    }
    return dist;
  }
  static ArrayList<ArrayList<Pair>> makeGraph(int numV, int[][] info) {
    // info: [[start, destination, weigth], [], ...]
    ArrayList<ArrayList<Pair>> graph = new ArrayList<>();
    for (int i = 0; i < numV; i++) graph.add(new ArrayList<>());
    for (int[] edge : info) {
      graph.get(edge[0]).add(new Pair(edge[1], edge[2]));
    }
    return graph;
  }
  static ArrayList<ArrayList<Pair>> makeRandomGraph(int numV, int numE) {
    int[][] info = new int[numE][3];
    for (int i = 0; i < numE; i++) {
      info[i][0] = (int) (Math.random() * numV);
      info[i][1] = (int) (Math.random() * numV);
      info[i][2] = (int) (Math.random() * 5) - 2;
    }
    return makeGraph(numV, info);
  }
}
