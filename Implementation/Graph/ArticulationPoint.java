import java.util.*;

public class ArticulationPoint {
  private static Graph graph;
  private static int id = 0;
  private static int[] dfsId;
  private static boolean[] isAP;
  static ArrayList<Integer> aplist() {
    ArrayList<Integer> list = new ArrayList<>();
    dfsId = new int[graph.size()];
    for (int i = 1; i <= graph.size(); i++) {
      if (dfsId[i] == 0) dfs(i, true);
    }
    for (int i = 1; i <= graph.size(); i++) {
      if (isAP[i]) list.add(i);
    }
    return list;
  }
  static int dfs(int node, boolean isRoot) {
    int min = id, numChild = 0;
    dfsId[node] = id++;
    for (int adj : graph.get(node)) {
      if (dfsId[adj] == 0) {
        numChild++;
        int low = dfs(adj, false);
        if (!isRoot && low >= dfsId[node]) isAP[node] = true;
      } else {
        min = Integer.min(min, dfsId[adj]);
      }
    }
    if (isRoot) isAP[node] = numChild >= 2;
    return min;
  }
  public static void main(String[] args) {

  }  
}
