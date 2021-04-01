import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.Graph;
import edu.princeton.cs.algs4.Stack;

// Q3. Euler cycle.
// An Euler cycle in a graph is a cycle (not necessarily simple) that uses every edge in the graph exactly one.
// Show that a connected graph has an Euler cycle if and only if every vertex has even degree.
// Design a linear-time algorithm to determine whether a graph has an Euler cycle, and if so, find one.

// sol)
// proof
// (=>) Suppose graph g is Euler cycle that has vertex with odd degree.
// Let x be vertex with odd degree of g.
// Since g is Euler cycle, all edge is used exactly once by definition.
// Pair the edge entering and exiting vertex x.
// Since x has odd degree, one edge remain. contradiction.

// (<=) Use Mathematical Induction for the number of vertex.
// Let V be the number of vertex of graph.
// 1. V = 0.
// Nothing to proof.
// 2. Induction Hypothesis.
// Suppose every graph with no-odd-degree that has k(or less) vertices is Euler cycle.
// We prove that graph X with no-odd-degree that has (k+1) vertices is Euler cycle.
// As each vertex has even degree (and graph is connected), graph contains a circuit, say C.
// If C contain every edge of graph, It is Euler cycle, done
// If C does not contain some edge of graph.
// Let X' be graph obtained from X removing all the edge in C.
// X' may be disconnected, but still has even degree.
// By Induction Hypothesis, X' is Euler cycle. say its circuit C'.
// Suppose C' is started at vertex w.
// When we meet vertex w by traversing C, Follow circuie C'.
// When we finish following C', Continue C.
// It is Euler cycle.

public class Interview_01_03_EulerCycle {
	// record edge
	private class Edge {
		int v;
		int w;
		boolean isUsed;
		public Edge(int v, int w) {
			this.v = v;
			this.w = w;
			isUsed = false;
		}
		public int other (int vertex) {
			if (v == vertex) return w;
			else if (w == vertex) return v;
			else throw new IllegalArgumentException();
		}
	}
	
	public static Iterable<Integer> isEuler(Graph g) {
		if (g.V() == 0) return null;
		Queue<Edge>[] adj = new Queue[g.V()];
		
		for(int i = 0; i < g.V(); i++)
			adj[i] = new Queue<Edge>();
		
		Stack<Integer> dfs = new Stack<Integer>();
		dfs.push(0);
		while(!dfs.isEmpty()) {
			int v = dfs.pop();

		}
	}
	public static void main(String[] args) {
		
	}
}
