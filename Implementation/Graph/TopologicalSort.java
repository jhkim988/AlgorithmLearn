import java.util.*;

public class TopologicalSort {
  static Queue<Integer> topologicalSort_Kahn_Algorithm (ArrayList<Queue<Integer>> graph) {
    int size = graph.size();
    // 1. Get Array of Number of Incomming Edge
    int[] numIncomming = new int[size];
    for (int i = 0; i < size; i++) {
      Queue<Integer> edge = graph.get(i);
      for (int node : edge) {
        numIncomming[node]++;
      }
    }

    // 2. Start with zero-incomming node
    Queue<Integer> zeroIncomming = new LinkedList<>();
    for (int i = 0; i < size; i++) {
      if (numIncomming[i] == 0) zeroIncomming.add(i);
    }

    // 3. Remove Edge from zero-incomming node and Subtract number of Incomming Edge.
    boolean[] deleted = new boolean[size];
    Queue<Integer> result = new LinkedList<>();
    while (!zeroIncomming.isEmpty()) {
      int node = zeroIncomming.poll();
      deleted[node] = true;
      result.add(node);
      Queue<Integer> edge = graph.get(node);
      for (int adj : edge) {
        numIncomming[adj]--;
      }

      // 4. Add zero Incomming node.
      for (int i = 0; i < size; i++) {
        if (!deleted[i] && numIncomming[i] == 0) zeroIncomming.add(i);
      }
    }

    return result;
  }
  public static void main(String[] args) {
    
  }  
}
