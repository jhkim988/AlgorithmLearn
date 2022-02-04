import java.util.*;

public class FloydWarshall {
  static final int INF = Integer.MAX_VALUE/2;

  public static void main(String[] args) {
    GraphWeighted graph = makeRandomGraph(10, 30);
    int[][] dist = floydWarshall(graph);
    for (int[] d : dist) System.out.println(Arrays.toString(d));
  }
  static int[][] floydWarshall(GraphWeighted graph) {
    // time complexity: O(|V|^3)
    int numV = graph.size();
    int[][] dist = new int[numV][numV];
    for (int i = 0; i < numV; i++) Arrays.fill(dist[i], INF); // Initialize dist[][]
    for (int i = 0; i < numV; i++) {
      for (Edge edge : graph.get(i)) {
        dist[i][edge.end] = edge.weight;
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
  static GraphWeighted makeGraph(int numV, int[][] info) {
    // info: [[start, destination, weigth], [], ...]
    GraphWeighted graph = new GraphWeighted(numV);
    for (int[] edge : info) {
      graph.addEdge(edge[0], edge[1], edge[2], false);
    }
    return graph;
  }
  static GraphWeighted makeRandomGraph(int numV, int numE) {
    int[][] info = new int[numE][3];
    for (int i = 0; i < numE; i++) {
      info[i][0] = (int) (Math.random() * numV);
      info[i][1] = (int) (Math.random() * numV);
      info[i][2] = (int) (Math.random() * 5) - 2;
    }
    return makeGraph(numV, info);
  }
}
