import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.algs4.Queue;

public class DigraphSearch {
	// Same method as for undirected graphs.
	// Every undirected graph is a digraph(with edges in both directions).
	// DFS is a digraph algorithm.
	// same code...
	
	// Similary BFS
	// Multiple-source shortest paths
	// Web crawler
	
	private boolean[] marked;
	public DigraphSearch(Digraph G, int s) {
		marked = new boolean[G.V()];
		dfs(G, s);
		bfs(G, s);
	}
	private void dfs(Digraph G, int v) {
		marked[v] = true;
		for(int w : G.adj(v))
			if (!marked[w]) dfs(G, w);
	}
	private void bfs(Digraph G, int s) {
		Queue<Integer> q = new Queue<Integer>();
		q.enqueue(s);
		while(!q.isEmpty()) {
			int v = q.dequeue();
			marked[v] = true;
			for(int w : G.adj(v))
				if (!marked[w]) q.enqueue(w);
		}
	}
	public boolean visited(int v) {
		return marked[v];
	}
	
	// Application: program control-flow analysis
	// Every program is digraph.
	// Vertex = basic block of instructions(straight-line program).
	// Edge = jump.
	// Dead code elimination - Find(and remove) unreachable code.
	// Infinite-loop detection - determine whether exit is unreachable.
	
	// Application: mark-sweep garbage collector
	// Vertex = object.
	// Edge = reference.
	// Roots. Objects known to be directly accessible by program.
	// Reachable objects. Objects indirectly accessible by program.
	// Mark: mark all reachable objects
	// Sweep: if object is unmarked, it is garbage(so add to free list)
	// Memory cost. Uses 1 extra mark bit per object + DFS stack
}
