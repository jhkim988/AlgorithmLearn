import java.util.*;

public class BellmanFord {
	static final int INF = Integer.MAX_VALUE;

	public static void main(String[] args) {
		int numVertex = (int) (Math.random() * 5) + 5;
		int numEdge = (int) (Math.random() * 5) + numVertex * numVertex + 5;
		int rangeWeight = 5;
		GraphWeighted graph = new GraphWeighted(numVertex);
		for (int i = 0; i < numEdge; i++) {
			int start = (int) (Math.random() * numVertex);
			int end = (int) (Math.random() * numVertex);
			int weigth = (int) (Math.random() * 2 * rangeWeight) - rangeWeight / 2;
			graph.addEdge(start, end, weigth, false);
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

	static int[] bellmanFord(int start, GraphWeighted graph) {
		// time complexity: O(VE)
		int numV = graph.size();
		boolean hasNegativeCycle = false;
		int[] dist = new int[numV]; // dist[i]: start -> i mininum cost
		Arrays.fill(dist, INF); // step 1: initilize
		dist[start] = 0;
		for (int i = 0; i < numV - 1; i++) { // step 2: |V| - 1 repeat
			for (int j = 0; j < numV; j++) {
				for (Edge edge : graph.get(j)) { // Renew dist[]
					if (dist[j] != INF && dist[edge.end] > dist[j] + edge.weight) {
						dist[edge.end] = dist[j] + edge.weight;
					}
				}
			}
		}
		// step 3: check NegativeCycle
		checkHasNegativeCycle: for (int j = 0; j < numV; j++) {
			for (Edge edge : graph.get(j)) {
				if (dist[j] != INF && dist[edge.end] > dist[j] + edge.weight) {
					hasNegativeCycle = true;
					break checkHasNegativeCycle;
				}
			}
		}
		return hasNegativeCycle ? null : dist;
	}

	static void printGraph(GraphWeighted graph) {
		for (int i = 0; i < graph.size(); i++) {
			System.out.print(i + ": ");
			for (Edge edge : graph.get(i)) {
				System.out.print("(" + edge.end + "/" + edge.weight + ") ");
			}
			System.out.println();
		}
	}
}
