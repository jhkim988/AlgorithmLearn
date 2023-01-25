import java.util.*;

public class TopologicalSort {
	static Queue<Integer> topologicalSort_Kahn_Algorithm(ArrayList<Queue<Integer>> graph) {
		int size = graph.size();
		// 1. Get Array of Number of Incomming Edge
		int[] numIncomming = new int[size]; // O(V), space
		for (int i = 0; i < size; i++) {
			Queue<Integer> edge = graph.get(i);
			for (int node : edge) { // O(E)
				numIncomming[node]++;
			}
		}

		// 2. Start with zero-incomming node
		Queue<Integer> zeroIncomming = new LinkedList<>();
		for (int i = 0; i < size; i++) {
			if (numIncomming[i] == 0)
				zeroIncomming.add(i);
		}

		// 3. Remove Edge from zero-incomming node and Subtract number of Incomming
		// Edge.
		boolean[] deleted = new boolean[size];
		Queue<Integer> result = new LinkedList<>();
		while (!zeroIncomming.isEmpty()) { // O(V)
			int node = zeroIncomming.poll();
			deleted[node] = true;
			result.add(node);
			Queue<Integer> edge = graph.get(node);
			for (int adj : edge) {
				numIncomming[adj]--;
			}

			// 4. Add zero Incomming node.
			for (int i = 0; i < size; i++) {
				if (!deleted[i] && numIncomming[i] == 0)
					zeroIncomming.add(i);
			}
		}

		return result;
	}

	static Stack<Integer> TopologicalSort_DFS(ArrayList<Queue<Integer>> graph) {
		// graph: vertex [0, ..., graph.size()-1]
		int[] indegree = new int[graph.size()];
		boolean[] visit = new boolean[graph.size()];
		Stack<Integer> stk = new Stack<>();
		for (int v = 0; v < graph.size(); v++) {
			for (int u : graph.get(v))
				indegree[u]++;
		}
		for (int v = 0; v < graph.size(); v++) {
			if (indegree[v] == 0)
				dfs(graph, v, visit, stk);
		}
		return stk;
	}

	static void dfs(ArrayList<Queue<Integer>> graph, int node, boolean[] visit, Stack<Integer> stk) {
		for (int adj : graph.get(node)) {
			if (visit[adj])
				continue;
			dfs(graph, adj, visit, stk);
		}
		stk.push(node);
	}

	public static void main(String[] args) {

	}
}
