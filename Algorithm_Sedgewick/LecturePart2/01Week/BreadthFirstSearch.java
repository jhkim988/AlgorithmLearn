import edu.princeton.cs.algs4.Queue;

public class BreadthFirstSearch {
	// DFS - use stack
	// BFS - use queue, shortest path(fewest number of edges)
	
	// Proposition
	// BFS computes shortest path(fewest number of edges)
	// from s to all other vertices in a graph in time proportional to E + V.
	
	private boolean[] marked;
	private int[] edgeTo;
	
	private void bfs(Graph G, int s) {
		Queue<Integer> q = new Queue<Integer>();
		q.enqueue(s);
		marked[s] = true;
		while(!q.isEmpty()) {
			int v = q.dequeue();
			for(int w : G.adj(v)) 
				if (!marked[w]) {
					q.enqueue(w);
					marked[w] = true;
					edgeTo[w] = v;
				}
		}
	}
}
