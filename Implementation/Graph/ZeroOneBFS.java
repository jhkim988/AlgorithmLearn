import java.util.*;

public class ZeroOneBFS {
	private static class Node {
		int end;
		int weight;

		Node(int end, int weight) {
			this.end = end;
			this.weight = weight;
		}
	}

	public static void main(String[] args) {
		// Graph:
		final int V = 20; // number of vertex, id: 1 ~ 20
		final int E = 10; // number of edge
		ArrayList<Queue<Node>> graph = new ArrayList<>(); // ArrayList of Iterable Object

		// Graph Initialize:
		for (int i = 0; i <= V; i++) {
			graph.add(new LinkedList<>());
		}
		for (int i = 0; i < E; i++) {
			// Generate Random Edge
			int start = (int) (Math.random() * 20) + 1;
			int end = (int) (Math.random() * 20) + 1;
			int weight = (int) (Math.random() * 2);
			graph.get(start).add(new Node(end, weight));
			// graph.get(end).add(new Node(start, weight)); // Undirected Graph
		}

		// 0-1 BFS:
		// Use Deque.
		int start = 1;
		int target = 20;
		Node startNode = new Node(start, 0);
		Deque<Node> deq = new LinkedList<>();
		int[] cost = new int[V + 1];
		Arrays.fill(cost, Integer.MAX_VALUE);
		cost[start] = 0;

		deq.addFirst(startNode);
		while (!deq.isEmpty()) {
			Node crnt = deq.removeFirst();

			if (crnt.end == target) {
				// Find. cost: crnt.weight;
				break;
			}

			for (Node adj : graph.get(crnt.end)) {
				// Zero cost: addFirst / One cost: addLast
				if (adj.weight == 0) {
					if (cost[adj.end] <= cost[crnt.end])
						continue;
					deq.addFirst(new Node(adj.end, crnt.weight));
					cost[adj.end] = crnt.weight;
				} else {
					if (cost[adj.end] <= cost[crnt.end] + 1)
						continue;
					deq.addLast(new Node(adj.end, crnt.weight + 1));
					cost[adj.end] = crnt.weight + 1;
				}
			}
		}
		// Not Found: disconnected
	}
}
