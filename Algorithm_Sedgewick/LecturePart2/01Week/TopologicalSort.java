import edu.princeton.cs.algs4.Stack;

public class TopologicalSort {
	// Goal. Given a set of tasks to be completed with precedence constraints,
	// in whici order should we schedule the tasks?
	
	// Diagraph model. vertex = task, edge = precedence constraint.
	
	// DAG. Directed acyclic graph.
	// if graph has cycle, no way to solve the problem.
	// Topological Sort. Redraw DAG so all edge point upwards.

	// Use DFS.
	// Return vertices in reverse postorder.
	// DFS -> the order in which we are done with the vertices, that is called postorder.
	// (Put it on stack)
	
	// Proposition
	// Reverse DFS postorder of a DAG is a topological order.
	// Consider any edge v -> w. When dfs(v) is called:
	// case 1. dfs(w) has already been called and returned. - w was done before v.
	// case 2. dfs(w) has not yet been called.
	// dfs(w) will get called directly or indirectly by dfs(v)
	// and will finish before dfs(v). - w was doen before v.
	// case 3. dfs(w) has already been called, but has not yet returned.
	// Can't happen in DAG: function call stack contains path w -> v.
	// So v -> w would complete a cycle.
	
	// Directed cycle detection
	// Proposition. A digraph has a topological order iff no directed cycle.
	// if directed cycle, topological order impossible
	// if no directed cycle, DFS-based algorithm finds a topological order.
	
	// Given a digraph, find a directed cycle - Use DFS.
	
	// Application.
	// Precedence Scheduling
	// Cyclic Inheritance(compiler)
	
	private boolean[] marked;
	private Stack<Integer> reversePost;
	
	public TopologicalSort(Digraph G) {
		reversePost = new Stack<Integer>();
		marked = new boolean[G.V()];
		for(int v = 0; v < G.V() && !marked[v]; v++)
			dfs(G, v);
	}
	private void dfs (Digraph G, int v) {
		marked[v] = true;
		for(int w : G.adj(v))
			if (!marked[w]) dfs(G, w);
		reversePost.push(v);
	}
	public Iterable<Integer> reversePost() {
		return reversePost;
	}
}
